package jm.fotheby.services;

// me
import jm.fotheby.entities.*;
import jm.fotheby.factories.*;
import jm.fotheby.util.*;

// java
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;
import java.lang.StringBuilder;
import java.util.*;

// JAX-RS
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;

// IO
import java.io.*;

// JPA
import javax.persistence.*;
import javax.persistence.criteria.*;

// JSON
import org.json.*;

@Path("/clients")
public class ClientResource
{

  @POST
  @Consumes("application/json")
  public Response createClient(String clientString)
  {
    Client client = ClientFactory.buildBasicClient(clientString);

    Database db = new Database();
    db.connect();
    EntityManager em = db.getEntityManager();

    try {
      em.getTransaction().begin();
      em.persist(client);
      em.getTransaction().commit();
    } catch (PersistenceException e) {
      return Response.status(422).build();
    }
    return Response.created(URI.create("/clients/" + client.getId())).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getClient(@PathParam("id") int id)
  {
    Database db = new Database();
    db.connect();
    Client client = db.getEntityManager().find(Client.class, id);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject c = new JSONObject(client);
        writer.println(c.toString());
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getClients()
  {

    Database db = new Database();
    db.connect();
    EntityManager em = db.getEntityManager();

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

  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response updateClient(@PathParam("id") int id, String input)
  {
    try {
      Database db = new Database();
      db.connect();
      Client current = db.getEntityManager().find(Client.class, id);
      Client update = ClientFactory.buildBasicClient(input);
      db.getEntityManager().getTransaction().begin();
      current.setTitle(update.getTitle());
      current.setFirstName(update.getFirstName());
      current.setSurname(update.getSurname());
      current.setTelNumber(update.getTelNumber());
      current.setContactAddress(update.getContactAddress());
      current.setEmailAddress(update.getEmailAddress());
      current.setCountry(update.getCountry());
      db.getEntityManager().getTransaction().commit();
      db.close();
    } catch (PersistenceException e) {
      return Response.serverError().build();
    }

    return Response.noContent().build();
  }

  @GET
  @Path("/search-client")
  @Consumes("application/json")
  @Produces("application/json")
  public StreamingOutput clientSearch(@DefaultValue("") @QueryParam("emailAddress") String emailAddress)
  {
    Database db = new Database();
    db.connect();
    EntityManager em = db.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    ParameterExpression<String> emailParam = cb.parameter(String.class);
    CriteriaQuery<ClientSearchResult> cq = cb.createQuery(ClientSearchResult.class);
    Root<Client> client = cq.from(Client.class);
    CompoundSelection<ClientSearchResult> selection = cb.construct(ClientSearchResult.class,
                                                                   client.get("id"),
                                                                   client.get("title"),
                                                                   client.get("firstName"),
                                                                   client.get("surname"),
                                                                   client.get("emailAddress"),
                                                                   client.get("contactAddress").get("firstLine"));
    Predicate predicate = cb.like(client.get("emailAddress"), emailParam);
    cq.select(selection);
    cq.where(predicate);
    TypedQuery<ClientSearchResult> query = em.createQuery(cq).setMaxResults(5);
    query.setParameter(emailParam, "%" + emailAddress.trim() + "%");

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        JSONArray out = new JSONArray();
        PrintStream writer = new PrintStream(ops);
        List<ClientSearchResult> results = query.getResultList();

        for ( ClientSearchResult result : results )
        {
          JSONObject c = new JSONObject(result);
          out.put(c);
        }

        writer.println(out.toString());
      }
    };
  }

}