/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Panier;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.facade.IAdherentDS;
import enterprise.web_jpa_war.facade.IPanierDS;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class PanierDS implements IPanierDS {

    IAdherentDS adherentDS;

    public PanierDS(EntityManager em) {
        adherentDS = new AdherentDS(em);
    }

    public void ajouterAuPanier(int idAdherent, Oeuvre oeuvre) {
        Adherent a = adherentDS.getAdherent(idAdherent);
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
                adherentDS.updateAdherent(a);
            }
        }
    }

    public void supprimerDuPanier(int idAdherent, int idOeuvre) {
        Adherent a = adherentDS.getAdherent(idAdherent);
        if (a != null) {
            a.getCompte().getPanier().supprimerOeuvre(idOeuvre);
            adherentDS.updateAdherent(a);
        }
    }
}
