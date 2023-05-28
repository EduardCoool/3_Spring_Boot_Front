package spring.service.chatGpt;

import feign.Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

@Component
public class ChatGptServiceImpl implements ChatGptService {
    protected final String PREFIX = "Оцени агрессивность текста,отвечая только цифрами, от 0 до 10:";
    private final ChatGptApiFeignClient client;
    @Autowired
    private JsonChatGPT jsonChatGPT;

    @Value("${API_KEY}")
    private String API_KEY;

    public ChatGptServiceImpl(@Value("${URL}") String URL) {
        this.client = Feign.builder()
                .target(ChatGptApiFeignClient.class, URL);
    }

    @Override
    public String getResponse(String message) {
        try {
            String jsonResponse = client.getResponse(API_KEY, jsonChatGPT.getJsonRequest(PREFIX + message).toString());
            return jsonChatGPT.getStringFromJsonResponse(jsonResponse);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
