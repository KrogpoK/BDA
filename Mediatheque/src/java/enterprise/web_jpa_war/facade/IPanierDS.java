/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Panier;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;

/**
 *
 * @author user
 */
public interface IPanierDS {
    
     public void ajouterAuPanier(int idAdherent, Oeuvre oeuvre);
     
     public void supprimerDuPanier(int idAdherent, int idOeuvre);
}
