package services;

import adapters.ZeroMQObservableAdapter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.libs.F;
import play.libs.Json;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import rx.Observable;
import scala.NotImplementedError;
import services.models.Attendee;
import services.models.Journey;

import java.util.List;
import java.util.Map;

/**
 * JourneyService implementation that delegates to a third party JSON Web service
 */
public class JourneysServiceHTTP implements JourneysService {

    /** The HTTP client used to communicate with the Web service */
    final WSClient client;
    /** A JSON object mapper to handle JSON serialization/deserialization */
    final ObjectMapper mapper;


    /** The Web service base URL */
    final static String API_URL = "http://localhost:8080";

    public JourneysServiceHTTP(WSClient client) {
        this.client = client;
        mapper = new ObjectMapper();
    }

    @Override
    public F.Promise<List<Journey>> allJourneys() {
        // Example of implementation performing a GET request to the `/journeys` endpoint and interpreting the result as
        // a list of Journey values.
        return client.url(API_URL + "/rest/ev/")
                .get()
                .map(r -> mapper.readValue(r.getBody(), new TypeReference<List<Journey>>() {}));

    }

    @Override
    public F.Promise<List<Attendee>> allAttendees(){
        return client.url(API_URL + "/rest/ev/personne")
                .get()
                .map(r -> mapper.readValue(r.getBody(), new TypeReference<List<Attendee>>() {}));
    }

    @Override
    public F.Promise<Journey> getJourney(Long id) {
        try{
           return client.url(API_URL + "/rest/ev/getevent/"+id)
                    .get()
                    .map(r -> mapper.readValue(r.getBody(), new TypeReference<Journey>() {}));
        }catch(Exception e){ }
        return null;

    }


    @Override
    public F.Promise<Attendee> getAttendee(Long id) {
        try{
            return client.url(API_URL + "/rest/ev/personne/id/"+id)
                    .get()
                    .map(r -> mapper.readValue(r.getBody(), new TypeReference<Attendee>() {}));
        }catch(Exception e){ }
        return null;

    }

    @Override
    public void deleteAttendees(Long idAttendee)
    {
        JsonNode json = Json.newObject()
                .put("id", idAttendee.toString());
        System.out.println("delete called ");
        client.url(API_URL + "/rest/ev/delete/personne/")
                .setQueryParameter("id",  idAttendee.toString()).delete();
    }

    @Override
    public void create_ev(String idAttendee, String depart, String dest)
    {
        JsonNode json = Json.newObject()
                .put("id", idAttendee)
                .put("depart", depart)
                .put("dest", dest);

        client.url(API_URL + "/rest/ev/propose//")
                .setQueryParameter("id", idAttendee)
                .setQueryParameter("depart", depart)
                .setQueryParameter("dest", dest);
    }


    @Override
    public F.Promise<Boolean> join(Long journeyId, Long driverId, String attendeeName) {
        throw new NotImplementedError();
    }

    @Override
    public F.Promise<Boolean> attend(Long journeyId, String attendeeName, Integer availableSeats) {
        throw new NotImplementedError();
    }

    @Override
    public Observable<Attendee> attendees(Long journeyId) {
        ZeroMQObservableAdapter k;
        throw new NotImplementedError();
    }

}
