package pl.comp.datalog.repository;


import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Damian Ratajczak
 */
public interface QueryRepository {

    String resolve(String query);
}
