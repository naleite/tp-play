package controllers;

/**
 * Created by naleite on 15/11/14.
 */
public class MyAuthenticator extends play.mvc.Security.Authenticator {

    @Override
    public play.mvc.Result onUnauthorized(play.mvc.Http.Context context){
        return redirect(routes.Authentication.login());

    }
}
