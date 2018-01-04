package pl.training.jpa.service;

import org.springframework.stereotype.Service;
import pl.training.jpa.model.Order;
import pl.training.jpa.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Arkadiusz Parzych
 */
@Service
@Transactional
public class ShopingListService {

  @PersistenceContext
  private EntityManager em;

  public void saveProduct(Product product) {
    em.persist(product);
  }

  public void saveOrder(Order order) {
    em.persist(order);
  }

  public Product findProduct(int productId) {
    return em.find(Product.class, productId);
  }

  public Order findOrder(int orderId) {
    return em.find(Order.class, orderId);
  }

  public List<Product> findProductsInCategory(String category) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Product> query = cb.createQuery(Product.class);
    Root<Product> p = query.from(Product.class);
    query.select(p).where(cb.equal(p.get("category"), category));
    return em.createQuery(query).getResultList();
  }

  public void updatePrice(int productId, int newPrice) {
    Product product = findProduct(productId);
    product.setPrice(newPrice);
  }

  public void addProductToOrder(int orderId, int productId) {
    Order order = findOrder(orderId);
    Product product = findProduct(productId);
    order.addProduct(product);
  }
}
