package soccer.api.dto;

import soccer.api.repository.Match;

public interface MatchMapper {

    Match toMatch(MatchCreateDto dto);
    MatchResponseDto toMatchResponse(Match match);
}
