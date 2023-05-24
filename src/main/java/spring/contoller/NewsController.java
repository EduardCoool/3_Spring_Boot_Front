package spring.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import spring.model.News;
import spring.service.NewsService;

import java.util.List;

@RestController

public class NewsController {
    @Autowired
    NewsService newsService;

    @GetMapping(value = "/admin/news/view")
    public List<News> viewNewsForAdmin() {
        return newsService.allNewsForAdmin();
    }

    @GetMapping(value = "/news/view")
    public List<News> viewNewsForUser() {
        return newsService.allNewsForUser();
    }

    @PostMapping(value = "/news/save")
    public boolean addNews(@RequestBody News news) {
        return newsService.save(news);
    }

    @PutMapping(value = "/admin/news/moderation/{id}")
    public boolean moderationByAdmin(@PathVariable("id") Long id) {
        return newsService.moderationByAdmin(id);
    }

    @DeleteMapping(value = "/admin/news/delete/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return newsService.deleteNews(id);
    }
}
