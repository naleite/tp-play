package services.models;

/**
 * Created by josian on 06/01/15.
 */
public class Comment {

    public final Long id;
    public final String value;
    public Attendee personne;

    //utilisé dans JOURNEY
    Comment(Long id, String value, Attendee personne)
    {
        this.id=id;
        this.value=value;
        this.personne = personne;
    }
}
