/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.dao.impl.mediatheque.ReservationDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.facade.IReservationDS;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class ReservationDS implements IReservationDS {

    private ReservationDao reservationDao;

    public ReservationDS(EntityManager em) {
        reservationDao = new ReservationDao(em);
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

    public void creerReservation(Reservation r) {
        reservationDao.persist(r);
    }

    public void supprimerReservation(Reservation resa) {
        reservationDao.delete(resa);
    }

    public Reservation getReservation(int idReservation) {
        try {
            return reservationDao.find(idReservation);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Reservation> getReservationsActives(Adherent a) {
        return reservationDao.findAllResevationsActives(a);
    }
}
