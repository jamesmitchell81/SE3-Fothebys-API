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

@Path("/clients")
public class ClientResource
{

  public ClientResource() {}

// post.
  @POST
  @Consumes("application/json")
  public Response createClient(String json)
  {
    Client client = new Client();

    return Response.created(URI.create("/clients/1")).build();
  }



// put/update.
// get.
// get single by id : "/clients/{id}"


// get single by username : "/clients/{username}"
// get all          : "/clients"
}