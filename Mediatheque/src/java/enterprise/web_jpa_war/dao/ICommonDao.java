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
public interface ICommonDao<T> {

    public T find(int id);

    public T findByExample(T example);

    public void persist(T obj);

    public void delete(T obj);

    public void deleteByExample(T obj);

    public String getWhereClause(T obj);

    public List<T> findAll();

    public List<T> findAllByExample(T obj);
}
