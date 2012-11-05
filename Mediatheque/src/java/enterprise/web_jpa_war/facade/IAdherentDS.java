/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.entity.Adherent;
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

    public void reserverPanier(int idAdherent);

    public void creerAdherent(Adherent adherent);

    public void updateAdherent(Adherent adherent);

    public void supprimerAdherent(int idAdherent);

    public List<Adherent> listerAdherent();

    public Adherent getAdherent(String nom, String prenom, Date dateDeNaissance);

//    public List<Critique> getCritiques(int idAdherent);
    public List<Reservation> getReservations(int idAdherent);

//    public List<Emprunt> getEmprunts(int idAdherent);
//    public void ajouterEmprunt(int idAdherent, Emprunt emprunt);
    public void supprimerEmprunt(int idAdherent, int idEmprunt);

    public void supprimerReservation(int idAdherent, int idReservation);
}
