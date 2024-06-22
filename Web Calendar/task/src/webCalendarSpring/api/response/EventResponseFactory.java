package webCalendarSpring.api.response;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EventResponseFactory {

    ResponseEntity<List<EventInfoWithIdResponse>> create(List<EventInfoWithIdResponse> events,
                                                         boolean shouldReturn204);

    ResponseEntity<EventInfoResponse> create(EventInfoResponse event);

    ResponseEntity<EventInfoWithIdResponse> create(EventInfoWithIdResponse event);

    ResponseEntity<String> create(String error, int status);

}
