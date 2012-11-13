/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.entity.configuration.Configuration;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import java.util.HashMap;
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
    
    public Oeuvre getOeuvre(int id);

    public boolean estDisponible(Oeuvre oeuvre);
    
    public List<Ouvrage> getListeOuvrage(Oeuvre oeuvre);

//    public void ajouterCritique(int idOeuvre, Critique critique);
    public void creerOuvrage(Ouvrage ouvrage);

    public boolean oeuvreExists(Oeuvre oeuvre);

//    public List<Ouvrage> getOuvrages();
    public void creerFilm(Film film);

    public void creerLivre(Livre livre);

    public void creerPeriodique(Periodique periodique);

    public void creerCD(CD cd);
    
    public int getPlaceAttenteReservation(Oeuvre oeuvre);
    
    public Configuration getConfiguration(String support);
    
    public List<Oeuvre> getOeuvres();
   
     public void setCritique(Critique critique);

    public List<Critique> getCritiques(Oeuvre oeuvre);

    public List<Film> getFilms(HashMap<String, String> mapParamsOeuvre);

    public List<Livre> getLivres(HashMap<String, String> mapParamsOeuvre);

    public List<CD> getCDs(HashMap<String, String> mapParamsOeuvre);

    public List<Periodique> getPeriodiques(HashMap<String, String> mapParamsOeuvre);

    public List<Oeuvre> getOeuvres(HashMap<String, String> mapParamsOeuvre);

    public List<Ouvrage> getOuvrages(HashMap<String, String> mapParamsOuvrage);

    public void creerOeuvre(Oeuvre o);
}