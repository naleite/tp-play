package controllers;

import play.data.Form;
import play.data.validation.ValidationError;
import play.libs.F;
import play.mvc.Controller;
import play.mvc.Result;
import scala.NotImplementedError;
import services.models.Attendee;
import services.models.Journey;
import views.html.detail_personne;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

/**
 * Created by josian on 09/01/15.
 */
public class AttendeeCtrl extends Controller {




    public static Result  create_evenement() {

        Form<AttendeeCreateJourney> formLogin = form(AttendeeCreateJourney.class).bindFromRequest();
        if (formLogin.hasErrors())
        {
            System.out.println("Bad in class Foo trying to submit my form: " + formLogin.errorsAsJson());
            return badRequest("excepting some data");
            //return ok("<h1> does not work!</h1>").as("echec.html");
        }
        else
        {
            AttendeeCreateJourney requete_create_ev = formLogin.get();
            Journeys.service.create_ev(requete_create_ev.id, requete_create_ev.depart, requete_create_ev.destination );
            return redirect(routes.Journeys.journeys());
        }
    }
    /**
     * Map the data of the login form submission.
     *
     * Example of use:
     *
     * <pre>
     *     Form<Login> submission = form(Login.class).bindFromRequest();
     * </pre>
     */
    public static class AttendeeCreateJourney {

        public String id; //id  du participant qui crée l'evenement.

        public String depart;

        public String destination;

       //public Attendee attendee ;

        // If needed, override this method to add a “global” validation rule (i.e not related to a particular field)

        public List<ValidationError> validate() {
            boolean ifnull=true;
            List<ValidationError> l = new ArrayList<>();
            if(this.depart.equals(""))
            {
                ValidationError v = new ValidationError("depart", "le champ depart ne doit pas être vide");
                l.add(v);
                ifnull=false;
            }
            if(this.destination.equals(""))
            {
                ValidationError v = new ValidationError("destination", "le champ destination ne doit pas être vide");
                l.add(v);
                ifnull=false;
            }
            if (ifnull)
            {
                return null;
            }
            else
            {
                return l;
            }

        }

    }
}
