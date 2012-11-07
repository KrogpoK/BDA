/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.dao.impl.mediatheque.item.CDDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.FilmDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.LivreDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.item.PeriodiqueDao;
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

    public MediaDS(EntityManager em) {
        fDao = new FilmDao(em);
        LivreDao lDao = new LivreDao(em);
        CDDao cDao = new CDDao(em);
        PeriodiqueDao pDao = new PeriodiqueDao(em);
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

    public boolean estDisponible(int idOeuvre) {
        throw new UnsupportedOperationException("Not supported yet.");
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
