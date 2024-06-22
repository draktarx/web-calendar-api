package webCalendarSpring.api.response;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventResponseFactoryImpl implements EventResponseFactory {

    @Override
    public ResponseEntity<List<EventInfoWithIdResponse>> create(List<EventInfoWithIdResponse> events, boolean shouldReturn204) {
        if (shouldReturn204 && events.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(events);
    }

    @Override
    public ResponseEntity<EventInfoResponse> create(EventInfoResponse event) {
        return ResponseEntity.ok(event);
    }

    @Override
    public ResponseEntity<EventInfoWithIdResponse> create(EventInfoWithIdResponse event) {
        return ResponseEntity.ok(event);
    }

    @Override
    public ResponseEntity<String> create(String error, int status) {
        return ResponseEntity.status(status).body(error);
    }

}
