package app.runfinder.domain.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record RunGroupGetDTO(
    long runGroupId,
    String runGroupName,
    LocalDate runStartDate,
    LocalTime runStarTime,
    String startAddress,
    String zipcode,
    String city,
    Long addedByAppUserId
) {}
