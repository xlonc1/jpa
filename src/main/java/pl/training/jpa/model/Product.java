package pl.training.jpa.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author Arkadiusz Parzych
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
  private String name;
  private String category;
  private int price;

  public Product(String name, String category, int price) {
    this.name = name;
    this.category = category;
    this.price = price;
  }
}
