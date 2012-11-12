/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.Emprunt;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class EmpruntDao extends AbstractCommonnDao implements ICommonDao<Emprunt> {

    public EmpruntDao(EntityManager em) {
        this.em = em;
    }

    public Emprunt find(int id) {
        return em.find(Emprunt.class, id);
    }

    public Emprunt findByExample(Emprunt example) {
        return (Emprunt) em.createQuery("select e from Emprunt e where " + getWhereClause(example)).getSingleResult();
    }

    public void persist(Emprunt obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Emprunt obj) {
        em.createQuery("delete from Emprunt e where " + getWhereClause(obj));
    }

    public String getWhereClause(Emprunt obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.geteCompte().getId() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" e.eCompte.id='").append(obj.geteCompte().getId()).append("' ");
        }
        


        return clause.toString();
    }

    public List<Emprunt> findAll() {
        return em.createQuery("select e from Emprunt e").getResultList();
    }

    public List<Emprunt> findAllByExample(Emprunt obj) {
        try {
            return em.createQuery("select e from Emprunt e where " + getWhereClause(obj)).getResultList();
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Emprunt> findAllEmpruntsActif(Adherent a) {
      try {
            System.out.println("query : select e from Emprunt e where e.Ecompte='"+a.getCompte().getId()+"' and e.dateFinEmprunt = null");
            return em.createQuery("select e from Emprunt e where e.Ecompte='"+a.getCompte().getId()+"' and e.dateFinEmprunt = null").getResultList();
        } catch (Exception e) {
            return null;
        }  
    }
}
