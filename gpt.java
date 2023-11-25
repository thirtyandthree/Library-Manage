import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class gpt {
    private static final String API_KEY = "sk-bCnBAlsepSdqg8DaM6RCGKAk4DrF46rq9tNy6tv32xJvJk6S";
    private static final String API_URL = "https://api.chatanywhere.com.cn/v1/chat/completions";

    public  String chatgpt(String content) {
        String userMessage = content;
        String response = gpt35ApiStream(userMessage);
        String assistantResponse = extractAssistantResponse(response);
        return assistantResponse;
    }

    private static String gpt35ApiStream(String message) {
        HttpClient client = HttpClient.newHttpClient();

        String requestBody = "{\"model\": \"gpt-3.5-turbo\", \"messages\": [{\"role\": \"user\", \"content\": \"" + message + "\"}]}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "错误: " + e.getMessage();
        }
    }

    public static String extractAssistantResponse(String jsonResponse) {
        Pattern pattern = Pattern.compile("\"content\":\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(jsonResponse);

        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "无法提取内容。";
        }
    }

}
