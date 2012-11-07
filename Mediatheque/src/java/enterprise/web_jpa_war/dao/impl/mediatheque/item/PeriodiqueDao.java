/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import enterprise.web_jpa_war.util.DateTool;
import java.nio.Buffer;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class PeriodiqueDao extends AbstractCommonnDao implements ICommonDao<Periodique> {

    public PeriodiqueDao(EntityManager em)
    {
        super.em = em;
    }
    public Periodique find(int id) {
       return em.find(Periodique.class, id);
    }

    public Periodique findByExample(Periodique example) {
        return (Periodique) em.createQuery("select p from Periodique p where " + getWhereClause(example)).getSingleResult();
    }

    public void persist(Periodique obj) {
        em.persist(obj);
    }

    public void delete(int id) {
       em.remove(id);
    }

    public void deleteByExample(Periodique obj) {
        em.createQuery("delete from Periodique p where " + getWhereClause(obj));
    }

     public List<Periodique> findAll() {
        return (List<Periodique>) em.createQuery("select p from Periodique p" ).getResultList();
    }
     
     
    public String getWhereClause(Periodique obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getTitre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" p.titre'").append(obj.getTitre()).append("' ");
        }
        if (obj.getGenre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" p.genre'").append(obj.getGenre()).append("' ");
        }
        if (obj.getPeriodicite()!= null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" p.periodicite'").append(obj.getPeriodicite()).append("' ");
        }
        if (obj.getTheme() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" p.theme'").append(obj.getTheme()).append("' ");
        }
        if (obj.getType() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" p.type'").append(obj.getType()).append("' ");
        }
        if (obj.getDateParution() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" p.dateParution'").append(DateTool.printDate(obj.getDateParution())).append("' ");
        }
        
        return clause.toString();
    }

   
    
}
