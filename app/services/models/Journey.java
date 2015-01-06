package services.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * A journey is identified by `id`, it has a `name` and a list of attending people `attendees`.
 */
public class Journey {

    public Long id;
    public String dest;
    public String depart;
    public  int nbPersonRest;
    public Attendee conducteur;
    public  String dateDeDepart;
    public  List<Attendee> participants;
    public  List<Comment> listComEv;


    public Journey(Long id,
                  String dest,
                  String depart,
                  String dateDeDepart,
                  int nbPersonRest,
                  List<Comment> listComEv,
                  List<Attendee> participants,
                  Attendee conducteur) {
        this.id = id;
        this.dest = dest;
        this.depart=depart;
        this.participants = participants;

        this.conducteur=conducteur;
        this.listComEv= listComEv;
        this.dateDeDepart=dateDeDepart;//pas utilisé
        this.nbPersonRest=nbPersonRest;

   }

    public Journey()
    {

    }
    /**
     * "id": 1,
     4	"dest": "Marseille",
     5	"depart": "Rennes",
     6	"dateDeDepart": 1420531791340,
     7	"nbPersonRest": 4,
     8	"conducteur":{"id": 1, "localisation": "Rennes", "destination": "Marseille", "nom": "Vicky la Conductrice",…},
     19	"participants":[],
     21	"listComEv":[]
     */
}
