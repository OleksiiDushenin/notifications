package dushenin.oleksii.notifications;

import dushenin.oleksii.notifications.web.Notification;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.TimeUnit;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON;

public class NotificationsApplicationClient {

    public static void main(String[] args) throws InterruptedException {
        WebClient client = WebClient.create("http://localhost:8080");

        client.get()
                .uri("/notifications")
                .accept(APPLICATION_STREAM_JSON)
                .retrieve()
                .bodyToFlux(Notification.class)
                .map(Notification::toString)
                .subscribe(System.out::println);

        TimeUnit.MINUTES.sleep(2L);
    }

}
