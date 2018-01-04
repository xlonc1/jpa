package pl.training.jpa.service;

import pl.training.jpa.model.Order;
import pl.training.jpa.model.Product;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Arkadiusz Parzych
 */
public class OrderBuilder {

  private List<Product> products = new ArrayList<>();

  static OrderBuilder anOrder() {
    return new OrderBuilder();
  }

  OrderBuilder withProduct(Product product) {
    products.add(product);
    return this;
  }

  Order build() {
    Order order = new Order();
    products.forEach(order::addProduct);
    return order;
  }
}
