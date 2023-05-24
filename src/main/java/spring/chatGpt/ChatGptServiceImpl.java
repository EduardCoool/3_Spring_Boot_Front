package spring.chatGpt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.Optional;

@Component
public class ChatGptServiceImpl implements ChatGptService {

    String PREFIX = "Оцени агрессивность текста,отвечая только цифрами, от 0 до 10:";

    @Autowired
    ConnectionChatGPT connectionChatGPT;

    @Autowired
    JsonChatGPT jsonChatGPT;

    @Override
    public String getResponse(String message) {
        HttpURLConnection connection = connectionChatGPT.getConnectionChatGpt();
        try {
            String output = null;
            connection.getOutputStream().write(jsonChatGPT.getJsonRequest(PREFIX + message).toString().getBytes());
            Optional<String> result = new BufferedReader(new InputStreamReader(connection.getInputStream()))
                    .lines()
                    .reduce((a, b) -> a + b);
            if (result.isPresent()) {
                output = result.get();
            }

            return jsonChatGPT.getStringFromJsonResponse(output);

        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
