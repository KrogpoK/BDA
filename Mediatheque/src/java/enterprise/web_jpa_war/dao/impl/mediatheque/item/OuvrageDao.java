/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.entity.mediatheque.item.Livre;
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
public class OuvrageDao extends AbstractCommonnDao implements ICommonDao<Ouvrage> {

    public OuvrageDao(EntityManager em) {
        super.em = em;
    }

    public Ouvrage find(int id) {
        return em.find(Ouvrage.class, id);
    }

    public Ouvrage findByExample(Ouvrage example) {
        return (Ouvrage) em.createQuery("select o from Ouvrage o where " + getWhereClause(example)).getSingleResult();
    }

    public void persist(Ouvrage obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Ouvrage obj) {
        em.createQuery("delete from Ouvrage o where " + getWhereClause(obj));
    }

    public List<Ouvrage> findAll() {
        return (List<Ouvrage>) em.createQuery("select o from Ouvrage o").getResultList();
    }

    public List<Ouvrage> findAllByExample(Ouvrage obj) {
        return (List<Ouvrage>) em.createQuery("select o from Ouvrage o where " + getWhereClause(obj)).getResultList();
    }

    public List<Ouvrage> findAllByExample(Oeuvre oeuvre) {
        try {
            return (List<Ouvrage>) em.createQuery("select o from Ouvrage o where o.oeuvre.id = '" + oeuvre.getId() + "'").getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public String getWhereClause(Ouvrage obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getOeuvre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" o.oeuvre.id'").append(obj.getOeuvre().getId()).append("' ");
        }

        if (clause.length() > 1) {
            clause.append(" and");
        }
        clause.append(" o.nbEmprunts'").append(obj.getNbEmprunts()).append("' ");


        if (clause.length() > 1) {
            clause.append(" and");
        }
        clause.append(" o.disponibilite'").append(obj.getDisponibilite()).append("' ");


        if (obj.getDateArrivee() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" o.dateArivee'").append(DateTool.printDate(obj.getDateArrivee())).append("' ");
        }

        return clause.toString();
    }

    public List<Ouvrage> findWithParams(HashMap<String, String> mapParamsOuvrage) {
        String retour = analyseParamsOuvrage(mapParamsOuvrage);
        System.out.println("select o from Ouvrage o " + retour);
        Long tpsAvt = System.currentTimeMillis();
        List<Ouvrage> result = (List<Ouvrage>) em.createQuery("select o from Ouvrage o " + retour).getResultList();
        System.out.println("Temps de réponse : " + (System.currentTimeMillis() - tpsAvt) + "ms");
        return result;
    }

    private String analyseParamsOuvrage(HashMap<String, String> mapParamsOuvrage) {
        StringBuilder retour = new StringBuilder();
        retour.append("where ");
        retour.append(DaoTool.analyseParams(mapParamsOuvrage, "o"));
        if (!("".equals(mapParamsOuvrage.get(Ouvrage.DATEARRIVEE))) && mapParamsOuvrage.get(Ouvrage.DATEARRIVEE) != null) {
            retour.append("o.dateParution ");
            retour.append((mapParamsOuvrage.get(Ouvrage.DATEARRIVEEINDICATEUR).equals("avant")) ? "<" : ">");
            retour.append("= '" + mapParamsOuvrage.get(Ouvrage.DATEARRIVEE) + "' AND ");
        }
        if (!("".equals(mapParamsOuvrage.get(Ouvrage.DISPONIBILITE))) && mapParamsOuvrage.get(Ouvrage.DISPONIBILITE) != null) {
            retour.append("o.disponibilite = '" + mapParamsOuvrage.get(Ouvrage.DISPONIBILITE) + "' AND ");
        }
        if (!("".equals(mapParamsOuvrage.get(Ouvrage.NBEMPRUNTS))) && mapParamsOuvrage.get(Ouvrage.NBEMPRUNTS) != null) {
            retour.append("o.nbEmprunts ");
            retour.append((mapParamsOuvrage.get(Ouvrage.NBEMPRUNTSINDICATEUR).equals("avant")) ? "<" : ">");
            retour.append("= '" + mapParamsOuvrage.get(Ouvrage.NBEMPRUNTS) + "' AND ");
        }
        retour.append("1=1 ");
        return retour.toString();
    }
}
