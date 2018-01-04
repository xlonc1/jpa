package pl.training.jpa.service;

import org.springframework.stereotype.Service;
import pl.training.jpa.model.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * @author Arkadiusz Parzych
 */
@Service
public class ClientService {

  @PersistenceContext
  private EntityManager em;

  @Transactional(Transactional.TxType.REQUIRES_NEW)
  public void saveClient(Client client) {
    em.persist(client);
  }

  public Client findClient(int clientId) {
    return em.createQuery(
        "select c from Client c join fetch c.orders o join fetch o.products where c.id = :id", Client.class) //join fetch reslove n+1 problem
        .setParameter("id", clientId)
        .getSingleResult();
  }
}
