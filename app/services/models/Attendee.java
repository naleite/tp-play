package services.models;

/**
 * An Attendee is identified by `id`, he has a `name` and a number of available seats `availableSeats` (which is zero if this Attendee is not a driver)
 */
public class Attendee {
    public Long id;
    public String localisation;
    public String  destination;
    public String nom;
    public Voiture voiture;

   /** public Attendee(Long id, String nom, String localisation, String destination, Voiture voiture) {
        this.id = id;
        this.nom = nom;
        this.destination=destination;
        this.localisation=localisation;
        this.voiture=voiture;
    }**/
}
