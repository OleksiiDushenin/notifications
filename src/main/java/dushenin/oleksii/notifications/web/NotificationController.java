package dushenin.oleksii.notifications.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

@RestController
@AllArgsConstructor
@RequestMapping(value = "notifications")
public class NotificationController {

    @GetMapping(produces = APPLICATION_STREAM_JSON_VALUE)
    public Flux<Notification> getAllNotifications() {
        return Flux
                .interval(Duration.ofSeconds(5L))
                .map(id ->
                        Notification.builder()
                                .id(id)
                                .event(String.format("Notification with id '%d' created.", id))
                                .creationDate(Instant.now())
                                .build()
                );
    }

}
