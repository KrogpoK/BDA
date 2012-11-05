/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.dao.impl.AdherentDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.facade.IAdherentDS;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class AdherentDS implements IAdherentDS {

    private AdherentDao adherentDao;

    public AdherentDS(EntityManager em) {
        adherentDao = new AdherentDao(em);
    }

    public boolean checkId(String nom, String pass) {
        Adherent a = getAdherent(nom);
        if(a != null)
        {
            System.out.println(a.getLogin()+" "+a.getPass());
            return pass.equals(a.getPass());
        }
         System.out.println("NULL");
        return false;
    }

    public void ajouterAuPanier(int idAdherent, Oeuvre oeuvre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void supprimerDuPanier(int idAdherent, int idOeuvre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void reserverPanier(int idAdherent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void creerAdherent(Adherent adherent) {
        adherentDao.persist(adherent);
    }

    public void updateAdherent(Adherent adherent) {
        adherentDao.persist(adherent);
    }

    public void supprimerAdherent(int idAdherent) {
        adherentDao.delete(idAdherent);
    }

    public List<Adherent> listerAdherent() {
        return adherentDao.findAll();
    }

    public Adherent getAdherent(String nom, String prenom, Date dateDeNaissance) {
        Adherent a = new Adherent();
        a.setNom(nom);
        a.setPrenom(prenom);
        a.setDateNaissance(dateDeNaissance);
        return adherentDao.findByExample(a);
    }

    public List<Reservation> getReservations(int idAdherent) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void supprimerEmprunt(int idAdherent, int idEmprunt) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void supprimerReservation(int idAdherent, int idReservation) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Adherent getAdherent(String login) {
        Adherent a = new Adherent();
        a.setLogin(login);
       return  adherentDao.findByExample(a);
    }
}
