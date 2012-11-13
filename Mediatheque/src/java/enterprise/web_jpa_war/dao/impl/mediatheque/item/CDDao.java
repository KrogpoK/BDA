/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Oeuvre;
import enterprise.web_jpa_war.util.DaoTool;
import enterprise.web_jpa_war.util.DateTool;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class CDDao extends AbstractCommonnDao implements ICommonDao<CD> {

    public CDDao(EntityManager em)
    {
        super.em = em;
    }
    public CD find(int id) {
       return em.find(CD.class, id);
    }

    public CD findByExample(CD example) {
        return (CD) em.createQuery("select c from CD c where " + getWhereClause(example)).getSingleResult();
    }

    public void persist(CD obj) {
        em.persist(obj);
    }

    public void delete(CD obj) {
        em.remove(obj);
    }

   

    public void deleteByExample(CD obj) {
        em.createQuery("delete from CD c where " + getWhereClause(obj));
    }

     public List<CD> findAll() {
        return (List<CD>) em.createQuery("select c from CD c" ).getResultList();
    }
     
     public List<CD> findAllByExample(CD obj) {
        return (List<CD>) em.createQuery("select c from CD c  where " + getWhereClause(obj)).getResultList();
    }
     
    public String getWhereClause(CD obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getGenre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.genre'").append(obj.getGenre()).append("' ");
        }
        if (obj.getInterprete()!= null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.interprete'").append(obj.getInterprete()).append("' ");
        }
        if (obj.getMaisonEdition() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.maisonEdition'").append(obj.getMaisonEdition()).append("' ");
        }
        if (obj.getNbPiste() != 0) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" c.nbPiste'").append(obj.getNbPiste()).append("' ");
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

    public List<CD> findWithParams(HashMap<String, String> mapParamsOeuvre) {
        String retour = analyseParamsCD(mapParamsOeuvre);
        System.out.println("select c from CD c " + retour);
        Long tpsAvt = System.currentTimeMillis();
        List<CD> result = (List<CD>) em.createQuery("select c from CD c " + retour).getResultList();
        System.out.println("Temps de réponse : " + (System.currentTimeMillis() - tpsAvt) + "ms");
        return result;    
    }

    private String analyseParamsCD(HashMap<String, String> mapParamsOeuvre) {
        StringBuilder retour = new StringBuilder();
        retour.append("where ");
        retour.append(DaoTool.analyseParams(mapParamsOeuvre, "c"));
        if (!("".equals(mapParamsOeuvre.get(CD.INTERPRETE))) && mapParamsOeuvre.get(CD.INTERPRETE) != null) {
            retour.append("c.interprete like '%" + mapParamsOeuvre.get(CD.INTERPRETE) + "%' AND ");
        }
        if (!("".equals(mapParamsOeuvre.get(CD.MAISONEDITION))) && mapParamsOeuvre.get(CD.MAISONEDITION) != null) {
            retour.append("c.maisonEdition like '%" + mapParamsOeuvre.get(CD.MAISONEDITION) + "%' AND ");
        }
        retour.append("1=1 ");
        return retour.toString();
    }
    
}
