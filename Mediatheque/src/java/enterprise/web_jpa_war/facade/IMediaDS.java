/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import java.util.List;

/**
 *
 * @author user
 */
public interface IMediaDS {

    public List<Film> getFilms();

    public List<CD> getCDs();

    public List<Livre> getLivres();

    public List<Periodique> getPeriodiques();
    
    public Film getFilm(int id);

    public CD getCD(int id);

    public Livre getLivre(int id);

    public Periodique getPeriodique(int id);

    public boolean estDisponible(int idOeuvre);

//    public void ajouterCritique(int idOeuvre, Critique critique);
//    public void creerOuvrage(Ouvrage ouvrage);
    public boolean oeuvreExists(Oeuvre oeuvre);

//    public List<Ouvrage> getOuvrages();
    public void creerFilm(Film film);

    public void creerLivre(Livre livre);

    public void creerPeriodique(Periodique periodique);

    public void creerCD(CD cd);
}
