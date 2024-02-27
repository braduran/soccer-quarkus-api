package soccer.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;
import soccer.api.dto.MatchCreateDto;
import soccer.api.dto.MatchResponseDto;
import soccer.api.dto.MatchUpdateDto;
import soccer.api.dto.MatchesPagination;
import soccer.api.repository.Match;
import soccer.api.repository.MatchState;

import java.util.List;

@Path("/match")
public class MatcController {

    @Inject
    private MatchService service;


    @Path("/create")
    @Transactional
    @POST
    public Response create(MatchCreateDto dto){
        MatchResponseDto matchOut = service.create(dto);
        return Response.ok(matchOut).build();
    }

    @Path("/update")
    @Transactional
    @PUT
    public Response update(MatchUpdateDto dto){
        MatchResponseDto matchOut = service.update(dto);
        return Response.ok(matchOut).build();
    }


    @Path("/list")
    @GET
    public Response list(){
        List<MatchResponseDto> matchOut = service.list();
        return Response.ok(matchOut).build();
    }

    @Path("/state")
    @GET
    public Response matchesState(@QueryParam("state") MatchState state){
        List<MatchResponseDto> matchOut = service.byState(state.toString());
        return Response.ok(matchOut).build();
    }

    @Path("/team")
    @GET
    public Response historyMatchesTeam(@QueryParam("team") String team,
                                       @QueryParam("page") @DefaultValue("1") int page){
        MatchesPagination<Match> matchOut = service.team(page, team);
        return Response.ok(matchOut).build();
    }
}
