/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.dao.impl.AdherentDao;
import enterprise.web_jpa_war.dao.impl.configuration.ConfigurationDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.EmpruntDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.ReservationDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import enterprise.web_jpa_war.entity.mediatheque.Panier;
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
    private EmpruntDao empruntDao;
    private ReservationDao reservationDao;
    private ConfigurationDao configDao;

    public AdherentDS(EntityManager em) {
        adherentDao = new AdherentDao(em);
        empruntDao = new EmpruntDao(em);
        reservationDao = new ReservationDao(em);
        configDao = new ConfigurationDao(em);
    }

    public boolean checkId(String nom, String pass) {
        Adherent a = getAdherent(nom);
        if (a != null) {
            return pass.equals(a.getPass());
        }
        return false;
    }


    public boolean creerAdherent(Adherent adherent) {
        if (getAdherent(adherent.getNom(), adherent.getPrenom(), adherent.getDateNaissance()) == null
                && getAdherent(adherent.getLogin()) == null) {
            adherentDao.persist(adherent);
            return true;
        }
        return false;

    }

    public void updateAdherent(Adherent adherent) {
        adherentDao.persist(adherent);
    }

    public void supprimerAdherent(Adherent adherent) {
        adherentDao.delete(adherent);
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

   

   

    

    public Adherent getAdherent(String login) {
        Adherent a = new Adherent();
        a.setLogin(login);
        return adherentDao.findByExample(a);
    }

    public Adherent getAdherent(int idAdherent) {
        return adherentDao.find(idAdherent);
    }

   
}
