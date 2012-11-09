/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.BonDeCommande;
import enterprise.web_jpa_war.entity.mediatheque.Catalogue;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import java.util.List;

/**
 *
 * @author isso
 */
public class CatalogueDao extends AbstractCommonnDao implements ICommonDao<Catalogue> {

    public Catalogue find(int id) {
        return em.find(Catalogue.class, id);
    }

    public Catalogue findByExample(Catalogue example) {
        
        try  {
            
            return (Catalogue) em.createQuery("SELECT c from Catalogue WHERE "+getWhereClause(example)).getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void persist(Catalogue obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Catalogue obj) {
        em.createQuery("delete from Catalogue r where " + getWhereClause(obj));
    }

    public String getWhereClause(Catalogue obj) {
        
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getNameCatalogue() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.NameCatalogue'").append(obj.getNameCatalogue()).append("' ");
        }
        return clause.toString();
    }

    public List<Catalogue> findAll() {
        
      try{
            return (List<Catalogue>) em.createQuery("select r from Catalogue r" ).getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public List<Catalogue> findAllByExample(Catalogue obj) {
        return (List<Catalogue>) em.createQuery("select c from Catalogue c  where " + getWhereClause(obj)).getResultList();
    }
    
}
