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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;

import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.WebApplicationException;

// IO
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.IOException;

// JPA
import javax.persistence.*;
import javax.persistence.criteria.*;

// JSON
import org.json.*;

@Path("/clients")
public class ClientResource
{
  private EntityManagerFactory emf;
  private EntityManager em;

  public ClientResource(EntityManager em)
  {
    // this.emf = Persistence.createEntityManagerFactory("$objectdb/db/client.odb");
    // this.em = emf.createEntityManager();
    this.em = em;
  }

  @POST
  @Consumes("application/json")
  @Produces("application/json")
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