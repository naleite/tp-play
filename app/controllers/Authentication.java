package controllers;

import play.data.Form;
import play.data.validation.ValidationError;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;

import static play.data.Form.form;

/**
 * Controller grouping actions related to authentication
 */
public class Authentication extends Controller {
    /**
     * Show the authentication form
     */
    public static Result login() {
        if(session().isEmpty()) {
            return ok(views.html.login.render(form(Login.class)));
        }
        else
            return redirect(routes.Journeys.journeys());
    }

    /**
     * Handle the authentication form submission.
     *
     * If the submitted data is invalid (e.g. the user password is wrong), this action must return a 400 status code
     * and show again the form with its errors.
     *
     * Otherwise, the user must be authenticated (his user id should be stored into his session) and redirected to the index page.
     */
    public static Result authenticate() {
        Form<Login> formLogin = form(Login.class).bindFromRequest();
        if (formLogin.hasErrors())
        {
            return badRequest(views.html.login.render(formLogin));
        }
        else
        {
            Login newLogin = formLogin.get();

            if (newLogin.name.equals("test")  && newLogin.password.equals("test")) {
                //return ok("hello" + newLogin.name);
                session().clear();
                session("username",newLogin.name);
                return redirect(routes.Journeys.journeys());
            }
            else return badRequest(views.html.login.render(formLogin));
        }


        // TODO:
        // - Read the data of the form submission
        // - If data is valid, check that the user name and password are correct
        // - If everything is alright associate the user’s name to the "username" key in his session and redirect him to the Journeys.journeys action
        // - In case of failure, reply with a 400 status code (Bad Request) and show the form with the validation errors
    }

    /**
     * Logs out an user (remove his name from his session) and show a good bye message
     */
    public static Result logout() {
        String username=username();
        session().clear();

       // return ok("<h1>good bye "+name+"!</h1>").as("logout.html");
        return ok(views.html.logout.render(username));
    }

    /**
     * @return The current user name
     */
    public static String username() {
        return session("username");
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
    public static class Login {

        public String name;

        public String password;

        // If needed, override this method to add a “global” validation rule (i.e not related to a particular field)
        public List<ValidationError> validate() {
            boolean ifnull=true;
            List<ValidationError> l = new ArrayList<>();
            if(this.name.equals(""))
            {
                ValidationError v = new ValidationError("name", "empty username");
                l.add(v);
                ifnull=false;
            }
            if(this.password.equals(""))
            {
                ValidationError v = new ValidationError("password", "empty password");
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
