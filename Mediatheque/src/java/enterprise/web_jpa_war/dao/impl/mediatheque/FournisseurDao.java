/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.Catalogue;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.Fournisseur;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import java.util.List;

/**
 *
 * @author isso
 */
public class FournisseurDao extends AbstractCommonnDao implements ICommonDao<Fournisseur>   {

    public Fournisseur find(int id) {
        return em.find(Fournisseur.class, id);
    }

    public Fournisseur findByExample(Fournisseur example) {
        
          try  {
            
            return (Fournisseur) em.createQuery("SELECT c from Critique WHERE "+getWhereClause(example)).getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void persist(Fournisseur obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(Fournisseur obj) {
        em.createQuery("delete from Fournisseur r where " + getWhereClause(obj));
    }

    public String getWhereClause(Fournisseur obj) {

        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getNomFournisseur() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.nomFournisseur'").append(obj.getNomFournisseur()).append("' ");
        }       
        return clause.toString();
    }

    public List<Fournisseur> findAll() {
        
       try{
            return (List<Fournisseur>) em.createQuery("select r from Fournisseur r" ).getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }
    
    public List<Fournisseur> findAllByExample(Fournisseur obj) {
        return (List<Fournisseur>) em.createQuery("select c from Fournisseur c  where " + getWhereClause(obj)).getResultList();
    }
    
}
