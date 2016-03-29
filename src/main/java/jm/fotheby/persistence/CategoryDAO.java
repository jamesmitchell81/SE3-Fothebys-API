package jm.fotheby.persistence;

import jm.fotheby.entities.Category;
import javax.persistence.*;
import java.util.*;

import org.json.*;

public class CategoryDAO
{

  private EntityManager em;

  public CategoryDAO()
  {
    this.em = Persistence.createEntityManagerFactory("$objectdb/db/category.odb")
                         .createEntityManager();
  }

  public CategoryDAO(EntityManager em)
  {
    this.em = em;
  }

  public void insert(Category category) throws PersistenceException
  {
    this.em.getTransaction().begin();
    this.em.persist(category);
    this.em.getTransaction().commit();
  }

  public void update(int id, Category update) throws PersistenceException
  {
      Category current = this.em.find(Category.class, id);

      this.em.getTransaction().begin();
      current.setName(update.getName());
      current.setAttributes(update.getAttributes());
      this.em.getTransaction().commit();
  }

  public List<Category> get()
  {
    return this.em.createQuery("SELECT c FROM Category c", Category.class)
                  .getResultList();
  }

  public Category get(String name)
  {
    TypedQuery<Category> query = em.createQuery("SELECT DISTINCT c FROM Category c WHERE name = :name", Category.class);
    query.setParameter("name", name);
    List<Category> list = query.getResultList();
    return list.get(0);
  }

  public Category get(int id)
  {
    return this.em.find(Category.class, id);
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