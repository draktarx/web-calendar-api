package webCalendarSpring.api;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import webCalendarSpring.api.exception.DeletingEventNotFoundException;
import webCalendarSpring.api.exception.EventDoesntExistException;
import webCalendarSpring.api.response.EventInfoResponse;
import webCalendarSpring.api.response.EventInfoWithIdResponse;
import webCalendarSpring.api.response.EventResponseFactory;
import webCalendarSpring.dto.NewEventReq;
import webCalendarSpring.service.impl.EventServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("event")
public class EventsRestController {

    private final EventServiceImpl eventService;

    private final EventResponseFactory eventResponseFactory;

    public EventsRestController(EventServiceImpl eventService,
                                EventResponseFactory eventResponseFactory) {
        this.eventService = eventService;
        this.eventResponseFactory = eventResponseFactory;
    }

    @PostMapping
    public ResponseEntity<EventInfoResponse> addEvent(@Valid @RequestBody NewEventReq newEvent) {
        EventInfoResponse eventInfo = eventService.addEvent(newEvent);
        return eventResponseFactory.create(eventInfo);
    }

    @DeleteMapping
    public ResponseEntity<List<EventInfoWithIdResponse>> deleteAllEvents(
            @RequestBody Optional<List<Integer>> ids) {
        List<EventInfoWithIdResponse> deletedEventsInfo;
        if (ids.isPresent()) {
            deletedEventsInfo = eventService.deleteAllById(ids.get());
        } else {
            deletedEventsInfo = eventService.deleteEvents();
        }
        return eventResponseFactory.create(deletedEventsInfo, false);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventInfoWithIdResponse> deleteEventById(@PathVariable int id) {
        EventInfoWithIdResponse deletedEventInfo = eventService.deleteEvent(id);
        return eventResponseFactory.create(deletedEventInfo);
    }

    @GetMapping
    public ResponseEntity<List<EventInfoWithIdResponse>> getAllEvents(
            @RequestParam("start_time") Optional<LocalDate> startTime,
            @RequestParam("end_time") Optional<LocalDate> endTime) {
        List<EventInfoWithIdResponse> allEvents;
        if (startTime.isEmpty() || endTime.isEmpty()) {
            allEvents = eventService.getAllEvents();
        } else {
            allEvents = eventService.getAllEventsBetween(startTime.get(), endTime.get());
        }
        return eventResponseFactory.create(allEvents, true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventInfoWithIdResponse> getEventById(@PathVariable int id) {
        EventInfoWithIdResponse event = eventService.findById(id);
        return eventResponseFactory.create(event);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleConstraintViolationException() {
        return ResponseEntity.status(400).body("");
    }

    @ExceptionHandler(EventDoesntExistException.class)
    public ResponseEntity<Map<String, String>> handleEventDoesntExistException(
            EventDoesntExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @ExceptionHandler(DeletingEventNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDeletingEventNotFoundException(
            DeletingEventNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", ex.getMessage()));
    }

    @GetMapping("/today")
    public ResponseEntity<List<EventInfoWithIdResponse>> getTodayEvents() {
        List<EventInfoWithIdResponse> todayEvents = eventService.getTodayEvents();
        return eventResponseFactory.create(todayEvents, false);
    }

}

