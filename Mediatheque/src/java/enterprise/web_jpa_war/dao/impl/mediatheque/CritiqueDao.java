/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.Catalogue;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.util.DateTool;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isso
 */
public class CritiqueDao  extends AbstractCommonnDao implements ICommonDao<Critique>  {
    
   public Critique find(int id) {
           
       return em.find(Critique.class, id);
   }
    
    public Critique findByExample(Critique example) {
        
        try  {
            
            return (Critique) em.createQuery("SELECT c from Critique WHERE "+getWhereClause(example)).getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public String getWhereClause(Critique obj) {

        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getCompte() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.compte.id'").append(obj.getCompte().getId()).append("' ");
        }
        if (obj.getOeuvre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.oeuvre'").append(obj.getOeuvre().getId()).append("' ");
        }
        
        if (obj.getNote() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.note'").append(obj.getNote()).append("' ");
        }        
        return clause.toString();
    }
    public void persist(Critique obj) {
    
        em.persist(obj);
    }
    
    public void delete(int id) {
        
        em.remove(id);
    }
    
    public void deleteByExample(Critique obj) {
    
       em.createQuery("delete from Critique r where " + getWhereClause(obj));
    }
        
    public List<Critique> findAll() {
    
      try{
            return (List<Critique>) em.createQuery("select r from Critique r" ).getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public List<Critique> findAllByExample(Critique obj) {
        return (List<Critique>) em.createQuery("select c from Critique c  where " + getWhereClause(obj)).getResultList();
    }
}
