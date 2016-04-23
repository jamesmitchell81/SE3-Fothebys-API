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

/*
REFERENCE:
JAX-RS implementation based on instruction from: (Burke, 2013)
*/

@Path("/lot-item")
public class LotItemResource
{

  @POST
  @Consumes("application/json")
  public Response createLotItem(String json)
  {
    Item item = ItemFactory.buildItem(json);
    ItemDAO dao = new ItemDAO();

    try {
      dao.insert(item);
    } catch (PersistenceException e) {
      return Response.status(422).build();
    }

    ItemAppraisal itemAppraisal = ItemAppraisalFactory.buildAppraisal(json);
    ItemAppraisalDAO iaDAO = new ItemAppraisalDAO();
    itemAppraisal.setItem(item);

    try {
      iaDAO.insert(itemAppraisal);
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.created(URI.create("/lot-item/" + item.getId())).build();
  }

  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response createLotItem(@PathParam("id") int id, String json)
  {
    Item update = ItemFactory.buildItem(json);
    ItemAppraisal iaUpdate = ItemAppraisalFactory.buildAppraisal(json);
    Item current = new Item();
    ItemDAO dao = new ItemDAO();
    ItemAppraisalDAO iaDAO = new ItemAppraisalDAO();

    try {
      current = dao.update(id, update);
    } catch (PersistenceException e) {
      return Response.status(422).build();
    }

    try {
      iaDAO.update(id, iaUpdate);
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
    }

    return Response.noContent().build();
  }

  @GET
  @Path("/item-appraisal/{id}")
  @Consumes("application/json")
  public StreamingOutput searchLotItems(@PathParam("id") int id)
  {
    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        ItemAppraisal itemAppraisal = new ItemAppraisal();
        ItemAppraisalDAO iaDAO = new ItemAppraisalDAO();

        try {
          itemAppraisal = iaDAO.get(id);
        } catch (PersistenceException e) {
          System.out.println(e.getMessage());
        }

        PrintStream writer = new PrintStream(ops);
        JSONObject out = new JSONObject(itemAppraisal);
        writer.println(out.toString());
      }
    };
  }

  @GET
  @Path("/search")
  @Consumes("application/json")
  public StreamingOutput searchLotItems(String searchCriteria)
  {
    ItemDAO iaDAO = new ItemDAO();
    List<Item> items = iaDAO.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONArray out = new JSONArray();

        for ( Item item : items)
        {
          JSONObject obj = new JSONObject(item);
          out.put(obj);
        }
        writer.println(out.toString());
      }
    };
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getLotItem(@PathParam("id") int id)
  {
    ItemDAO dao = new ItemDAO();
    Item item = dao.get(id);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject out = new JSONObject(item);
        writer.println(out.toString());
      }
    };
  }


  @GET
  @Produces("application/json")
  public StreamingOutput getLotItems()
  {
    ItemDAO dao = new ItemDAO();
    List<Item> items = dao.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONArray out = new JSONArray();

        for ( Item item : items)
        {
          JSONObject obj = new JSONObject(item);
          out.put(obj);
        }

        writer.println(out.toString());
      }
    };
  }

  @DELETE
  @Path("{id}")
  public Response deleteItem(@PathParam("id") int id)
  {
    try {
      ItemDAO dao = new ItemDAO();
      dao.delete(id);
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.ok().build();
  }

}