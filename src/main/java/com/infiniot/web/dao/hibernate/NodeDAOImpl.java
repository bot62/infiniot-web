package com.infiniot.web.dao.hibernate;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.infiniot.web.dao.AbstractDAO;
import com.infiniot.web.dao.NodeDAO;
import com.infiniot.web.model.Node;

@Transactional
public class NodeDAOImpl extends AbstractDAO implements NodeDAO {

  public void addNode(Node n) {
    persist(n);
  }

  public Node getNode(String nid) {
    Criteria criteria = getSession().createCriteria(Node.class);
    criteria.add(Restrictions.eq("id", nid));
    return (Node) criteria.uniqueResult();
  }

  @Override
  public List<Node> getNodes() {
    Criteria criteria = getSession().createCriteria(Node.class);
    @SuppressWarnings("unchecked")
    List<Node> nodes = criteria.list();
    return nodes;
  }

  public List<Node> getNodes(String location) {
    Criteria criteria = getSession().createCriteria(Node.class);
    criteria.add(Restrictions.like("id", location));
    @SuppressWarnings("unchecked")
    List<Node> nodes = criteria.list();
    return nodes;
  }

  public void updateNode(Node n) {
    getSession().update(n);
  }

  public void updateNodes(List<Node> sensors) {

  }
}
