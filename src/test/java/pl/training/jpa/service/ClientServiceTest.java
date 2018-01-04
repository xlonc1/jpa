package pl.training.jpa.service;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.training.jpa.config.DatabaseConfig;
import pl.training.jpa.model.Client;
import pl.training.jpa.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.StrictAssertions.assertThat;
import static pl.training.jpa.service.OrderBuilder.anOrder;

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

  @Transactional
  @Test
  public void shouldSaveProductIntoDatabase() {
//		given
    Client client = new Client();

    client.addOrder(anOrder()
        .withProduct(new Product("vodka", "strong-drinks", 2))
        .withProduct(new Product("whisky", "strong-drinks", 6))
        .build());

    client.addOrder(anOrder()
        .withProduct(new Product("rum", "strong-drinks", 4))
        .withProduct(new Product("burbon", "strong-drinks", 3))
        .build());

    client.addOrder(anOrder()
        .withProduct(new Product("beer", "light-drinks", 1))
        .build());

    clientService.saveClient(client);

//		when
    Statistics emfStats = em.getEntityManagerFactory().unwrap(SessionFactory.class).getStatistics();
    emfStats.setStatisticsEnabled(true);
    long collectionFetchCountBefore = emfStats.getCollectionFetchCount();

    Client clientFromDb = clientService.findClient(client.getId());

//		then
    int sum = clientFromDb.getOrders().stream().flatMap(order -> order.getProducts().stream()).mapToInt(Product::getPrice).sum();
    long collectionFetchCountAfter = emfStats.getCollectionFetchCount();
    assertThat(sum).isEqualTo(16);
    assertThat(collectionFetchCountAfter - collectionFetchCountBefore).isEqualTo(0);
  }

}