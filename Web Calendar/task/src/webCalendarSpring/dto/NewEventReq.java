package webCalendarSpring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewEventReq(
        @NotBlank String event, @NotNull LocalDate date) {

}
