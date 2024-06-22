package webCalendarSpring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webCalendarSpring.api.exception.DeletingEventNotFoundException;
import webCalendarSpring.api.exception.EventDoesntExistException;
import webCalendarSpring.api.response.EventInfoResponse;
import webCalendarSpring.api.response.EventInfoWithIdResponse;
import webCalendarSpring.dto.NewEventReq;
import webCalendarSpring.entity.Event;
import webCalendarSpring.repository.EventRepository;
import webCalendarSpring.service.EventService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventInfoResponse addEvent(NewEventReq newEvent) {
        Event savedEvent = eventRepository.save(new Event(newEvent.event(), newEvent.date()));
        return new EventInfoResponse(savedEvent);
    }

    @Override
    public EventInfoWithIdResponse deleteEvent(int id) {
        boolean eventExists = eventRepository.existsById(id);
        if (eventExists) {
            EventInfoWithIdResponse event = findById(id);
            eventRepository.deleteById(id);
            return event;
        } else {
            throw new DeletingEventNotFoundException("The event doesn't exist!");
        }
    }

    @Override
    public List<EventInfoWithIdResponse> deleteEvents() {
        List<EventInfoWithIdResponse> allEvents = getAllEvents();
        eventRepository.deleteAll();
        return allEvents;
    }

    @Override
    public EventInfoWithIdResponse findById(int id) {
        Optional<Event> eventFound = eventRepository.findById(id);
        if (eventFound.isPresent()) {
            return new EventInfoWithIdResponse(eventFound.get());
        } else {
            throw new EventDoesntExistException("The event doesn't exist!");
        }
    }

    @Override
    public List<EventInfoWithIdResponse> getAllEventsBetween(LocalDate startTime,
                                                             LocalDate endTime) {
        List<Event> events = eventRepository.findAllByDateBetween(startTime, endTime);
        return events.stream().map(EventInfoWithIdResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<EventInfoWithIdResponse> deleteAllById(List<Integer> ids) {
        List<Event> events = eventRepository.findAllByIdIn(ids);
        eventRepository.deleteAllById(ids);
        return events.stream().map(EventInfoWithIdResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<EventInfoWithIdResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventInfoWithIdResponse::new).collect(Collectors.toList());
    }

    @Override
    public List<EventInfoWithIdResponse> getTodayEvents() {
        List<Event> events = eventRepository.findAllByDate(LocalDate.now());
        return events.stream().map(EventInfoWithIdResponse::new).collect(Collectors.toList());
    }

}
