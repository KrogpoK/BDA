/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque.item;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.item.Film;
import enterprise.web_jpa_war.util.DateTool;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class FilmDao extends AbstractCommonnDao implements ICommonDao<Film> {

    public FilmDao(EntityManager em) {
        super.em = em;
    }

    public Film find(int id) {
        return em.find(Film.class, id);
    }

    public Film findByExample(Film example) {
        return (Film) em.createQuery("select f from Film f where " + getWhereClause(example)).getSingleResult();
    }

    public void persist(Film obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Film obj) {
        em.createQuery("delete from Film f where " + getWhereClause(obj));
    }

    public List<Film> findAll() {
        return (List<Film>) em.createQuery("select f from Film f").getResultList();
    }

    public List<Film> findAllByExample(Film obj) {
        return (List<Film>) em.createQuery("select f from Film f where " + getWhereClause(obj)).getResultList();
    }

    public String getWhereClause(Film obj) {
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getTitre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.titre'").append(obj.getTitre()).append("' ");
        }
        if (obj.getGenre() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.genre'").append(obj.getGenre()).append("' ");
        }
        if (obj.getLangue() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.langue'").append(obj.getLangue()).append("' ");
        }
        if (obj.getRealisateur() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.realisateur'").append(obj.getRealisateur()).append("' ");
        }
        if (obj.getType() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.type'").append(obj.getType()).append("' ");
        }
        if (obj.getDateParution() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.dateParution'").append(DateTool.printDate(obj.getDateParution())).append("' ");
        }
        if (obj.getDuree() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" f.duree'").append(DateTool.printTime(obj.getDuree())).append("' ");
        }

        return clause.toString();
    }
}
