package jm.fotheby.services;

// me
import jm.fotheby.entities.Person;
import jm.fotheby.entities.Client;

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

import org.json.*;

@Path("/lot-items")
public class LotItemResource
{

  public LotItemResource() {}

// post.
  @POST
  @Consumes("application/json")
  public Response createLotItem(String json)
  {
    // input:
    // Item Name.
    // productionDate.
    // textualDescription.
    // category.
    // estimatedPrice.
    // ItemDimensions.
    JSONObject input = new JSONObject(json);


    return Response.created(URI.create("/lot-items/1")).build();
  }


}