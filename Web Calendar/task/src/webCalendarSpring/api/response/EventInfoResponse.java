package webCalendarSpring.api.response;

import webCalendarSpring.entity.Event;

import java.time.format.DateTimeFormatter;

public record EventInfoResponse(String message, String event, String date) {

    public EventInfoResponse(Event event) {
        this("The event has been added!",
             event.getEvent(),
             event.getDate().format(DateTimeFormatter.ISO_DATE));
    }

}
