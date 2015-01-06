package services.models;

/**
 * Created by josian on 06/01/15.
 */
public class Voiture {

    public Long id;
    public String series;
    public String nbPlaceTotal;

   public Voiture(Long id, String series,String nbPlaceTotal) {
        this.id =id;
        this.series=series;
        this.nbPlaceTotal = nbPlaceTotal;
    }

    public Voiture()
    {

    }
}
