package pl.training.jpa.model;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arkadiusz Parzych
 */
@Entity
//@Cacheable // activate second level cache
public class Client extends AbstractEntity {

  @OneToMany(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "client_id")
  private Set<Order> orders = new HashSet<>();

  public void addOrder(Order order) {
    orders.add(order);
  }

  public Set<Order> getOrders(){
    return orders;
  }
}
