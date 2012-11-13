/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.entity.mediatheque.item.Periodique;
import enterprise.web_jpa_war.util.DaoTool;
import enterprise.web_jpa_war.util.DateTool;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class PeriodiqueDao extends AbstractCommonnDao implements ICommonDao<Periodique> {

    public PeriodiqueDao(EntityManager em) {
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

    public void delete(Periodique obj) {
        em.remove(obj);
    }

    
    public void deleteByExample(Periodique obj) {
        em.createQuery("delete from Periodique p where " + getWhereClause(obj));
    }

    public List<Periodique> findAll() {
        return (List<Periodique>) em.createQuery("select p from Periodique p").getResultList();
    }

    public List<Periodique> findAllByExample(Periodique obj) {
        return (List<Periodique>) em.createQuery("select p from Periodique p where " + getWhereClause(obj)).getResultList();
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
        if (obj.getPeriodicite() != null) {
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

    public List<Periodique> findWithParams(HashMap<String, String> mapParamsOeuvre) {
        String retour = analyseParamsPeriodique(mapParamsOeuvre);
        System.out.println("select p from Periodique p " + retour);
        Long tpsAvt = System.currentTimeMillis();
        List<Periodique> result = (List<Periodique>) em.createQuery("select p from Periodique p " + retour).getResultList();
        System.out.println("Temps de réponse : " + (System.currentTimeMillis() - tpsAvt) + "ms");
        return result; 
    }

    private String analyseParamsPeriodique(HashMap<String, String> mapParamsOeuvre) {
        StringBuilder retour = new StringBuilder();
        retour.append("where ");
        retour.append(DaoTool.analyseParams(mapParamsOeuvre,"p"));
        if (!("".equals(mapParamsOeuvre.get(Periodique.THEME))) && mapParamsOeuvre.get(Periodique.THEME) != null) {
            retour.append("p.theme like '%" + mapParamsOeuvre.get(Periodique.THEME) + "%' AND ");
        }
        if (!("".equals(mapParamsOeuvre.get(Periodique.PERIODICITE))) && mapParamsOeuvre.get(Periodique.THEME) != null) {
            retour.append("p.periodicite = '" + mapParamsOeuvre.get(Periodique.PERIODICITE) + "' AND ");
        }
        retour.append("1=1 ");
        return retour.toString();
    }
}
