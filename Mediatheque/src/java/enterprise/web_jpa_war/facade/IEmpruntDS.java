/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.Exception.CreationEmpruntException;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public interface IEmpruntDS {

    public Emprunt getEmprunt(int idEmprunt);

    public List<Emprunt> getEmprunts(Adherent a);

    public void ajouterEmprunt(Emprunt emprunt) throws CreationEmpruntException;

    public List<Emprunt> getEmpruntsActifs(Adherent a);
    
    public List<Emprunt> getEmprutTermineDuJour(Adherent a,Date d);
    
    public Emprunt getEmpruntPrepare(Adherent a, Oeuvre oeuvre);
}