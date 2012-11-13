/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.util.List;

/**
 *
 * @author user
 */
public interface IReservationDS {
    
     public List<Reservation> getReservationsByAdherent(Adherent a);

    public List<Reservation> getReservationsByOeuvre(Oeuvre oeuvre);
    
    public void creerReservation(Reservation r);

    public void supprimerReservation(Reservation resa);

    public Reservation getReservation(int idReservation);
    
    public List<Reservation> getReservationsActives(Adherent a);
}
