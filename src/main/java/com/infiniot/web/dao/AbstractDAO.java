package com.infiniot.web.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    em.flush();
  }

  protected List<T> findAll(Class<T> entityType) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(entityType);
    Root<T> rootEntry = cq.from(entityType);
    CriteriaQuery<T> all = cq.select(rootEntry);
    TypedQuery<T> allQuery = em.createQuery(all);
    return allQuery.getResultList();
  }

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
