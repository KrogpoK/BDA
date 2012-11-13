/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.facade.impl;

import enterprise.web_jpa_war.Exception.CreationEmpruntException;
import enterprise.web_jpa_war.dao.impl.AdherentDao;
import enterprise.web_jpa_war.dao.impl.configuration.ConfigurationDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.EmpruntDao;
import enterprise.web_jpa_war.dao.impl.mediatheque.ReservationDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.configuration.Configuration;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.facade.IEmpruntDS;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class EmpruntDS implements IEmpruntDS {

    private EmpruntDao empruntDao;
    private ConfigurationDao configurationDao;

    public EmpruntDS(EntityManager em) {
        empruntDao = new EmpruntDao(em);
        configurationDao = new ConfigurationDao(em);
    }

    public Emprunt getEmprunt(int idEmprunt) {

        return empruntDao.find(idEmprunt);

    }

    public List<Emprunt> getEmprunts(Adherent a) {
        Emprunt e = new Emprunt();
        e.seteCompte(a.getCompte());
        return empruntDao.findAllEmpruntsActif(a);
    }

    public void ajouterEmprunt(Emprunt emprunt) throws CreationEmpruntException {
        boolean estValide = false;
        emprunt.setRenouvele(false);
        
        List<Emprunt> listeEmprunt = getEmprutTermineDuJour(emprunt.geteCompte().getProprietaire(), new Date());
        Configuration config = configurationDao.getConfiguration(emprunt.geteOuvrage().getOeuvre().getStrType());
        //verifie si l'emprunt est renouvelé et, s'il est autorisé a etre renouvele
        if (listeEmprunt != null && !listeEmprunt.isEmpty()) {
            // recherche dans la liste des rendu du jour contient l'oeuvre
            for (int i = 0; i < listeEmprunt.size(); i++) {
                Emprunt e = listeEmprunt.get(i);
                Oeuvre oeuvre = e.geteOuvrage().getOeuvre();
                //si la liste contient l'oeuvre, alors c'est un renouvellement
                if (oeuvre.equals(emprunt.geteOuvrage().getOeuvre())) {
                    //check si l'emprunt peut être renouvele
                    if (config != null) {
                        // si l'oeuvre est renouvelable et pas deja renouvelee
                        if (config.isEstRenouvelable()) {
                            if (!e.isRenouvele()) {
                                estValide = true;
                                emprunt.setRenouvele(true);
                            } else {
                                throw new CreationEmpruntException("L'oeuvre a deja ete renouvelee");
                            }
                        } else {
                            throw new CreationEmpruntException("L'oeuvre ne peut pas etre renouvelee");
                        }
                    }
                }
            }
        } else {
            estValide = true;
        }
        
        // verifie si le nombre d'oeuvre du meme type n'est pas dépassée
        int nbOeuvreMemeType=0;
        for(Emprunt e : listeEmprunt)
        {
            Oeuvre o = e.geteOuvrage().getOeuvre();
            if(o.getStrType().equals(emprunt.geteOuvrage().getOeuvre().getStrType()))
            {
                nbOeuvreMemeType++;
            }
            if(nbOeuvreMemeType >= config.getNbOuvragesMemeType())
            {
               throw new CreationEmpruntException("Trop d'oeuvre du meme type ont ete empruntees");
            }
        }
        if (estValide) {
            empruntDao.persist(emprunt);
        }
    }

    public List<Emprunt> getEmpruntsActifs(Adherent a) {
        try {
            return empruntDao.findAllEmpruntsActif(a);
        } catch (Exception e) {
            return null;
        }
    }

    public List<Emprunt> getEmprutTermineDuJour(Adherent a, Date d) {
        return empruntDao.getEmprutTermineDuJour(a, d);
    }

    public Emprunt getEmpruntPrepare(Adherent a, Oeuvre oeuvre) {
        return empruntDao.getEmpruntPrepare(a, oeuvre);
    }
    
    
}
