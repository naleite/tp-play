package services.models;

import java.util.List;

/**
 * A journey is identified by `id`, it has a `name` and a list of attending people `attendees`.
 */
public class Journey {
    public Long id;
    //public final String name;
    public String dest;
    public String depart;
    public String nbPersonRest;
    public Attendee conducteur;
    public String dateDeDepart;
    public List<Attendee> attendees;

   /** public Journey(Long id, String dest, String depart, List<Attendee> attendees) {
        this.id = id;
        this.dest = dest;
        this.depart=depart;
       // name= dest+" "+depart;
        this.attendees = attendees;
    }**/
}
