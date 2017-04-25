package pl.comp.datalog.dto;

/**
 * Created by Damian Ratajczak
 */
public class QueryDTO {

    private String value;
    private String result;

    public QueryDTO(String value, String result) {
        this.value = value;
        this.result = result;
    }

    public QueryDTO() {
    }

    /**
     * Sets new value.
     *
     * @param value New value of value.
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets result.
     *
     * @return Value of result.
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets new result.
     *
     * @param result New value of result.
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Gets value.
     *
     * @return Value of value.
     */
    public String getValue() {
        return value;
    }
}
