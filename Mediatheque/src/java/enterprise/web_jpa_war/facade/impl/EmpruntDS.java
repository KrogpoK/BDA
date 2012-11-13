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
import enterprise.web_jpa_war.facade.IEmpruntDS;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class EmpruntDS implements IEmpruntDS {

    private EmpruntDao empruntDao;

    public EmpruntDS(EntityManager em) {
        empruntDao = new EmpruntDao(em);
    }

    public Emprunt getEmprunt(int idEmprunt) {

        return empruntDao.find(idEmprunt);

    }

    public List<Emprunt> getEmprunts(Adherent a) {
        Emprunt e = new Emprunt();
        e.seteCompte(a.getCompte());
        return empruntDao.findAllEmpruntsActif(a);
    }

    public void ajouterEmprunt(Emprunt emprunt) {
        empruntDao.persist(emprunt);
    }

    public List<Emprunt> getEmpruntsActifs(Adherent a) {
        try {
            return empruntDao.findAllEmpruntsActif(a);
        } catch (Exception e) {
            return null;
        }
    }
}
