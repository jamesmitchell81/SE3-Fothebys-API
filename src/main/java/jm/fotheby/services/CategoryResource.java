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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

// JSON
import org.json.*;

@Path("/category")
public class CategoryResource
{

  @POST
  @Consumes("application/json")
  public Response createNewCategory(Category category)
  {
    // Category category = this.getCategory(json);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/category.odb");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(category);
      em.getTransaction().commit();
    } catch (PersistenceException pe) {
      return Response.status(422).build();
    }

    return Response.created(URI.create("/category/" + category.getId())).build();
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getCategories()
  {
    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/category.odb");
        EntityManager em = emf.createEntityManager();
        JSONArray out = new JSONArray();
        PrintStream writer = new PrintStream(ops);

        List<Category> categoryList = em.createQuery("SELECT c FROM Category c", Category.class).getResultList();

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

    System.out.println(update.getId());

    try {
      EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/category.odb");
      EntityManager em = emf.createEntityManager();

      Category current = em.find(Category.class, id);

      em.getTransaction().begin();
      current.setName(update.getName());
      current.setAttributes(update.getAttributes());
      em.getTransaction().commit();

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

}