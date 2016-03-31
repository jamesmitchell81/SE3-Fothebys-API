package jm.fotheby.persistence;

import jm.fotheby.entities.Classification;
import jm.fotheby.util.*;

import javax.persistence.*;
import java.util.*;

import org.json.*;

public class ClassificationDAO
{
  public void insert(Classification classification) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(classification);
    db.getEntityManager().getTransaction().commit();
  }

  public List<Classification> get()
  {
    Database db = new Database();
    db.connect();
    List<Classification> list = db.getEntityManager().createQuery("SELECT c FROM Classification c", Classification.class)
                  .getResultList();
    db.close();
    return list;
  }

  public Classification get(int id)
  {
    Database db = new Database();
    db.connect();
    Classification cls = db.getEntityManager().find(Classification.class, id);
    db.close();
    return cls;
  }

  public Classification get(JSONObject json)
  {
    Classification classification = new Classification();

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
    Database db = new Database();
    db.connect();
    TypedQuery<Classification> query = db.getEntityManager().createQuery("SELECT DISTINCT c FROM Classification c WHERE name = '" + name + "'", Classification.class);
    query.setParameter("name", name);
    List<Classification> list = query.getResultList();
    Classification cls = list.get(0);
    db.close();
    return cls;
  }
}

