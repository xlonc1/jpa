package pl.training.jpa.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import pl.training.jpa.config.DatabaseConfig;
import pl.training.jpa.model.Product;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    //given
    Product product = new Product("laptop", "electronics", 5200);

    //when
    //save
  }

  @Test
  public void shouldFindAllProductsDromCategory() {
  }

  @Test
  public void shouldUpdateProductPrice() {
  }

  @Test
  public void shouldAddProductToOrder() {
  }
}