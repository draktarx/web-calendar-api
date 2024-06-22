package webCalendarSpring.api.response;

import webCalendarSpring.entity.Event;

import java.time.format.DateTimeFormatter;

public record EventInfoWithIdResponse(Integer id, String event, String date) {

    public EventInfoWithIdResponse(Event event) {
        this(event.getId(), event.getEvent(), event.getDate().format(DateTimeFormatter.ISO_DATE));
    }

}
