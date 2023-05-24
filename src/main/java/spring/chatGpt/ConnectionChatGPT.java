package spring.chatGpt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class ConnectionChatGPT {

    @Value("${URL}")
    private String URL;

    @Value("${API_KEY}")
    private String API_KEY;

    public ConnectionChatGPT() {
    }

    public HttpURLConnection getConnectionChatGpt() {

        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) new URL(URL).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
