package webCalendarSpring.service;

import webCalendarSpring.api.response.EventInfoResponse;
import webCalendarSpring.api.response.EventInfoWithIdResponse;
import webCalendarSpring.dto.NewEventReq;

import java.time.LocalDate;
import java.util.List;

public interface EventService {

    EventInfoResponse addEvent(NewEventReq newEvent);

    EventInfoWithIdResponse deleteEvent(int id);

    List<EventInfoWithIdResponse> deleteEvents();

    List<EventInfoWithIdResponse> deleteAllById(List<Integer> ids);

    EventInfoWithIdResponse findById(int id);

    List<EventInfoWithIdResponse> getAllEventsBetween(LocalDate startTime, LocalDate endTime);

    List<EventInfoWithIdResponse> getAllEvents();

    List<EventInfoWithIdResponse> getTodayEvents();

}
