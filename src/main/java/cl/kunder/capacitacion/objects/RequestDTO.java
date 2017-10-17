package cl.kunder.capacitacion.objects;

/**
 * DTO to be used in POST method
 */
public class RequestDTO {
    private String data;

    public RequestDTO() {
    }

    /**
     *
     * @return Error:Not a word! in case of parseInt success or string data in other case
     */
    public String getData() {
        try {
            int myint = Integer.parseInt(data);
            return "Error:Not a word!";
        } catch (Exception e) {
            return data;
        }
    }

    public void setData(String data) {
        this.data = data;
    }
}
