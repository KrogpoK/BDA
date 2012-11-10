/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao.impl.configuration;

import enterprise.web_jpa_war.dao.AbstractCommonnDao;
import enterprise.web_jpa_war.entity.configuration.Configuration;
import javax.persistence.EntityManager;

/**
 *
 * @author user
 */
public class ConfigurationDao extends AbstractCommonnDao {

    public ConfigurationDao(EntityManager em) {
        this.em = em;
    }

    public Configuration getConfiguration(String supportStr) {
        try {
            return (Configuration) em.createQuery("select c from Configuration c "
                    + "where c.nomSupport='" + supportStr + "'").getSingleResult();

        } catch (Exception e) {
            return null;
        }
    }
}
