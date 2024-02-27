package soccer.api.dto;

import java.time.LocalDate;

public record MatchCreateDto(String local,
                             String visitor,
                             String stadium,
                             String competition,
                             Integer attendeesNumber,
                             LocalDate date) {
}
