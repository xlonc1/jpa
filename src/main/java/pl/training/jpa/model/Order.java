package pl.training.jpa.model;

import org.hibernate.annotations.BatchSize;
import javax.persistence.CascadeType;
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
public class Order extends AbstractEntity {

  @OneToMany(cascade = CascadeType.PERSIST) // save products before flushing
  @JoinColumn(name="order_id")
  @BatchSize(size = 3)
  private Set<Product> products = new HashSet<>();

  public void addProduct(Product product){products.add(product);}

  public Set<Product> getProducts() {
    return products;
  }
}
