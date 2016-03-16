package jm.fotheby.services;

import jm.fotheby.entities.Category;

// JAX-RS
import java.net.URI;
import javax.ws.rs.core.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

import javax.ws.rs.Path;

// JPA
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// JSON
import org.json.*;

@Path("/category")
public class CategoryResource
{

  @POST
  @Consumes("application/json")
  public Response createNewCategory(String json)
  {
    JSONObject input = new JSONObject(json);
    Category category = new Category();
    String name = input.get("name").toString();
    JSONArray attributes = input.getJSONArray("attributes");

    category.setName(name);

    for ( int i = 0; i < attributes.length(); i++ )
    {
      category.addAttribute(attributes.get(i).toString());
    }

    return Response.created(URI.create("/category/1")).build();
  }

}