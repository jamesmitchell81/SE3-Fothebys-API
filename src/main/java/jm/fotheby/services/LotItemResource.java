package jm.fotheby.services;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;
import jm.fotheby.factories.*;
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
    Item item = ItemFactory.buildItem(json);
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

    JSONObject obj = new JSONObject(json);



    return Response.created(URI.create("/lot-item/" + item.getId())).build();
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

}