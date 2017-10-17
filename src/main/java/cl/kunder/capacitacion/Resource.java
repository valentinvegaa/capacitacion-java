package cl.kunder.capacitacion;

import cl.kunder.capacitacion.objects.RequestDTO;
import cl.kunder.capacitacion.objects.ResponseDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Path("resource")
public class Resource {
    /**
     * @param code receives the code used to generate response
     * @param description receives a brief description of the error
     * @param data receives the data, transformed by the method or the original data depending on success/error.
     * @return the response created.
     */
    private ResponseDTO createResponse(String code, String description, String data) {
        ResponseDTO rp = new ResponseDTO();
        rp.setCode(code);
        rp.setDescription(description);
        rp.setData(data);
        return rp;
    }

    /**
     *
     * @param hour is the string used by user to get
     * @return the string in UTC ISO DATE format
     * @throws ParseException is thrown when String hour doesnt match the format hh:mm:ss
     */
    private String formatDate(String hour) throws ParseException {
        String[] splitedHour = hour.split(":");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(splitedHour[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(splitedHour[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(splitedHour[2]));

        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd'T'hh:mm:ss.SSXX");
        String resp;
        resp = formatter.format(calendar.getTime());
        return resp;
    }

    /**
     *
     * @param value is the hour input value in format hh:mm:ss
     * @return a Response with the code of the operation, a description of success(OK) or Error and the data in case
     * of success
     */
    @Path("time")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTime(@QueryParam("value") String value) {
        ResponseDTO rp;
        int status;
        String responseDate = null;

        try {
            responseDate = formatDate(value);
            rp = createResponse("00", "OK", responseDate);
            status = 200;
        } catch (ParseException | ArrayIndexOutOfBoundsException e) {
            rp = createResponse("400", "Error: Bad request", responseDate);
            status = 400;
        } catch (Exception e) {
            rp = createResponse("500", "Error: Server error", responseDate);
            status = 400;
        }

        return Response.status(status).entity(rp).build();
    }

    /**
     *
     * @param request is data in Json format
     * @return a Response with the code of the operation, a description of success(OK) or Error and the data in case
     * of success
     */
    @Path("word")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response toUpper(RequestDTO request) {
        ResponseDTO rp = new ResponseDTO();
        int status = 0;

        try {
            String word = request.getData();
            if (word == null) {
                rp = createResponse("400", "Error: Null it's not a word", null);
                status = 400;
            } else if ("Error:Not a word!".equals(word)) {
                rp = createResponse("400", "Error:Not a word!", request.getData());
                status = 400;
            } else if (word.length() != 4) {
                rp = createResponse("400", "Error: Wrong length", word);
                status = 400;
            } else {
                rp = createResponse("00", "OK", word.toUpperCase());
                status = 200;
            }
        } catch (Exception e) {
            rp = createResponse("500", "Server error", "");
            status = 500;
        }
        return Response.status(status).entity(rp).build();
    }
}
