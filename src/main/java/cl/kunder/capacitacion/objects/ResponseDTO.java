package cl.kunder.capacitacion.objects;

/**
 * DTO to be used as a response of methods GET and POST
 */
public class ResponseDTO {
    private String code;
    private String description;
    private String data;

    public ResponseDTO() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
