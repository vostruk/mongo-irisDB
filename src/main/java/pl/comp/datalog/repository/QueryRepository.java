package pl.comp.datalog.repository;


import pl.comp.datalog.model.Query;

/**
 * Created by Damian Ratajczak
 */
public interface QueryRepository {

    Query resolve(Query query);
}
