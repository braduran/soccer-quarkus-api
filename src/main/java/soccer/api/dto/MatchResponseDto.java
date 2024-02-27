package soccer.api.dto;

import soccer.api.repository.MatchState;

import java.time.LocalDate;

public record MatchResponseDto(String local,
                               String visitor,
                               String stadium,
                               String competition,
                               Integer attendeesNumber,
                               LocalDate date,
                               Integer minute,
                               String state) {
}
