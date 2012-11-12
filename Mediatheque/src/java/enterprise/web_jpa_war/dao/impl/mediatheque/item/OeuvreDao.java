/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.entity.mediatheque.item.Ouvrage;
import enterprise.web_jpa_war.util.DaoTool;
import enterprise.web_jpa_war.util.DateTool;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class OeuvreDao extends AbstractCommonnDao implements ICommonDao<Oeuvre> {

    public OeuvreDao(EntityManager em) {
        super.em = em;
    }

    public String getWhereClause(Oeuvre obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getGenre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.genre'=").append(obj.getGenre()).append("' ");
        }
        if (obj.getDateParution() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.dateParution'=").append(DateTool.printDate(obj.getDateParution())).append("' ");
        }
        if (obj.getTitre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.titre'=").append(obj.getTitre()).append("' ");
        }

        return clause.toString();
    }

    public Oeuvre find(int id) {
        return em.find(Oeuvre.class, id);
    }

    public Oeuvre findByExample(Oeuvre example) {
        try {
            return (Oeuvre) em.createQuery("select o from Oeuvre where " + getWhereClause(example)).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public void persist(Oeuvre obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Oeuvre obj) {
        em.createQuery("delete from Ouvrage o where " + getWhereClause(obj));
    }

    public List<Oeuvre> findAll() {
        return (List<Oeuvre>) em.createQuery("select o from Oeuvre o").getResultList();
    }

    public List<Oeuvre> findAllByExample(Oeuvre obj) {
        return (List<Oeuvre>) em.createQuery("select o from Oeuvre o where " + getWhereClause(obj)).getResultList();
    }

    public List<Oeuvre> findWithParams(HashMap<String, String> mapParamsOeuvre) {
        String retour = DaoTool.analyseParams(mapParamsOeuvre,"o");
        System.out.println("select o from Oeuvre o where" + retour);
        Long tpsAvt = System.currentTimeMillis();
        List<Oeuvre> result = (List<Oeuvre>) em.createQuery("select o from Oeuvre o where" + retour).getResultList();
        System.out.println("Temps de réponse : " + (System.currentTimeMillis() - tpsAvt) + "ms");
        return result;     
    }

}
