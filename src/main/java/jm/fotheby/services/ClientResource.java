package jm.fotheby.services;

// me
import jm.fotheby.entities.*;
import jm.fotheby.util.*;

// java
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;

import java.lang.StringBuilder;
import java.util.HashMap;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
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
import javax.persistence.TypedQuery;
import javax.persistence.PersistenceException;

// JSON
import org.json.*;

@Path("/clients")
public class ClientResource
{
  private EntityManagerFactory emf;
  private EntityManager em;

  public ClientResource()
  {
    this.emf = Persistence.createEntityManagerFactory("$objectdb/db/client.odb");
    this.em = emf.createEntityManager();
  }

// post.
  @POST
  @Consumes("application/json")
  public Response createClient(Client client)
  {

    try {
      em.getTransaction().begin();
      em.persist(client);
      em.getTransaction().commit();
    } catch (PersistenceException e) {
      return Response.status(422).build();
    }

    System.out.println(client.getId());

    // return Response.created(URI.create("/clients/" + client.getId())).build();
    return Response.ok(client).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getClient(@PathParam("id") int id)
  {
    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getClients()
  {
    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        JSONArray out = new JSONArray();
        PrintStream writer = new PrintStream(ops);
        List<Client> clientList = em.createQuery("SELECT c FROM Client c", Client.class).getResultList();

        for ( Client client : clientList )
        {
          JSONObject c = new JSONObject(client);
          out.put(c);
        }

        writer.println(out.toString());
      }
    };
  }

  @GET
  @Path("/search-client")
  @Consumes("application/json")
  @Produces("application/json")
  public StreamingOutput clientSearch(@QueryParam("emailAddress") String emailAddress,
                                      @QueryParam("surname") String surname,
                                      @QueryParam("telNumber") String telNumber)
  {
    HashMap<String, String> data = new HashMap<String, String>();
    StringBuilder query = new StringBuilder();

    data.put("emailAddress", emailAddress);
    data.put("surname", surname);
    data.put("telNumber", telNumber);

    query.append("SELECT NEW jm.fotheby.entities.ClientSearchResult(c.id, c.title, c.firstName, c.surname, c.contactAddress.firstLine) ");
    query.append("FROM Client c ");
    // query.append("WHERE ");

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        JSONArray out = new JSONArray();
        PrintStream writer = new PrintStream(ops);

        TypedQuery<ClientSearchResult> tquery = em.createQuery(query.toString(), ClientSearchResult.class);
        List<ClientSearchResult> results = tquery.getResultList();

        for ( ClientSearchResult result : results )
        {
          JSONObject c = new JSONObject(result);
          out.put(c);
        }

        writer.println(out.toString());
      }
    };
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