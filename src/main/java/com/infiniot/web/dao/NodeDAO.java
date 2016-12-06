package com.infiniot.web.dao;

import java.util.List;

import com.infiniot.web.model.Node;

public interface NodeDAO {

  // CRUD for node
  public void addNode(Node n);

  public Node getNode(String nid);

  public List<Node> getNodes();

  public List<Node> getNodes(String location);

  public void updateNode(Node n);

  public void updateNodes(List<Node> nodes);
}
