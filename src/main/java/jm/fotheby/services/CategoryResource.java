package jm.fotheby.services;

import jm.fotheby.entities.Category;
import jm.fotheby.persistence.CategoryDAO;
import jm.fotheby.util.Database;

import java.util.List;
import java.util.Set;
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import java.io.*;
import javax.persistence.*;

import org.json.*;

@Path("/category")
public class CategoryResource
{

  @POST
  @Consumes("application/json")
  public Response createNewCategory(Category category)
  {
    try {
      CategoryDAO dao = new CategoryDAO();
      dao.insert(category);
    } catch (PersistenceException pe) {
      return Response.status(422).build();
    }

    return Response.created(URI.create("/category/" + category.getId())).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getCategory(@PathParam("id") int id)
  {
    CategoryDAO dao = new CategoryDAO();
    Category category = dao.get(id);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject cat = new JSONObject(category);
        writer.println(cat.toString());
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getCategories()
  {
    CategoryDAO dao = new CategoryDAO();

    JSONArray out = dao.getJSON();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
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
      CategoryDAO dao = new CategoryDAO();
      dao.update(id, update);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return Response.serverError().build();
    }

    return Response.noContent().build();
  }

  private Category createCategory(String json)
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