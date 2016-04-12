package jm.fotheby.persistence;

import jm.fotheby.entities.Category;
import jm.fotheby.util.*;

import javax.persistence.*;
import java.util.*;

import org.json.*;

public class CategoryDAO
{

  public void insert(Category category) throws PersistenceException
  {
    Database db = new Database();
    db.connect();

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().persist(category);
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public void update(int id, Category update) throws PersistenceException
  {
    Database db = new Database();
    db.connect();
    Category current = db.getEntityManager().find(Category.class, id);

    db.getEntityManager().getTransaction().begin();
    current.setName(update.getName());
    current.setAttributes(update.getAttributes());
    db.getEntityManager().getTransaction().commit();

    db.close();
  }

  public List<Category> get()
  {
    Database db = new Database();
    db.connect();
    List<Category> list = db.getEntityManager().createQuery("SELECT c FROM Category c", Category.class)
                                               .getResultList();
    db.close();
    return list;
  }

  public JSONArray getJSON()
  {
    Database db = new Database();
    JSONArray output = new JSONArray();
    db.connect();
    List<Category> list = db.getEntityManager().createQuery("SELECT c FROM Category c", Category.class)
                                               .getResultList();

    for ( Category category : list)
    {
      JSONObject cat = new JSONObject(category);
      output.put(cat);
    }

    db.close();
    return output;
  }

  public Category get(String name)
  {
    Database db = new Database();
    db.connect();
    TypedQuery<Category> query = db.getEntityManager().createQuery("SELECT DISTINCT c FROM Category c WHERE name = :name", Category.class);
    query.setParameter("name", name);
    List<Category> list = query.getResultList();
    Category cat = list.get(0);
    db.close();
    return cat;
  }

  public Category get(int id)
  {
    Database db = new Database();
    db.connect();
    Category cat = db.getEntityManager().find(Category.class, id);
    db.close();
    return cat;
  }

  public Category get(JSONObject json)
  {
    Category category = new Category();

    if ( json.has("name") )
    {
      category = this.get(json.getString("name"));
    }

    if ( json.has("id") )
    {
      category = this.get(json.getInt("id"));
    }

    return category;
  }

}