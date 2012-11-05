/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.IAbstractDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.util.DateTool;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Hibernate;

/**
 *
 * @author user
 */
public class AdherentDao extends AbstractCommonnDao implements IAbstractDao<Adherent> {

    public AdherentDao(EntityManager em) {
        super.em = em;
    }

    public Adherent find(int id) {
        Adherent a = em.find(Adherent.class, id);
        if (a.getAdress() != null) {
            Hibernate.initialize(a.getAdress());
        }
        if (a.getCompte() != null) {
            Hibernate.initialize(a.getCompte());
        }
        if (a.getCompte().getListeReservation() != null) {
            Hibernate.initialize(a.getCompte().getListeReservation());
            for (Reservation r : a.getCompte().getListeReservation()) {
                if (r.getOeuvre() != null) {
                    Hibernate.initialize(r.getOeuvre());
                }
            }
        }

        return a;
    }

    public Adherent findByExample(Adherent example) {
        try
        {
        Adherent a = (Adherent) em.createQuery("select a from Adherent a where " + getWhereClause(example)).getSingleResult();
        
        if (a.getAdress() != null) {
            Hibernate.initialize(a.getAdress());
        }
        if (a.getCompte() != null) {
            Hibernate.initialize(a.getCompte());
        }
        if (a.getCompte().getListeReservation() != null) {
            Hibernate.initialize(a.getCompte().getListeReservation());
            for (Reservation r : a.getCompte().getListeReservation()) {
                if (r.getOeuvre() != null) {
                    Hibernate.initialize(r.getOeuvre());
                }
            }
        }
        return a;
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void persist(Adherent obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Adherent obj) {
        em.createQuery("delete from Adherent a where " + getWhereClause(obj));
    }

    public List<Adherent> findAll() {
        List<Adherent> liste = (List<Adherent>) em.createQuery("select a from Adherent a").getResultList();
        return liste;
    }

    public String getWhereClause(Adherent a) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (a.getId() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" a.id='").append(a.getId()).append("' ");
        }
        if (a.getNom() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" a.nom='").append(a.getNom()).append("' ");
        }
        if (a.getPrenom() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" a.prenom='").append(a.getPrenom()).append("' ");
        }
         if (a.getLogin() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" a.login='").append(a.getLogin()).append("' ");
        }
        if (a.getDateNaissance() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            String bDate = DateTool.printDate(a.getDateNaissance());
            clause.append(" a.dateNaissance='").append(bDate).append("' ");
        }
        return clause.toString();
    }
}
