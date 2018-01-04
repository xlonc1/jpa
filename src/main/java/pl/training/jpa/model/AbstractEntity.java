package pl.training.jpa.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Opis
 *
 * @author Arkadiusz Parzych
 * @since 04.01.18
 */
@MappedSuperclass
public abstract class AbstractEntity {

  @Id
  @GeneratedValue
  private Integer id;

  public Integer getId() {
    return id;
  }
}
