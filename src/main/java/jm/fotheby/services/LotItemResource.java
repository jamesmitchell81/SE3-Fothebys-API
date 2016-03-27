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
import java.io.InputStream;
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

  public Item buildItem(String json)
  {
    Item item = new Item();
    JSONObject obj = new JSONObject(json);

    if ( obj.has("catergory") )
    {
      Category category = CategoryResource.find(obj.getJSONObject("catergory").getString("name"));
      item.setCategory(category);
    }

    if ( obj.has("classifications") )
    {

    }

    if ( obj.has("attributes") )
    {

    }

    if ( obj.has("itemName") )
    {
      item.setItemName(obj.getString("itemName"));
    }

    if ( obj.has("productionDate") )
    {
      DatePeriod dp = new DatePeriod(obj.getJSONObject("productionDate"));
      item.setProductionDate(dp);
    }

    if ( obj.has("textualDescription") )
    {
      item.setTextualDescription("textualDescription");
    }

    if ( obj.has("dimensions") )
    {
      ItemDimensions dimensions = new ItemDimensions(obj.getJSONObject("dimensions"));
      item.setDimensions(dimensions);
    }

    if ( obj.has("provenanceDetails") )
    {
      item.setProvenanceDetails(obj.getString("provenanceDetails"));
    }

    if ( obj.has("authenticated") )
    {
      item.setAuthenticated(obj.getBoolean("authenticated"));
    }

    if ( obj.has("estimatedPrice") )
    {
      item.setEstimatedPrice(obj.getDouble("estimatedPrice"));
    }
    return item;
  }

  @POST
  @Consumes("application/json")
  public Response createLotItem(String json)
  {
    Item item = this.buildItem(json);
    // get classifications ...
    // get images ...
    // get attributes.

    try {
      this.em.getTransaction().begin();
      // this.em.persist(item);
      this.em.getTransaction().commit();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.created(URI.create("/lot-items/1")).build();
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getLotItems()
  {
    List<Item> items = em.createQuery("SELECT i FROM Item i", Item.class).getResultList();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);

        for ( Item item : items)
        {
          JSONObject out = new JSONObject(item);

          writer.println(out.toString());
        }
      }
    };
  }


}