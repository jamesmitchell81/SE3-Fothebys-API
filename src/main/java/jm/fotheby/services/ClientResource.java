package jm.fotheby.services;

// me
import jm.fotheby.entities.*;
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
  public Response createClient(Client client)
  {
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

  @GET
  @Path("/search-client")
  @Consumes("application/json")
  @Produces("application/json")
  public StreamingOutput clientSearch(@DefaultValue("") @QueryParam("emailAddress") String emailAddress,
                                      @DefaultValue("") @QueryParam("surname") String surname,
                                      @DefaultValue("") @QueryParam("telNumber") String telNumber)
  {
    // HashSet<String> whitelist = new HashSet();
    // whitelist.add("emailAddress")
    // whitelist.add("surname");
    // whitelist.add("telNumber");

    // MultivaluedMap<String, String> data = queryString.getQueryParameters();
    // HashMap<String, String> input = new HashMap();
    // Iterator iterator = data.keySet().iterator();

    // while ( iterator.hasNext() )
    // {
    //   String key = (String)iterator.next();
    //   if ( whitelist.contains(key) && data.getFirst(key) != "" )
    //   {
    //     input.put(key, data.getFirst(key));
    //   }
    // }

    Database db = new Database();
    db.connect();
    EntityManager em = db.getEntityManager();

    CriteriaBuilder cb = em.getCriteriaBuilder();

    ParameterExpression<String> emailParam = cb.parameter(String.class);
    ParameterExpression<String> surnameParam = cb.parameter(String.class);
    ParameterExpression<String> telParam = cb.parameter(String.class);

    CriteriaQuery<ClientSearchResult> cq = cb.createQuery(ClientSearchResult.class);
    Root<Client> client = cq.from(Client.class);

    CompoundSelection<ClientSearchResult> selection = cb.construct(ClientSearchResult.class,
                                                                   client.get("id"),
                                                                   client.get("title"),
                                                                   client.get("firstName"),
                                                                   client.get("surname"),
                                                                   client.get("contactAddress").get("firstLine"));

    Predicate[] or = new Predicate[3];
    or[0] = cb.equal(client.get("emailAddress"), emailParam);
    or[1] = cb.equal(client.get("surname"), surnameParam);
    or[2] = cb.equal(client.get("telNumber"), telParam);

    cq.select(selection);
    cq.where(cb.or(or));

    TypedQuery<ClientSearchResult> query = em.createQuery(cq);
    query.setParameter(emailParam, emailAddress.trim());
    query.setParameter(surnameParam, surname.trim());
    query.setParameter(telParam, telNumber.trim());

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