/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface IAdherentDS {

    public boolean checkId(String nom, String pass);

    public Adherent getAdherent(String login);

    public void ajouterAuPanier(int idAdherent, Oeuvre oeuvre);

    public void supprimerDuPanier(int idAdherent, int idOeuvre);

    public boolean reserverPanier(int idAdherent);

    public boolean creerAdherent(Adherent adherent);

    public void updateAdherent(Adherent adherent);

    public void supprimerAdherent(Adherent adherent);

    public List<Adherent> listerAdherent();

    public Adherent getAdherent(String nom, String prenom, Date dateDeNaissance);

//    public List<Critique> getCritiques(int idAdherent);
    public List<Reservation> getReservationsByAdherent(Adherent a);

    public List<Reservation> getReservationsByOeuvre(Oeuvre oeuvre);

    public List<Reservation> getReservationsActives(Adherent a);
    public List<Emprunt> getEmprunts(Adherent a);

    public void ajouterEmprunt(Emprunt emprunt);

    //indique si le rendu se fait en retard
    public Emprunt getEmprunt(int idEmprunt);

    public void creerReservation(Reservation r);
    
    public void supprimerReservation(Reservation reservation);

    public Reservation getReservation(int idReservation);
    public Adherent getAdherent(int idAdherent);
    
    public List<Emprunt> getEmpruntsActifs(Adherent a);
}
