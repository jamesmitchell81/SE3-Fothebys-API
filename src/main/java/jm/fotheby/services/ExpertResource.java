package jm.fotheby.services;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;
import jm.fotheby.util.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import java.util.List;
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import java.io.*;
import javax.persistence.*;
import javax.persistence.criteria.*;

// JSON
import org.json.*;

@Path("/expert")
public class ExpertResource
{
  @POST
  @Consumes("application/json")
  public Response createNewExpert(String json)
  {
    Expert expert = this.buildExpert(json);

    JSONObject exp = new JSONObject();
    System.out.println(exp.toString());

    try {
      ExpertDAO dao = new ExpertDAO();
      dao.insert(expert);
    } catch (PersistenceException pe) {
      System.out.println(pe.getMessage());
      return Response.status(422).build();
    }

    return Response.created(URI.create("/expert/" + expert.getId())).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getExpert(@PathParam("id") int id)
  {
    Expert expert = new Expert();

    try {
      ExpertDAO dao = new ExpertDAO();
      expert = dao.get(id);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    JSONObject out = new JSONObject(expert);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        writer.println(out.toString());
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getExperts()
  {
    ExpertDAO dao = new ExpertDAO();
    List<Expert> list = dao.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        JSONArray out = new JSONArray();
        PrintStream writer = new PrintStream(ops);

        for ( Expert expert : list)
        {
          JSONObject expt = new JSONObject(expert);
          out.put(expt);
        }
        writer.println(out.toString());
      }
    };
  }

  @GET
  @Path("/search")
  @Consumes("application/json")
  @Produces("application/json")
  public StreamingOutput searchExperts(@DefaultValue("") @QueryParam("emailAddress") String emailAddress)
  {
    Database db = new Database();
    db.connect();
    EntityManager em = db.getEntityManager();
    CriteriaBuilder cb = em.getCriteriaBuilder();
    ParameterExpression<String> emailParam = cb.parameter(String.class);
    CriteriaQuery<ExpertSearchResult> cq = cb.createQuery(ExpertSearchResult.class);
    Root<Expert> expert = cq.from(Expert.class);
    CompoundSelection<ExpertSearchResult> selection = cb.construct(ExpertSearchResult.class,
                                                                   expert.get("id"),
                                                                   expert.get("title"),
                                                                   expert.get("firstName"),
                                                                   expert.get("surname"),
                                                                   expert.get("emailAddress"),
                                                                   expert.get("location"),
                                                                   expert.get("category"));
    Predicate predicate = cb.like(expert.get("emailAddress"), emailParam);
    cq.select(selection);
    cq.where(predicate);
    TypedQuery<ExpertSearchResult> query = em.createQuery(cq).setMaxResults(5);
    query.setParameter(emailParam, "%" + emailAddress.trim() + "%");

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        JSONArray out = new JSONArray();
        PrintStream writer = new PrintStream(ops);
        List<ExpertSearchResult> results = query.getResultList();

        for ( ExpertSearchResult result : results )
        {
          JSONObject e = new JSONObject(result);
          out.put(e);
        }

        writer.println(out.toString());
      }
    };
  }


  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response updateExpert(@PathParam("id") int id, String expert)
  {
    Expert update = this.buildExpert(expert);

    try {
      ExpertDAO dao = new ExpertDAO();
      dao.update(id, update);
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.serverError().build();
    }

    return Response.noContent().build();
  }

  private Expert buildExpert(String json)
  {
    JSONObject obj = new JSONObject(json);
    Expert expert = new Expert();

    System.out.println(obj.toString());

    try {
      JSONObject addr = obj.getJSONObject("contactAddress");
      Address address = new Address();
      address.setFirstLine(addr.getString("firstLine"));
      address.setSecondLine(addr.getString("secondLine"));
      address.setTownCity(addr.getString("townCity"));
      address.setPostalCode(addr.getString("postalCode"));

      JSONObject loc = obj.getJSONObject("location");
      LocationDAO locDAO = new LocationDAO();
      Location location = locDAO.get(loc.getInt("id"));

      JSONObject cat = obj.getJSONObject("category");
      CategoryDAO catDAO = new CategoryDAO();
      Category category = catDAO.get(cat.getInt("id"));

      JSONArray specialties = obj.getJSONArray("specialties");
      ClassificationDAO clsDAO = new ClassificationDAO();
      List<Integer> list = new ArrayList<Integer>();

      System.out.println(specialties.toString());

      for ( int i = 0; i < specialties.length(); i++ )
      {
        Classification classification = clsDAO.get(specialties.getJSONObject(i));
        System.out.println(classification.getId());
        list.add((Integer)classification.getId());
      }

      expert.setLocation(location);
      expert.setCategory(category);
      expert.setSpecialties(list);
      expert.setContactAddress(address);
      expert.setTitle(obj.getString("title"));
      expert.setFirstName(obj.getString("firstName"));
      expert.setSurname(obj.getString("surname"));
      expert.setEmailAddress(obj.getString("emailAddress"));
      expert.setTelNumber(obj.getString("telNumber"));
      expert.setRole(obj.getString("role"));

    } catch (Exception e) {
      // e.printStackTrace();
      System.out.println(e.getMessage());
    }

    return expert;
  }

}