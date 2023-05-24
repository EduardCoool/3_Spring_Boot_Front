package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import spring.chatGpt.ChatGptService;
import spring.dao.NewsRepository;
import spring.model.News;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService, ChatGPTModerationService {
    @Autowired
    NewsRepository newsRepository;

    @Autowired
    ChatGptService chatGptService;

    @Value("${sortByValue}")
    private String sortByValue;

    @Override
    public News findNewsById(Long newsId) {
        Optional<News> newsFromDb = newsRepository.findById(newsId);
        return newsFromDb.orElse(new News());
    }

    @Override
    public List<News> allNewsForAdmin() {
        return newsRepository.findAll(Sort.by(sortByValue))
                .stream()
                .filter((news) -> !news.isModerationAdmin())
                .toList();
    }

    public List<News> allNewsForUser() {
        return newsRepository.findAll(Sort.by(sortByValue))
                .stream()
                .filter(News::isModerationAdmin)
                .toList();
    }

    @Override
    public boolean save(News news) {
        if (moderationByGPT(news)) {
            newsRepository.save(news);
            return true;
        }
        return false;
    }

    @Override
    public boolean moderationByAdmin(Long id) {
        News newsFromDB = findNewsById(id);
        if (newsFromDB != null) {
            newsFromDB.setModerationAdmin(true);
            newsRepository.save(newsFromDB);
            return true;
        }
        return false;
    }

    @Override
    public boolean moderationByGPT(News news) {
        int assessmentAggression = Integer.parseInt(chatGptService.getResponse(news.getContent()));
        news.setAssessmentAggression(assessmentAggression);
        if (assessmentAggression < 6) {
            news.setModerationGPT(true);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteNews(Long id) {
        if (newsRepository.findById(id).isEmpty()) {
            return false;
        }
        newsRepository.deleteById(id);
        return true;
    }
}
