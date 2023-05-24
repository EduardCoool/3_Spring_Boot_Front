package spring.service;

import spring.model.News;

import java.util.List;

public interface NewsService {

    News findNewsById(Long newsId);

    List<News> allNewsForAdmin();

    List<News> allNewsForUser();

    boolean save(News news);

    boolean moderationByAdmin(Long id);

    boolean deleteNews(Long id);
}
