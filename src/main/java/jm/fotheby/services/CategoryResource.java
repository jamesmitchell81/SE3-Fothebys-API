package jm.fotheby.services;

import jm.fotheby.entities.Category;

import java.util.List;

// JAX-RS
import java.net.URI;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.WebApplicationException;

// IO
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

// JPA
import javax.persistence.*;

// JSON
import org.json.*;

@Path("/category")
public class CategoryResource
{

  private EntityManager em;

  public CategoryResource(EntityManager em)
  {
    this.em = em;
  }

  @POST
  @Consumes("application/json")
  public Response createNewCategory(Category category)
  {
    try {
      this.em.getTransaction().begin();
      this.em.persist(category);
      this.em.getTransaction().commit();
    } catch (PersistenceException pe) {
      return Response.status(422).build();
    }

    return Response.created(URI.create("/category/" + category.getId())).build();
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getCategories()
  {
    JSONArray out = new JSONArray();
    List<Category> categoryList = this.em.createQuery("SELECT c FROM Category c", Category.class)
                                         .getResultList();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);

        for ( Category category : categoryList)
        {
          JSONObject cat = new JSONObject(category);
          out.put(cat);
        }
        writer.println(out.toString());
      }
    };
  }

  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response updateCategory(@PathParam("id") int id, Category update)
  {

    try {
      Category current = this.em.find(Category.class, id);

      this.em.getTransaction().begin();
      current.setName(update.getName());
      current.setAttributes(update.getAttributes());
      this.em.getTransaction().commit();

    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.serverError().build();
    }

    return Response.noContent().build();
  }

  private Category getCategory(String json)
  {
    Category category = new Category();
    JSONObject input = new JSONObject(json);
    String name = input.get("name").toString();
    JSONArray attributes = input.getJSONArray("attributes");
    category.setName(name);

    for ( int i = 0; i < attributes.length(); i++ )
      category.addAttribute(attributes.get(i).toString());

    return category;
  }

  public static Category find(int id)
  {
    EntityManager em = Persistence.createEntityManagerFactory("$objectdb/db/category.odb").createEntityManager();
    return em.find(Category.class, id);
  }

  public static Category find(String name)
  {
    EntityManager em = Persistence.createEntityManagerFactory("$objectdb/db/category.odb").createEntityManager();
    TypedQuery<Category> query = em.createQuery("SELECT DISTINCT c FROM Category c WHERE name = '" + name + "'", Category.class);
    query.setParameter("name", name);
    List<Category> list = query.getResultList();

    return list.size() == 0 ? new Category() : list.get(0);
  }

}