package services.models;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * A journey is identified by `id`, it has a `name` and a list of attending people `attendees`.
 */
public class Journey {
    public final Long id;
    public final String dest;
    public final String depart;
    public final Integer nbPersonRest;
    public final Attendee conducteur;
    public final String dateDeDepart;
    public final List<Attendee> attendees;
    public final List<Comment> listComEv;

   public Journey(Long id, String dest, String depart, String dateDeDepart,
                  List<Attendee> attendees,
                  Integer nbPersonRest,
                  List<Comment> listComEv,
                  Attendee conducteur,String d) {
        this.id = id;
        this.dest = dest;
        this.depart=depart;
        this.attendees = attendees;
        this.conducteur=conducteur;
        this.listComEv= listComEv;
        this.dateDeDepart=dateDeDepart;//pas utilisé
        this.nbPersonRest=nbPersonRest;

   }
}
