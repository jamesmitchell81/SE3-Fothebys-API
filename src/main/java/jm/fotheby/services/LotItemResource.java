package jm.fotheby.services;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;
import jm.fotheby.util.*;

import java.lang.reflect.Field;

import java.util.*;
import java.net.URI;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.*;
import javax.persistence.*;
import org.json.*;

@Path("/lot-item")
public class LotItemResource
{
  @POST
  @Consumes("application/json")
  public Response createLotItem(String json)
  {
    Item item = this.buildItem(json);

    JSONObject obj = new JSONObject(item);
    System.out.println(obj.toString());

    Database db = new Database();
    db.connect();

    try {
      db.getEntityManager().getTransaction().begin();
      db.getEntityManager().persist(item);
      db.getEntityManager().getTransaction().commit();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.created(URI.create("/lot-items/" + item.getId())).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getLotItem(@PathParam("id") long id)
  {
    Database db = new Database();
    db.connect();

    List<Item> items = db.getEntityManager().createQuery("SELECT i FROM Item i", Item.class).getResultList();

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


  @GET
  @Produces("application/json")
  public StreamingOutput getLotItems()
  {
    Database db = new Database();
    db.connect();

    List<Item> items = db.getEntityManager().createQuery("SELECT i FROM Item i", Item.class).getResultList();

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

  private Item buildItem(String json)
  {
    Item item = new Item();
    JSONObject obj = new JSONObject(json);

    if ( obj.has("category") )
    {
      CategoryDAO catDAO = new CategoryDAO();
      Category category = catDAO.get(obj.getJSONObject("category"));
      item.setCategory(category);
    }

    if ( obj.has("classifications") )
    {
      JSONArray classifications = obj.getJSONArray("classifications");
      ClassificationDAO clsDAO = new ClassificationDAO();
      List<Classification> list = new ArrayList<Classification>();

      for ( int i = 0; i < classifications.length(); i++ )
      {
        Classification classification = clsDAO.get(classifications.getJSONObject(i));
        list.add(classification);
      }

      item.setClassifications(list);
    }

    if ( obj.has("attributes") )
    {
      Map<String, String> attributes = new HashMap<String, String>();
      JSONArray attrs = obj.getJSONArray("attributes");

      for ( int i = 0; i < attrs.length(); i++ )
      {
        String name = attrs.getJSONObject(i).getString("name");
        String value = attrs.getJSONObject(i).getString("value");
        attributes.put(name, value);
      }
      System.out.println("FIX SET ATTRIBUTES");
      // item.setAttributes(attributes);
    }

    if ( obj.has("dimensions") )
    {
      ItemDimensions dimensions = new ItemDimensions(obj.getJSONObject("dimensions"));
      item.setDimensions(dimensions);
    }

    if ( obj.has("productionDate") )
    {
      DatePeriod dp = new DatePeriod(obj.getJSONObject("productionDate"));
      item.setProductionDate(dp);
    }

    if ( obj.has("itemName") )
    {
      item.setItemName(obj.getString("itemName"));
    }

    if ( obj.has("textualDescription") )
    {
      item.setTextualDescription("textualDescription");
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

}