package com.infiniot.web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;

/**
 * Abstract data access object (DAO).
 *
 * @param <T> The entity type of the access object.
 */
public abstract class AbstractDAO<T> {

  @PersistenceContext
  protected EntityManager em;

  public void persist(T entity) {
    em.persist(entity);
  }

  public abstract List<T> findAll();

  public T find(String entityId) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  public void delete(T entity) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  public void update(T entity) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  public void update(Iterable<T> entities) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  public void update(T... entities) {
    throw new UnsupportedOperationException("Not implemented yet.");
  }

  // TODO delete this method before PR
  @Deprecated
  public Session getSession() {
    return null;
  }

}
