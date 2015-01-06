package services.models;

/**
 * An Attendee is identified by `id`, he has a `name` and a number of available seats `availableSeats` (which is zero if this Attendee is not a driver)
 */
public class Attendee {
    public final Long id;
    public final String nom;
    public final String localisation;
    public final String  destination;
    public final Voiture voiture;

    public Attendee(Long id, String name, String localisation,  String destination, Voiture voiture) {
        this.id = id;
        this.nom = name;
        this.destination=destination;
        this.localisation=localisation;
        this.voiture=voiture;
    }
}
