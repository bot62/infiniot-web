package com.infiniot.web.dao.hibernate;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import com.infiniot.web.dao.NodeDAO;
import com.infiniot.web.model.Node;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author bot62
 */
public class NodeDAOImplTest {

  private static EntityManagerFactory emf;

  private List<Node> existingNodes;

  private EntityManager em;

  private NodeDAO nodeDAO;

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    emf = Persistence.createEntityManagerFactory(NodeDAOImplTest.class.getSimpleName() + "PU");
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    if (emf != null) {
      emf.close();
    }
  }

  @Before
  public void setUp() throws Exception {
    existingNodes = new ArrayList<>();
    existingNodes.add(new Node("node-1"));
    existingNodes.add(new Node("node-2"));
    existingNodes.add(new Node("node-3"));

    em = emf.createEntityManager();
    em.getTransaction().begin();
    existingNodes.forEach(em::persist);
    em.getTransaction().commit();

    nodeDAO = new NodeDAOImpl(em);
  }

  @After
  public void tearDown() throws Exception {
    em.getTransaction().begin();
    em.createQuery("delete from Node n").executeUpdate();
    em.getTransaction().commit();
    em.close();

    existingNodes.clear();
  }

  @Test
  public void getNodes() throws Exception {
    assertThat(nodeDAO.getNodes()).containsExactlyElementsOf(existingNodes);
  }

  @Test
  public void addNode() throws Exception {
    Node newNode = new Node("node-4");

    em.getTransaction().begin();
    nodeDAO.addNode(newNode);
    em.getTransaction().commit();

    List<Node> actualNodes = nodeDAO.getNodes();
    assertThat(actualNodes).hasSize(4);
    assertThat(actualNodes).contains(newNode);
    assertThat(actualNodes).containsAll(existingNodes);
  }

}
