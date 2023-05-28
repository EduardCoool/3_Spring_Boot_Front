package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.dao.PostRepository;
import spring.model.Post;
import spring.service.chatGpt.ChatGptService;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService, ChatGPTModerationService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ChatGptService chatGptService;

    @Value("${sortByValue}")
    private String sortByValue;

    @Override
    public Post findNewsById(Long newsId) {
        Optional<Post> newsFromDb = postRepository.findById(newsId);
        return newsFromDb.orElse(new Post());
    }

    @Override
    public List<Post> allNewsForAdmin() {
        return postRepository.findAll(Sort.by(sortByValue))
                .stream()
                .filter((news) -> !news.isModerationAdmin())
                .toList();
    }

    @Override
    public int getCountNoModerationToAdmin() {
        return postRepository.findAll(Sort.by(sortByValue))
                .stream()
                .filter((news) -> !news.isModerationAdmin())
                .toList().size();
    }

    public List<Post> allNewsForUser() {
        return postRepository.findAll(Sort.by(sortByValue))
                .stream()
                .filter(Post::isModerationAdmin)
                .toList();
    }

    @Override
    public boolean save(Post post) {
        if (moderationByGPT(post)) {
            postRepository.save(post);
            return true;
        }
        return false;
    }

    @Override
    public boolean moderationByAdmin(Long id) {
        Post postFromDB = findNewsById(id);
        if (postFromDB != null) {
            postFromDB.setModerationAdmin(true);
            postRepository.save(postFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean moderationByGPT(Post post) {
        int assessmentAggression = Integer.parseInt(chatGptService.getResponse(post.getContent()));
        post.setAssessmentAggression(assessmentAggression);
        if (assessmentAggression < 6) {
            post.setModerationGPT(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNews(Long id) {
        if (postRepository.findById(id).isEmpty()) {
            return false;
        }
        postRepository.deleteById(id);
        return true;
    }
}
