package soccer.api.dto;

import io.quarkus.hibernate.orm.panache.PanacheQuery;

import java.util.List;

public record MatchesPagination<M>(int page, int totalPages, List<M> data) {

    public MatchesPagination(PanacheQuery<M> query){
        this(query.page().index + 1, query.pageCount(), query.list());
    }
}
