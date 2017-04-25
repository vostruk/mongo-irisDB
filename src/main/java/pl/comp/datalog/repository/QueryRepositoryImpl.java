package pl.comp.datalog.repository;

import org.springframework.stereotype.Service;
import pl.comp.datalog.model.Query;

/**
 * Created by Damian Ratajczak
 */
@Service
public class QueryRepositoryImpl implements QueryRepository {

    @Override
    public Query resolve(Query query) {
        query.setResult("Resultat zapytania");
        return query;
    }
}
