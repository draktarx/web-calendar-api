package webCalendarSpring.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String event;

    @Column(nullable = false)
    private LocalDate date;

    public Event() {
    }

    public Event(String event, LocalDate date) {
        this.event = event;
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

}
