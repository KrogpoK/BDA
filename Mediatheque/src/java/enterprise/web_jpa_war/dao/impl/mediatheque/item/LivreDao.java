/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
import enterprise.web_jpa_war.util.DaoTool;
import enterprise.web_jpa_war.util.DateTool;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class LivreDao extends AbstractCommonnDao implements ICommonDao<Livre> {

    public LivreDao(EntityManager em) {
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

    public void delete(Livre obj) {
        em.remove(obj);
    }

    

    public void deleteByExample(Livre obj) {
        em.createQuery("delete from Livre l where " + getWhereClause(obj));
    }

    public List<Livre> findAll() {
        return (List<Livre>) em.createQuery("select l from Livre l").getResultList();
    }

    public List<Livre> findAllByExample(Livre obj) {
        return (List<Livre>) em.createQuery("select l from Livre l where " + getWhereClause(obj)).getResultList();
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
        if (obj.getAuteur() != null) {
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

    public List<Livre> findWithParams(HashMap<String, String> mapParamsOeuvre) {
        String retour = analyseParamsLivre(mapParamsOeuvre);
        System.out.println("select l from Livre l " + retour);
        Long tpsAvt = System.currentTimeMillis();
        List<Livre> result = (List<Livre>) em.createQuery("select l from Livre l " + retour).getResultList();
        System.out.println("Temps de réponse : " + (System.currentTimeMillis() - tpsAvt) + "ms");
        return result;  
    }

    private String analyseParamsLivre(HashMap<String, String> mapParamsOeuvre) {
        StringBuilder retour = new StringBuilder();
        retour.append("where ");
        retour.append(DaoTool.analyseParams(mapParamsOeuvre,"l"));
        if (!("".equals(mapParamsOeuvre.get(Livre.AUTEUR))) && mapParamsOeuvre.get(Livre.AUTEUR) != null) {
            retour.append("l.auteur like '%" + mapParamsOeuvre.get(Livre.AUTEUR) + "%' AND ");
        }
        if (!("".equals(mapParamsOeuvre.get(Livre.EDITEUR))) && mapParamsOeuvre.get(Livre.EDITEUR) != null) {
            retour.append("l.editeur like '%" + mapParamsOeuvre.get(Livre.EDITEUR) + "%' AND ");
        }
        retour.append("1=1 ");
        return retour.toString();
    }
}
