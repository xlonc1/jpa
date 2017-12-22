package pl.training.jpa.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.training.jpa.config.DatabaseConfig;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.*;

/**
 * @author Arkadiusz Parzych
 */
@ContextConfiguration(classes = DatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ClientServiceTest {

  @Autowired
  ClientService clientService;

  @PersistenceContext
  EntityManager em;

  public void shouldSaveProductsIntoDatabase(){
  }

}