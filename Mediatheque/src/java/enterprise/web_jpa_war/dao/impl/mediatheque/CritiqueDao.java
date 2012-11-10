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
import javax.persistence.EntityManager;

/**
 *
 * @author isso
 */
public class CritiqueDao  extends AbstractCommonnDao implements ICommonDao<Critique>  {
    
    public CritiqueDao(EntityManager em) {
        super.em = em;
    }
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

        System.out.print("1");
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getCompte() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            System.out.print("2");
            clause.append(" c.compte.id = '").append(obj.getCompte().getId()).append("' ");
        }
        if (obj.getOeuvre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            System.out.print("4");
            System.out.print(obj.getOeuvre().getId());
            clause.append(" c.oeuvre.id = '").append(obj.getOeuvre().getId()).append("' ");
        }
        
        if (obj.getNote() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            System.out.print("3");
            clause.append(" c.note.id = '").append(obj.getNote()).append("' ");
        }     
        System.out.print(clause);
        return clause.toString();
    }
    public void persist(Critique obj) {
    
        em.persist(obj);
    }
    
    public void delete(int id) {
        
        em.remove(id);
    }
    
    public void deleteByExample(Critique obj) {
    
       em.createQuery("delete from Critique c where " + getWhereClause(obj));
    }
        
    public List<Critique> findAll() {
    
      try{
            return (List<Critique>) em.createQuery("select c from Critique c" ).getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public List<Critique> findAllByExample(Critique obj) {
        
        List<Critique> res = (List<Critique>) em.createQuery("select c from Critique c  where " + getWhereClause(obj)).getResultList();
        System.out.print(res);
        return res;
    }
}
