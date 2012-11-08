/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.util.DateTool;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class OeuvreDao extends AbstractCommonnDao {

    public OeuvreDao(EntityManager em)
    {
        super.em = em;
    }
    
    public boolean exists(Oeuvre oeuvre)
    {
        try
        {
            em.createQuery("select o.titre from Oeuvre o where "+getWhereClause(oeuvre)).getSingleResult();
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }
     
     
    public String getWhereClause(Oeuvre obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getGenre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.genre'").append(obj.getGenre()).append("' ");
        }
        if (obj.getDateParution() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.dateParution'").append(DateTool.printDate(obj.getDateParution())).append("' ");
        }
        if (obj.getTitre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.titre'").append(obj.getTitre()).append("' ");
        }
        
        return clause.toString();
    }

   
    
}
