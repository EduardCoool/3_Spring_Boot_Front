package spring.service.chatGpt;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ChatGptApiFeignClient {

    @Headers({
            "Authorization: Bearer {API_KEY}",
            "Content-Type: application/json"
    })
    @RequestLine("POST")
    String getResponse(@Param("API_KEY") String API_KEY, String requestBody);
}