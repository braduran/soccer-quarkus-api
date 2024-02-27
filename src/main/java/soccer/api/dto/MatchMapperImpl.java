package soccer.api.dto;

import jakarta.enterprise.context.RequestScoped;
import soccer.api.repository.Match;
import soccer.api.repository.MatchState;

@RequestScoped
public class MatchMapperImpl implements MatchMapper{
    @Override
    public Match toMatch(MatchCreateDto dto) {
        var match = new Match();
        match.setLocal(dto.local());
        match.setVisitor(dto.visitor());
        match.setLocalScore(0);
        match.setVisitorScore(0);
        match.setStadium(dto.stadium());
        match.setCompetition(dto.competition());
        match.setAttendeesNumber(dto.attendeesNumber());
        match.setDate(dto.date());
        match.setMinutes(0);
        match.setState(MatchState.START.toString());
        return match;
    }

    @Override
    public MatchResponseDto toMatchResponse(Match match) {
        return new MatchResponseDto(match.getLocal(),
                match.getVisitor(), match.getStadium(),
                match.getCompetition(), match.getAttendeesNumber(),
                match.getDate(), match.getMinutes(),
                match.getState());

    }
}
