package com.infiniot.web.dao.hibernate;

import com.infiniot.web.dao.AbstractDAO;
import com.infiniot.web.dao.NodeDAO;
import com.infiniot.web.model.Node;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * @author bot62
 */
public class NodeDAOImpl extends AbstractDAO<Node> implements NodeDAO {

  public void Node() {

  }

  // Constructor for unit testing
  // TODO find a better approach, e.g. CDI
  NodeDAOImpl(EntityManager em) {
    super.em = em;
  }

  @Override
  public void addNode(Node n) {
    persist(n);
  }

  @Override
  public Node getNode(String nodeId) {
    return em.find(Node.class, nodeId);
  }

  @Override
  @Deprecated
  public List<Node> getNodes() {
    return findAll();
  }

  @Override
  @Deprecated
  public List<Node> getNodes(String location) {
    // TODO
    return null;
  }

  @Override
  public void updateNode(Node n) {

  }

  @Override
  public void updateNodes(List<Node> nodes) {

  }

  @Override
  public List<Node> findAll() {
    return em.createQuery("select n from Node n", Node.class).getResultList();
  }

  @Override
  public void update(Node n) {
    // TODO
  }

  @Override
  public void update(Iterable<Node> nodes) {
    // TODO
  }

  @Override
  public void update(Node... nodes) {
    // TODO
  }

  @Override
  public void delete(Node node) {
    // TODO
  }

}
