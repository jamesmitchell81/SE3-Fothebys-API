package jm.fotheby.persistence;

import jm.fotheby.entities.*;
import jm.fotheby.util.*;

import javax.persistence.*;
import java.util.*;

import org.json.*;

public class ItemDAO
{
  public void insert(Item item) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(item);
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public Item update(int id, Item update) throws PersistenceException
  {
    Database db = new Database();
    db.connect();
    Item current = db.getEntityManager().find(Item.class, id);

    db.getEntityManager().getTransaction().begin();
    current.setCategory(update.getCategory());
    current.setClassifications(update.getClassifications());
    current.setImages(update.getImages());
    current.setAttributes(update.getAttributes());
    current.setDimensions(update.getDimensions());
    current.setProductionDate(update.getProductionDate());
    current.setItemName(update.getItemName());
    current.setTextualDescription(update.getTextualDescription());
    current.setProvenanceDetails(update.getProvenanceDetails());
    current.setAuthenticated(update.getAuthenticated());
    db.getEntityManager().getTransaction().commit();

    db.close();

    return current;
  }

  public void delete(int id) throws PersistenceException
  {
      Database db = new Database();
      db.connect();
      Item item = db.getEntityManager().find(Item.class, id);
      db.getEntityManager().getTransaction().begin();
      db.getEntityManager().remove(item);
      db.getEntityManager().getTransaction().commit();
      db.close();
  }

  public List<Item> get()
  {
    Database db = new Database();
    db.connect();
    List<Item> list = db.getEntityManager().createQuery("SELECT i FROM Item i", Item.class)
                                               .getResultList();
    return list;
  }

  public Item get(String name)
  {
    Database db = new Database();
    db.connect();
    TypedQuery<Item> query = db.getEntityManager().createQuery("SELECT DISTINCT i FROM Item c WHERE itemName = :name", Item.class);
    query.setParameter("name", name);
    Item item = query.getSingleResult();
    return item;
  }

  public Item get(int id)
  {
    Database db = new Database();
    db.connect();
    Item item = db.getEntityManager().find(Item.class, id);
    return item;
  }

}