package spring.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import spring.model.Post;
import spring.service.PostService;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping(value = "/admin/post/view")
    public List<Post> viewNewsForAdmin() {
        return postService.allNewsForAdmin();
    }

    @GetMapping(value = "/post/view")
    public List<Post> viewNewsForUser() {
        return postService.allNewsForUser();
    }

    @PostMapping(value = "/post/save")
    public boolean addNews(@RequestBody Post post) {
        return postService.save(post);
    }

    @PutMapping(value = "/admin/post/moderation/{id}")
    public boolean moderationByAdmin(@PathVariable("id") Long id) {
        return postService.moderationByAdmin(id);
    }

    @DeleteMapping(value = "/admin/post/delete/{id}")
    public boolean delete(@PathVariable("id") Long id) {
        return postService.deleteNews(id);
    }
}
