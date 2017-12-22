package pl.training.jpa.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arkadiusz Parzych
 */
@Entity
@Table(name = "Orders")
public class Order {

  @OneToMany
  @JoinColumn(name="order_id")
  private Set<Product> products = new HashSet<>();

  public void addProduct(Product product){products.add(product);}

  public Set<Product> getProducts() {
    return products;
  }
}
