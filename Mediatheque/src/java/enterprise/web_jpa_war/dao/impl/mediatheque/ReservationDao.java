/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.Reservation;
import enterprise.web_jpa_war.util.DateTool;
import java.util.List;

/**
 *
 * @author user
 */
public class ReservationDao extends AbstractCommonnDao implements ICommonDao<Reservation> {


    public Reservation find(int id) {
       return em.find(Reservation.class, id);
    }

    public Reservation findByExample(Reservation example) {
        try
        {
            return (Reservation) em.createQuery("select r from Reservation where "+getWhereClause(example)).getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void persist(Reservation obj) {
       em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Reservation obj) {
         em.createQuery("delete from Reservation r where " + getWhereClause(obj));
    }

    public String getWhereClause(Reservation obj) {
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
        
        if (obj.getDebut() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.debut'").append(DateTool.printDate(obj.getDebut())).append("' ");
        }
        if (obj.getDispo() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.dispo'").append(DateTool.printDate(obj.getDispo())).append("' ");
        }
        
        return clause.toString();
    }

    public List<Reservation> findAll() {
        try{
            return (List<Reservation>) em.createQuery("select r from Reservation r" ).getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }

   
}