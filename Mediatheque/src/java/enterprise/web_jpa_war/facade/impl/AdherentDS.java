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

    public void ajouterAuPanier(int idAdherent, Oeuvre oeuvre) {
        Adherent a = getAdherent(idAdherent);
        if (a != null) {
            Panier p = a.getCompte().getPanier();
            boolean present = false;
            if (p != null) {
                if (p.getListeOeuvre() != null) {
                    for (Oeuvre o : p.getListeOeuvre()) {
                        if (o.getId() == oeuvre.getId()) {
                            present = true;
                        }
                    }
                }
            }
            if (!present) {
                a.getCompte().getPanier().ajouterOeuvre(oeuvre);
                updateAdherent(a);
            }
        }
    }

    public void supprimerDuPanier(int idAdherent, int idOeuvre) {
        Adherent a = getAdherent(idAdherent);
        if (a != null) {
            a.getCompte().getPanier().supprimerOeuvre(idOeuvre);
            updateAdherent(a);
        }
    }

    public boolean reserverPanier(int idAdherent) {
        throw new UnsupportedOperationException("Not supported yet.");
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

    public List<Reservation> getReservationsByAdherent(Adherent a) {
        Reservation r = new Reservation();
        r.setCompte(a.getCompte());
        return reservationDao.findAllByExample(r);
    }

    public List<Reservation> getReservationsByOeuvre(Oeuvre oeuvre) {
       Reservation r = new Reservation();
       r.setOeuvre(oeuvre);
        return reservationDao.findAllByExample(r);
    }

    
    public Emprunt getEmprunt(int idEmprunt) {

        return empruntDao.find(idEmprunt);

    }

    public void creerReservation(Reservation r) {
        reservationDao.persist(r);
    }

    
    public void supprimerReservation(Reservation resa) {
        reservationDao.delete(resa);
    }

    public Reservation getReservation(int idReservation) {
        try{
            return reservationDao.find(idReservation);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    
    public Adherent getAdherent(String login) {
        Adherent a = new Adherent();
        a.setLogin(login);
        return adherentDao.findByExample(a);
    }

    public Adherent getAdherent(int idAdherent) {
        return adherentDao.find(idAdherent);
    }

    public List<Emprunt> getEmprunts(Adherent a) {
        Emprunt e = new Emprunt();
        e.seteCompte(a.getCompte());
        return empruntDao.findAllEmpruntsActif(a);
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        empruntDao.persist(emprunt);
    }

    public List<Reservation> getReservationsActives(Adherent a) {
        return reservationDao.findAllResevationsActives(a);
    }
    
    
    
}
