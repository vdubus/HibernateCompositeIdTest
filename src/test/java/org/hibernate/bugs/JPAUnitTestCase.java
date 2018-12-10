package org.hibernate.bugs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.bugs.domain.jpa.Agent;
import org.hibernate.bugs.domain.jpa.EmbeddedTypeMission;
import org.hibernate.bugs.domain.jpa.EmbeddedTypeMissionId;
import org.hibernate.bugs.domain.jpa.IdClassTypeMission;
import org.hibernate.bugs.domain.jpa.IdClassTypeMissionId;
import org.hibernate.bugs.domain.jpa.SimpleTypeMission;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java Persistence API.
 */
public class JPAUnitTestCase {

	private EntityManagerFactory entityManagerFactory;

	@Before
	public void init() {
		entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
	}

	@After
	public void destroy() {
		entityManagerFactory.close();
	}

	@Test
	public void embeddedIdTypeMissionTest() throws Exception {
		final EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		final Agent agent = new Agent();
		agent.setLogin("jdoe");
		agent.setFirstName("John");
		agent.setLastName("Doe");

		entityManager.persist(agent);
		entityManager.flush();
		entityManager.refresh(agent);

		final EmbeddedTypeMission mission = new EmbeddedTypeMission();
		mission.setCity("New York");
		mission.setDateStart(LocalDateTime.of(2018, 12, 5, 7, 30));
		mission.setDateEnd(LocalDateTime.of(2018, 12, 6, 18, 30));
		mission.setAgent(agent);

		entityManager.persist(mission);
		entityManager.flush();
		entityManager.refresh(mission);

		final EmbeddedTypeMission result = entityManager.find(EmbeddedTypeMission.class, new EmbeddedTypeMissionId(mission.getId().getId(), "jdoe"));
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getId().getId());
		assertEquals("jdoe", result.getId().getAgentLogin());
		assertEquals("New York", result.getCity());

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void idClassTypeMissionTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		final Agent agent = new Agent();
		agent.setLogin("jdoe");
		agent.setFirstName("John");
		agent.setLastName("Doe");

		entityManager.persist(agent);
		entityManager.flush();
		entityManager.refresh(agent);

		final IdClassTypeMission mission = new IdClassTypeMission();
		mission.setCity("New York");
		mission.setDateStart(LocalDateTime.of(2018, 12, 5, 7, 30));
		mission.setDateEnd(LocalDateTime.of(2018, 12, 6, 18, 30));
		mission.setAgent(agent);

		entityManager.persist(mission);
		entityManager.flush();
		entityManager.refresh(mission);

		final IdClassTypeMission result = entityManager.find(IdClassTypeMission.class, new IdClassTypeMissionId(mission.getId(), "jdoe"));
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getAgent());
		assertEquals("jdoe", result.getAgent().getLogin());
		assertEquals("New York", result.getCity());

		entityManager.getTransaction().commit();
		entityManager.close();
	}

	@Test
	public void simpleTypeMissionTest() throws Exception {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();

		final Agent agent = new Agent();
		agent.setLogin("jdoe");
		agent.setFirstName("John");
		agent.setLastName("Doe");

		entityManager.persist(agent);
		entityManager.flush();
		entityManager.refresh(agent);

		final SimpleTypeMission mission = new SimpleTypeMission();
		mission.setCity("New York");
		mission.setDateStart(LocalDateTime.of(2018, 12, 5, 7, 30));
		mission.setDateEnd(LocalDateTime.of(2018, 12, 6, 18, 30));
		mission.setAgent(agent);

		entityManager.persist(mission);
		entityManager.flush();
		entityManager.refresh(mission);

		final SimpleTypeMission result = entityManager.find(SimpleTypeMission.class, mission.getId());
		assertNotNull(result);
		assertNotNull(result.getId());
		assertNotNull(result.getAgent());
		assertEquals("New York", result.getCity());

		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
