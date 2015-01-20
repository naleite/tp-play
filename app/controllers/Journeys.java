package controllers;


import play.Routes;
import play.data.validation.Constraints;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;
import play.mvc.Security;
import scala.NotImplementedError;
import services.JourneysService;
import services.JourneysServiceHTTP;
import services.models.Attendee;
import services.models.Journey;
import views.html.list_attendees;


import java.util.function.Function;

import static play.data.Form.form;

/**
 * Controller grouping actions related to the journeys service.
 */
@Security.Authenticated(MyAuthenticator.class)
public class Journeys extends Controller {

    /**
     * The entry point to the service implementation.
     */
    static JourneysService service = new JourneysServiceHTTP(play.libs.ws.WS.client());

    static F.Promise<Attendee> currentAttendee;
    /**
     * Show all visible journeys
     */
    public static F.Promise<Result> journeys() {
        return service.allJourneys().
                map(journeys -> ok(views.html.index.render(Authentication.username(), journeys)));
    }

    public static F.Promise<Result> deleteAttendees(Long id) {
        return service.deleteAttendees(id)
        .map(atendees -> ok(list_attendees.render(atendees)));

    }

    /**
     * Show all visible attendees in BDD
     */
    public static F.Promise<Result> listAttendees() {
        return service.allAttendees().
                map(atendees -> ok(list_attendees.render( atendees)));
    }

    /**
     * Show all visible attendees in BDD
     */
    public static F.Promise<Result> getAttendee(Long id) {
        currentAttendee = service.getAttendee(id);
        return currentAttendee.map(attendee -> ok(views.html.detail_personne.render(attendee, form(AttendeeCtrl.AttendeeCreateJourney.class))))
                ;
    }

    /**
     * Show the details of the journey with the given id
     */
    public static F.Promise<Result> journey(Long id) {

        return service.getJourney(id).
                map(journeys -> ok(views.html.showjourney.render(journeys)));

    }

    /**
     * Attend to the journey with the given id
     */
    public static F.Promise<Result> attend(Long journeyId) {
        throw new NotImplementedError();
    }

    /**
     * Attend to a journey by joining a driver already attending
     */
    public static F.Promise<Result> join(Long journeyId) {
        throw new NotImplementedError();
    }

    /**
     * Show a page displaying journey’s attendee
     */
    public static F.Promise<Result> observe(Long journeyId) {
        return withJourney(journeyId, journey -> ok(views.html.observe.render(journeyId)));
    }

    /**
     * Stream of participation notifications for the journey with the given id
     */
    public static Result attendees(Long journeyId) {
        throw new NotImplementedError();
    }

    /**
     * JavaScript resource defining a reverse router allowing us to compute URLs of the application from client-side
     */
    public static Result javaScriptRouter() {
        //return ok(Routes.javascriptRouter("routes", routes.javascript.Journeys.attendees()));
        return ok(Routes.javascriptRouter("routes", routes.javascript.Journeys.attendees()));
    }

    /**
     * Form model for attending as a driver
     */
    public static class Attend {
        @Constraints.Required
        public Integer availableSeats;
    }

    /**
     * Form model for attending by joining a driver
     */
    public static class Join {
        @Constraints.Required
        public Long driverId;
    }

     static F.Promise<Result> withJourney(Long id, Function<Journey, Result> f) {
        return service.allJourneys().map(journeys -> {
            return journeys.stream().
                filter(journey -> journey.id.equals(id)).
                findFirst().
                map(f::apply).
                orElseGet(Results::notFound);
        });
    }

}
