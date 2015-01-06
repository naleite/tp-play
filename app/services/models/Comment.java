package services.models;

/**
 * Created by josian on 06/01/15.
 */
public class Comment {

    public  Long id;
    public  String value;
    public Attendee personne;

    //utilisé dans JOURNEY
    public Comment(Long id, String value, Attendee personne)
    {
        this.id=id;
        this.value=value;
        this.personne = personne;
    }

    public Comment()
    {

    }
}
