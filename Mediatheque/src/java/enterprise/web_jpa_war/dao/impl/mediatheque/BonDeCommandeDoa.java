/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.mediatheque;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.dao.ICommonDao;
import enterprise.web_jpa_war.entity.mediatheque.BonDeCommande;
import enterprise.web_jpa_war.entity.mediatheque.Critique;
import enterprise.web_jpa_war.entity.mediatheque.item.CD;
import java.util.List;

/**
 *
 * @author isso
 */
public class BonDeCommandeDoa extends AbstractCommonnDao implements ICommonDao<BonDeCommande>  {

    public BonDeCommande find(int id) {
        return em.find(BonDeCommande.class, id);
    }

    public BonDeCommande findByExample(BonDeCommande example) {
        
       try  {            
            return (BonDeCommande) em.createQuery("SELECT c from BonDeCommande WHERE "+getWhereClause(example)).getSingleResult();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public void persist(BonDeCommande obj) {
        em.persist(obj);
    }

    public void delete(int id) {
        em.remove(id);
    }

    public void deleteByExample(BonDeCommande obj) {
        em.createQuery("delete from BonDeCommande r where " + getWhereClause(obj));
    }

    public String getWhereClause(BonDeCommande obj) {
        
        StringBuilder clause = new StringBuilder();
        clause.append(" ");
        if (obj.getFournisseur() != null) {
            if (clause.length() > 1) {
                clause.append(" and");
            }
            clause.append(" r.id'").append(obj.getFournisseur().getId()).append("' ");
        }
             
        return clause.toString();
    }

    public List<BonDeCommande> findAll() {
        try{
            return (List<BonDeCommande>) em.createQuery("select r from BonDeCommande r" ).getResultList();
        }
        catch(Exception e)
        {
            return null;
        }
    }

    public List<BonDeCommande> findAllByExample(BonDeCommande obj) {
         return (List<BonDeCommande>) em.createQuery("select c from BonDeCommande c  where " + getWhereClause(obj)).getResultList();
    }
    
}
