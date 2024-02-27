package soccer.api;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import soccer.api.dto.MatchCreateDto;
import soccer.api.dto.MatchMapperImpl;
import soccer.api.dto.MatchResponseDto;
import soccer.api.dto.MatchUpdateDto;
import soccer.api.dto.MatchesPagination;
import soccer.api.repository.Match;
import soccer.api.repository.MatchRepository;
import soccer.api.repository.MatchState;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MatchService {

    private final MatchRepository repository;
    private final MatchMapperImpl mapper;

    @Inject
    public MatchService(MatchRepository repository, MatchMapperImpl mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public MatchResponseDto create(MatchCreateDto dto){
        var matchQuery = getMatchCreate(dto.date(), dto.local(), dto.visitor());

        if(matchQuery != null){
            String message = String.format("Match already exist. local: %s, visitor: %s, date: %s",
                    dto.local(), dto.visitor(), dto.date());
            throw new IllegalArgumentException(message);
        }

        var match = mapper.toMatch(dto);
        repository.persist(match);
        return mapper.toMatchResponse(match);
    }

    public MatchResponseDto update(MatchUpdateDto dto){
        var matchQuery = getMatchUpdate(dto.date(), dto.local(), dto.visitor());
        if(matchQuery == null){
            String message = String.format("Match does not exist. local: %s, visitor: %s, date: %s",
                    dto.local(), dto.visitor(), dto.date());
            throw new IllegalArgumentException(message);
        }

        if(MatchState.END.toString().equals(matchQuery.getState()) ||
           MatchState.SUSPENDED.toString().equals(matchQuery.getState())){
            throw new IllegalArgumentException("It is not possible to update a suspended or finished match");
        }

        matchQuery.setLocalScore(dto.localScore());
        matchQuery.setVisitorScore(dto.visitorScore());
        matchQuery.setMinutes(dto.minutes());
        matchQuery.setState(dto.state().toString());

        if(dto.minutes() <= 0 || dto.minutes() > 120){ // 120 for extra time
            String message = String.format("Wrong minutes: %s min", dto.minutes());
            throw new IllegalArgumentException(message);
        }

        repository.persist(matchQuery);
        return mapper.toMatchResponse(matchQuery);
    }

    public List<MatchResponseDto> list(){
        return repository.listAll()
                .stream()
                .map(mapper::toMatchResponse)
                .collect(Collectors.toList());
    }

    public List<MatchResponseDto> byState(String state){
        var query = "state = ?1";
        return repository.list(query, state)
                .stream()
                .map(mapper::toMatchResponse)
                .collect(Collectors.toList());
    }

    public MatchesPagination<Match> team(int page, String team){
        Page p = new Page(page - 1, 5);

        var query = "local = ?1 OR visitor = ?1";
        Sort order = Sort.by("date", Sort.Direction.Descending);
        PanacheQuery<Match> result = repository.find(query, order, team).page(p);
        return new MatchesPagination<>(result);
    }

    private Match getMatchCreate(LocalDate date, String local, String visitor){
        var query = "date = ?1 AND (local = ?2 OR visitor = ?2 OR local = ?3 OR visitor = ?3)";
        return repository.find(query, date, local, visitor)
                .firstResult();
    }

    private Match getMatchUpdate(LocalDate date, String local, String visitor){
        var query = "date = ?1 AND local = ?2 AND visitor = ?3";
        return repository.find(query, date, local, visitor)
                .firstResult();
    }
}
