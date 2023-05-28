package spring.service;

import spring.model.Post;

import java.util.List;

public interface PostService {

    int getCountNoModerationToAdmin();

    Post findNewsById(Long newsId);

    List<Post> allNewsForAdmin();

    List<Post> allNewsForUser();

    boolean save(Post post);

    boolean moderationByAdmin(Long id);

    boolean deleteNews(Long id);
}
