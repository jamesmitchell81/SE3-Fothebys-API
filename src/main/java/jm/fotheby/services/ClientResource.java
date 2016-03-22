package jm.fotheby.services;

// me
import jm.fotheby.entities.*;
import jm.fotheby.util.*;

// java
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;

// JAX-RS
import java.net.URI;
import javax.ws.rs.core.Response;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
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

  @GET
  @Path("/address-template")
  @Produces("application/json")
  public String getAddressTemplate()
  {
    Person p = new Person();
    FormTemplate ft = new FormTemplate();
    Class cls = p.getClass();
    return ft.getTemplate(cls).toString();
  }

}