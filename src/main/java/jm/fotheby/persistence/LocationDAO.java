package jm.fotheby.persistence;

import jm.fotheby.entities.*;
import jm.fotheby.util.*;

import java.util.List;
import javax.persistence.*;

import org.json.*;

public class LocationDAO
{
  public void insert(Location location) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(location);
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public void update(int id, Location location)  throws PersistenceException
  {
    Location current = this.get(id);

    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();

    current.setName(location.getName());
    current.setAddress(location.getAddress());
    current.setCountry(location.getCountry());
    current.setTelNumber(location.getTelNumber());
    current.setCapacity(location.getCapacity());

    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public List<Location> get()
  {
    Database db = new Database();
    db.connect();
    List<Location> list = db.getEntityManager().createQuery("SELECT l FROM Location l", Location.class)
                                 .getResultList();
    db.close();
    return list;
  }

  public Location get(int id)
  {
    Database db = new Database();
    db.connect();
    Location location = db.getEntityManager().find(Location.class, id);
    db.close();
    return location;
  }

  public Location get(String name)
  {
    Database db = new Database();
    db.connect();
    TypedQuery<Location> query = db.getEntityManager().createQuery("SELECT DISTINCT l FROM Location l WHERE name = :name", Location.class);
    query.setParameter("name", name);
    List<Location> list = query.getResultList();
    Location location = list.get(0);
    db.close();
    return location;
  }
}



