package pl.training.jpa.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.training.jpa.config.DatabaseConfig;
import pl.training.jpa.model.Order;
import pl.training.jpa.model.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import java.util.List;
import static org.assertj.core.api.StrictAssertions.assertThat;
import static org.junit.Assert.*;

/**
 * @author Arkadiusz Parzych
 */
@ContextConfiguration(classes = DatabaseConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ShoppingListServiceTest {

  @Inject
  ShopingListService shopingListService;

  @Test
  public void shouldSaveProductIntoDatabase() {
    // given
    Product product = new Product("beer", "drinks", 3);

    // when
    shopingListService.saveProduct(product);

    // then
    Product productFromDB = shopingListService.findProduct(product.getId());
    assertThat(productFromDB.getName()).isEqualTo("beer");
    assertThat(productFromDB.getCategory()).isEqualTo("drinks");
    assertThat(productFromDB.getPrice()).isEqualTo(3);

  }

  @Test
  public void shouldFindAllProductsFromCategory() {
    // given
    shopingListService.saveProduct(new Product("bread", "breadstuff", 2));
    shopingListService.saveProduct(new Product("bun", "breadstuff", 1));
    shopingListService.saveProduct(new Product("ham", "meat", 10));

    // when
    List<Product> products = shopingListService.findProductsInCategory("breadstuff");

    // then
    Assertions.assertThat(products).hasSize(2);
    Assertions.assertThat(products).extracting("category").containsOnly("breadstuff");
  }


  @Test
  public void shouldUpdateProductPrice() {
    //given
    Product beer = new Product("beer", "drinks", 3);
    shopingListService.saveProduct(beer);
    Product wine = new Product("wine", "drinks", 20);
    shopingListService.saveProduct(wine);

    // when
    shopingListService.updatePrice(beer.getId(), 5);

    // then
    assertThat(shopingListService.findProduct(beer.getId()).getPrice()).isEqualTo(5);
    assertThat(shopingListService.findProduct(wine.getId()).getPrice()).isEqualTo(20);
  }

  @Transactional
  @Test
  public void shouldAddProductToOrder() {
    // given
    Product beer = new Product("beer", "drinks", 3);
    shopingListService.saveProduct(beer);
    Order order = new Order();
    shopingListService.saveOrder(order);

    // when
    shopingListService.addProductToOrder(order.getId(), beer.getId());

    // then
    Order orderFromDb = shopingListService.findOrder(order.getId());
    Assertions.assertThat(orderFromDb.getProducts()).extracting("id").containsOnly(beer.getId());
  }
}