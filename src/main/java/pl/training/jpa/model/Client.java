package pl.training.jpa.model;

import javax.persistence.Entity;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Arkadiusz Parzych
 */
@Entity
public class Client {
  private Set<Order> orders = new HashSet<>();

  public void addOrders(Set<Order> orders) {
    this.orders = orders;
  }

  public Set<Order> getOrders(){
    return orders;
  }
}
