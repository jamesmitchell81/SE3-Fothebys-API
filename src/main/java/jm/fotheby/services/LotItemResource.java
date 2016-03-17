package jm.fotheby.services;

// me
import jm.fotheby.entities.*;

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

import org.json.*;

@Path("/lot-item")
public class LotItemResource
{

  public LotItemResource() {}

  @POST
  @Consumes("application/json")
  public Response createLotItem(LotItem item)
  {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/lot-item.odb");
    EntityManager em = emf.createEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(item);
      em.getTransaction().commit();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    // Category c = item.getCategory();
    // System.out.println("! " + c.getId());

    return Response.created(URI.create("/lot-items/" + item.getId())).build();
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getLotItems()
  {
    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("$objectdb/db/lot-item.odb");
        EntityManager em = emf.createEntityManager();
        List<LotItem> items = em.createQuery("SELECT i FROM LotItem i", LotItem.class).getResultList();

        for ( LotItem item : items)
        {
          JSONObject out = new JSONObject(items);
          PrintStream writer = new PrintStream(ops);

          writer.println(out.toString());
        }
      }
    };
  }


}