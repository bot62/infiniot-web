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
  public List<Node> getNodes() {
    return findAll();
  }

  @Override
  public List<Node> getNodes(String location) {
    // TODO
    return null;
  }

  /**
   * @deprecated Use {@link #update(Node)} instead.
   */
  @Override
  @Deprecated
  public void updateNode(Node node) {
    update(node);
  }

  /**
   * @deprecated Use {@link #update(Iterable)} instead.
   */
  @Override
  @Deprecated
  public void updateNodes(List<Node> nodes) {
    update(nodes);
  }

  public List<Node> findAll() {
    return super.findAll(Node.class);
  }

  @Override
  public void update(Node node) {
    em.merge(node);
  }

  @Override
  public void update(Iterable<Node> nodes) {
    nodes.forEach(this::update);
  }

  @Override
  public void update(Node... nodes) {
    for (Node node : nodes) {
      em.merge(node);
    }
  }

  @Override
  public void delete(Node node) {
    em.createQuery("delete n from Node n").executeUpdate();
  }

}
