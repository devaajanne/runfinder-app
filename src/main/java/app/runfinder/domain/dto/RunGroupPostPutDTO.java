package app.runfinder.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RunGroupPostPutDTO(
        @Size(min = 5, max = 50, message = "Run group name must be between 5 and 50 characters long") String runGroupName,
        @NotNull(message = "Run group must have a start date") LocalDate runStartDate,
        @NotNull(message = "Run group must have a start time") LocalTime runStartTime,
        @NotEmpty(message = "Run group must have a start address") String startAddress,
        @Size(min = 5, max = 5, message = "Zipcode must be exactly 5 characters long") String zipcode) {
}