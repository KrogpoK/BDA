/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.Adherent;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.util.DateTool;
import java.nio.Buffer;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class LivreDao extends AbstractCommonnDao implements ICommonDao<Livre> {

    public LivreDao(EntityManager em)
    {
        super.em = em;
    }
    public Livre find(int id) {
       return em.find(Livre.class, id);
    }

    public Livre findByExample(Livre example) {
        return (Livre) em.createQuery("select l from Livre l where " + getWhereClause(example)).getSingleResult();
    }

    public void persist(Livre obj) {
        em.persist(obj);
    }

    public void delete(int id) {
       em.remove(id);
    }

    public void deleteByExample(Livre obj) {
        em.createQuery("delete from Livre l where " + getWhereClause(obj));
    }

     public List<Livre> findAll() {
        return (List<Livre>) em.createQuery("select l from Livre l" ).getResultList();
    }
     
     
    public String getWhereClause(Livre obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getTitre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" l.titre'").append(obj.getTitre()).append("' ");
        }
        if (obj.getGenre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" l.genre'").append(obj.getGenre()).append("' ");
        }
        if (obj.getAuteur()!= null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" l.auteur'").append(obj.getAuteur()).append("' ");
        }
        if (obj.getEditeur() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" l.editeur'").append(obj.getEditeur()).append("' ");
        }
        if (obj.getDateParution() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" l.dateParution'").append(DateTool.printDate(obj.getDateParution())).append("' ");
        }
        
        return clause.toString();
    }

   
    
}
