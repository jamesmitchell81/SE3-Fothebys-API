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
  private EntityManager em;

  public LotItemResource(EntityManager em) { this.em = em; }

  @POST
  @Consumes("application/json")
  public Response createLotItem(LotItem item)
  {

    try {
      this.em.getTransaction().begin();
      this.em.persist(item);
      this.em.getTransaction().commit();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    // Category c = item.getCategory();
    // System.out.println("! " + c.getId());

    // return Response.created(URI.create("/lot-items/" + item.getId())).build();
    return Response.created(URI.create("/lot-items/1")).build();
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getLotItems()
  {
    List<LotItem> items = em.createQuery("SELECT i FROM LotItem i", LotItem.class).getResultList();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {

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