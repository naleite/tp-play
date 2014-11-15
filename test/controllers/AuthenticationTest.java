package controllers;

import org.junit.Test;
import play.test.WithBrowser;

import static org.fest.assertions.Assertions.assertThat;

public class AuthenticationTest extends WithBrowser {

    @Test
    public void signingUser() {
        // Try to go to the index page
        browser.goTo(routes.Journeys.journeys().url());
        // User is redirected to the login page
        assertThat(browser.url()).isEqualTo(routes.Authentication.login().url());

        // Fill the signing in form
        browser.fill("[name=name]").with("test");
        browser.fill("[name=password]").with("test");
        browser.submit("form");
        // User is logged in and redirected to the index page

        assertThat(browser.url()).isEqualTo("/");

        // Logout

        browser.goTo(routes.Authentication.logout().url());
        assertThat(browser.url()).isEqualTo("/auth/logout");


    }


    @Test
    public void invalidLogin() {
        browser.goTo(routes.Journeys.journeys().url());
        assertThat(browser.url()).isEqualTo(routes.Authentication.login().url());
        // Submit an empty form

        browser.submit("form");
        // Validation error
        assertThat(browser.pageSource()).contains("bad form");

        // Submit an invalid form
        browser.goTo(routes.Journeys.journeys().url());
        browser.fill("[name=name]").with("tes");
        browser.fill("[name=password]").with("tddddd");
        browser.submit("form");
        assertThat(browser.pageSource()).contains("bad user");
    }

}
