package spring.service;

import spring.model.Post;

public interface ChatGPTModerationService {
    boolean moderationByGPT(Post post);
}
