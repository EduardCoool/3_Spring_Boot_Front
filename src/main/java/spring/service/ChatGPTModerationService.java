package spring.service;

import spring.model.News;

public interface ChatGPTModerationService {
    boolean moderationByGPT(News news);
}
