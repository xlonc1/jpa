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
@RequiredArgsConstructor
@Entity
public class Product {
  private String name;
  private String category;
  private int price;
}
