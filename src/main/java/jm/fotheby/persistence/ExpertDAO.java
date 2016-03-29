package jm.fotheby.persistence;

import jm.fotheby.entities.Expert;
import javax.persistence.*;
import java.util.*;

public class ExpertDAO
{
  private EntityManager em;

  public ExpertDAO()
  {
    this.em = Persistence.createEntityManagerFactory("$objectdb/db/expert.odb")
                         .createEntityManager();
  }

  public ExpertDAO(EntityManager em)
  {
    this.em = em;
  }

  public void insert(Expert expert) throws PersistenceException
  {
    this.em.getTransaction().begin();
    this.em.persist(expert);
    this.em.getTransaction().commit();
  }

  public void update(int id, Expert update) throws PersistenceException
  {
    Expert current = this.get(id);

    this.em.getTransaction().begin();

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

    this.em.getTransaction().commit();
  }

  public List<Expert> get()
  {
    return this.em.createQuery("SELECT e FROM Expert e", Expert.class)
                  .getResultList();
  }

  public Expert get(int id) throws IllegalArgumentException
  {
    // TypedQuery<Expert> query = em.createQuery("SELECT DISTINCT e FROM Expert e WHERE id = :id", Expert.class);
    // query.setParameter("id", id);
    // List<Expert> list = query.getResultList();
    // return list.get(0);
    return this.em.find(Expert.class, id);
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