package soccer.api.dto;

import soccer.api.repository.MatchState;

import java.time.LocalDate;

public record MatchUpdateDto(String local,
                             String visitor,
                             Integer localScore,
                             Integer visitorScore,
                             LocalDate date,
                             Integer minutes,
                             MatchState state) {
}
