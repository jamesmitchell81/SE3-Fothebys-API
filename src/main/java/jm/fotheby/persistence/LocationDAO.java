package jm.fotheby.persistence;

import jm.fotheby.entities.*;

import java.util.List;
import javax.persistence.*;

import org.json.*;

public class LocationDAO
{

  private EntityManager em;

  public LocationDAO()
  {
    this.em = Persistence.createEntityManagerFactory("$objectdb/db/location.odb")
                         .createEntityManager();
  }

  public LocationDAO(EntityManager em)
  {
    this.em = em;
  }


  public void insert(Location location) throws PersistenceException
  {
    this.em.getTransaction().begin();
    this.em.persist(location);
    this.em.getTransaction().commit();
  }

  public void update(int id, Location location)  throws PersistenceException
  {
    Location current = this.get(id);

    this.em.getTransaction().begin();

    current.setName(location.getName());
    current.setAddress(location.getAddress());
    current.setCountry(location.getCountry());
    current.setTelNumber(location.getTelNumber());
    current.setCapacity(location.getCapacity());

    this.em.getTransaction().commit();
  }

  public List<Location> get()
  {
    return this.em.createQuery("SELECT l FROM Location l", Location.class)
                  .getResultList();
  }

  public Location get(int id)
  {
    return this.em.find(Location.class, id);
  }

  public Location get(String name)
  {
    TypedQuery<Location> query = this.em.createQuery("SELECT DISTINCT l FROM Location l WHERE name = :name", Location.class);
    query.setParameter("name", name);
    List<Location> list = query.getResultList();
    return list.get(0);
  }
}