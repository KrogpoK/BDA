/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package enterprise.web_jpa_war.dao;

import java.util.List;

/**
 *
 * @author user
 */
public interface IBusinessDao<T> extends ICommonDao<T> {
    
    public List<T> findAllByAdherent(int adherentId);
    
}
