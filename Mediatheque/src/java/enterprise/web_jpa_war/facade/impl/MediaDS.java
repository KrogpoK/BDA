/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.dao.impl.FilmDao;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
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
    //TODO a changer
//        FilmDao LDao = new FilmDao(emf.createEntityManager());
//        FilmDao CDao = new FilmDao(emf.createEntityManager());
//        FilmDao PDao = new FilmDao(emf.createEntityManager());

    public MediaDS(EntityManager em) {
        fDao = new FilmDao(em);
//        LivreDao LDao = new LivreDao(em);
//        CDDao CDao = new CDDao(em);
//        PeriodiqueDao PDao = new PeriodiqueDao(em);
    }

    public List<Film> getFilms() {
       return fDao.findAll();
    }

    public List<CD> getCDs() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Livre> getLivres() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<Periodique> getPeriodiques() {
        throw new UnsupportedOperationException("Not supported yet.");
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
//       lDao.persist(livre);
    }

    public void creerPeriodique(Periodique periodique) {
//        pDao.persist(periodique);
    }

    public void creerCD(CD cd) {
//         cDao.persist(cd);
    }

    public Film getFilm(int id) {
      return fDao.find(id);
    }

    public CD getCD(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Livre getLivre(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Periodique getPeriodique(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
