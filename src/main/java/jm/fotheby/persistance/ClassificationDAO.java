package jm.fotheby.persistence;

import jm.fotheby.entities.Classification;
import javax.persistence.*;
import java.util.*;

import org.json.*;

public class ClassificationDAO
{
  private EntityManager em;

  public ClassificationDAO()
  {
    this.em = Persistence.createEntityManagerFactory("$objectdb/db/classification.odb")
                         .createEntityManager();
  }

  public ClassificationDAO(EntityManager em)
  {
    this.em = em;
  }

  public void insert(Classification classification) throws PersistenceException
  {
    this.em.getTransaction().begin();
    this.em.persist(classification);
    this.em.getTransaction().commit();
  }

  public List<Classification> get()
  {
    return this.em.createQuery("SELECT c FROM Classification c", Classification.class)
                  .getResultList();
  }

  public Classification get(int id)
  {
    return this.em.find(Classification.class, id);
  }

  public Classification get(JSONObject json)
  {
    Classification classification = new Classification();
    System.out.println(json.toString());

    if ( json.has("name") )
    {
      classification = this.get(json.getString("name"));
    }

    if ( json.has("id") )
    {
      classification = this.get(json.getInt("id"));
    }

    return classification;
  }

  public Classification get(String name)
  {
    TypedQuery<Classification> query = em.createQuery("SELECT DISTINCT c FROM Classification c WHERE name = '" + name + "'", Classification.class);
    query.setParameter("name", name);
    List<Classification> list = query.getResultList();
    return list.get(0);
  }
}

