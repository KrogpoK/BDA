/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

/**
 *
 * @author user
 */
public abstract class AbstractCommonnDao {
 
    protected EntityManagerFactory emf;
    @Resource
    protected UserTransaction utx;

    protected EntityManager em;
    
    protected EntityManager getEntityManager(EntityManagerFactory emf)
    {
        if(em == null)
        {
            this.emf = emf;
            em = emf.createEntityManager();
        }
        return em;
    }
    public void beginTransaction() throws NotSupportedException, SystemException {
        utx.begin();
        EntityManager em = emf.createEntityManager();
    }
    
    public void close() throws Exception
    {
        utx.commit();
         
    }
}
