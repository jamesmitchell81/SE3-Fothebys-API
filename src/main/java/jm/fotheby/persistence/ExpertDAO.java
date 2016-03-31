package jm.fotheby.persistence;

import jm.fotheby.entities.*;
import jm.fotheby.util.*;

import javax.persistence.*;
import java.util.*;
import org.json.*;

public class ExpertDAO
{
  public void insert(Expert expert) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(expert);
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public void update(int id, Expert update) throws PersistenceException
  {
    Expert current = this.get(id);

    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();

    current.setLocation(update.getLocation());
    current.setCategory(update.getCategory());
    current.setSpecialties(update.getSpecialties());
    current.setContactAddress(update.getContactAddress());
    current.setTitle(update.getTitle());
    current.setFirstName(update.getFirstName());
    current.setSurname(update.getSurname());
    current.setEmailAddress(update.getEmailAddress());
    current.setTelNumber(update.getTelNumber());
    current.setRole(update.getRole());

    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public List<Expert> get()
  {
    Database db = new Database();
    db.connect();

    List<Expert> list = db.getEntityManager().createQuery("SELECT e FROM Expert e", Expert.class).getResultList();

    db.close();
    return list;
  }

  public Expert get(int id) throws IllegalArgumentException
  {
    Database db = new Database();
    db.connect();
    Expert expert = db.getEntityManager().find(Expert.class, id);
    db.close();
    return expert;
  }

  // public List<Expert> get(Classification cls)
  // {

  // }

  // public List<Expert> get(Category cat)
  // {

  // }

  // public List<Expert> get(Location loc)
  // {

  // }

}