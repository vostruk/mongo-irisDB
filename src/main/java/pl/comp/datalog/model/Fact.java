package pl.comp.datalog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.comp.datalog.dto.FactDTO;

/**
 * Created by Damian Ratajczak
 */
@Document(collection = "fact")
public class Fact {

    @Id
    private String id;
    private String value;

    public Fact(String id, String value) {
        this.id = id;
        this.value = value;
    }

    public Fact() {
    }

    public Fact(FactDTO factDTO) {
        this.id = factDTO.getId();
        this.value = factDTO.getValue();
    }

    /**
     * Gets id.
     *
     * @return Value of id.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets value.
     *
     * @return Value of value.
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets new id.
     *
     * @param id New value of id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets new value.
     *
     * @param value New value of value.
     */
    public void setValue(String value) {
        this.value = value;
    }
}
