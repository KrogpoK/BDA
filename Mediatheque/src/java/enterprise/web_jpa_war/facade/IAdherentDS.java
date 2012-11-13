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

  

    public boolean creerAdherent(Adherent adherent);

    public void updateAdherent(Adherent adherent);

    public void supprimerAdherent(Adherent adherent);

    public List<Adherent> listerAdherent();

    public Adherent getAdherent(String nom, String prenom, Date dateDeNaissance);

    public Adherent getAdherent(int idAdherent);
   
}
