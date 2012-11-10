/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.dao.impl.configuration.ConfigurationDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.CritiqueDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.ReservationDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.CDDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.FilmDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.LivreDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.OeuvreDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.OuvrageDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.PeriodiqueDao;
import enterprise.web_jpa_war.entity.configuration.Configuration;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import enterprise.web_jpa_war.facade.IMediaDS;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class MediaDS implements IMediaDS {

    private FilmDao fDao;
    private LivreDao lDao;
    private CDDao cDao;
    private PeriodiqueDao pDao;
    private OuvrageDao oDao;
    private OeuvreDao oeuvreDao;
    private ReservationDao reservationDao;
    private ConfigurationDao configDao;
    private CritiqueDao rDao;

    public MediaDS(EntityManager em) {
        fDao = new FilmDao(em);
        lDao = new LivreDao(em);
        cDao = new CDDao(em);
        pDao = new PeriodiqueDao(em);
        oDao = new OuvrageDao(em);
        oeuvreDao = new OeuvreDao(em);
        reservationDao = new ReservationDao(em);
        configDao = new ConfigurationDao(em);
        rDao = new CritiqueDao(em);

    }

    public List<Film> getFilms() {
        return fDao.findAll();
    }

    public List<CD> getCDs() {
        return cDao.findAll();
    }

    public List<Livre> getLivres() {
        return lDao.findAll();
    }

    public List<Periodique> getPeriodiques() {
        return pDao.findAll();
    }

    public boolean estDisponible(Oeuvre oeuvre) {
        List<Ouvrage> l = getListeOuvrage(oeuvre);
        if (l != null && l.size() > 0) {
            boolean dispo = false;
            for (Ouvrage o : l) {
                if (o.getDisponibilite() == Ouvrage.DISPO_LIBRE) {
                    dispo = true;
                }
            }
            return dispo;
        }
        return false;
    }

    public Configuration getConfiguration(String support) {
        return configDao.getConfiguration(support);
    }

    public boolean oeuvreExists(Oeuvre oeuvre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void creerFilm(Film film) {
        fDao.persist(film);
    }

    public void creerLivre(Livre livre) {
        lDao.persist(livre);
    }

    public void creerPeriodique(Periodique periodique) {
        pDao.persist(periodique);
    }

    public void creerCD(CD cd) {
        cDao.persist(cd);
    }

    public Film getFilm(int id) {
        return fDao.find(id);
    }

    public CD getCD(int id) {
        return cDao.find(id);
    }

    public Livre getLivre(int id) {
        return lDao.find(id);
    }

    public Periodique getPeriodique(int id) {
        return pDao.find(id);
    }

    public void creerOuvrage(Ouvrage ouvrage) {
        oDao.persist(ouvrage);
    }

    public void setCritique(Critique critique) {

        rDao.persist(critique);
    }

    public List<Critique> getCritiques(Oeuvre oeuvre) {

        Critique film = new Critique();
        film.setOeuvre(oeuvre);
        return rDao.findAllByExample(film);
    }

    public Oeuvre getOeuvre(int id) {
        return oeuvreDao.find(id);
    }

    public List<Ouvrage> getListeOuvrage(Oeuvre oeuvre) {
        return oDao.findAllByExample(oeuvre);
    }

    public int getPlaceAttenteReservation(Oeuvre oeuvre) {
        Reservation resa = new Reservation();
        resa.setOeuvre(oeuvre);
        List<Reservation> listeResa = reservationDao.findAllByExample(resa);
        if (resa != null) {
            return listeResa.size() + 1;
        }
        return 1;
    }

    public List<Oeuvre> getOeuvres() {
        return null;
    }
}
