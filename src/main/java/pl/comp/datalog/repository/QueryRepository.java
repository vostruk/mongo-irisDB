package pl.comp.datalog.repository;


import pl.comp.datalog.dto.QueryDTO;

/**
 * Created by Damian Ratajczak
 */
public interface QueryRepository {

    QueryDTO resolve(QueryDTO query);
}
