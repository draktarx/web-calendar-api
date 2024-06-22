package webCalendarSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import webCalendarSpring.api.response.EventInfoWithIdResponse;
import webCalendarSpring.entity.Event;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {

    List<Event> findAllByDate(LocalDate date);

    List<Event> findAllByDateBetween(LocalDate start, LocalDate end);

    List<Event> findAllByIdIn(List<Integer> ids);

}
