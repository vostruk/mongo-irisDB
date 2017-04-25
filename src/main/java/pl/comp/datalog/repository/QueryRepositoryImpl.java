package pl.comp.datalog.repository;

import org.springframework.stereotype.Service;
import pl.comp.datalog.dto.QueryDTO;

/**
 * Created by Damian Ratajczak
 */
@Service
public class QueryRepositoryImpl implements QueryRepository {

    @Override
    public QueryDTO resolve(QueryDTO query) {
        query.setResult("Resultat zapytania");
        return query;
    }
}
