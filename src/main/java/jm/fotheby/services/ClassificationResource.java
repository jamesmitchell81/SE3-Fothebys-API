package jm.fotheby.services;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;

import java.util.List;
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import java.io.*;
import javax.persistence.*;

import org.json.*;

@Path("/classification")
public class ClassificationResource
{
  private ClassificationDAO dao;

  public ClassificationResource(EntityManager em) {
    this.dao = new ClassificationDAO(em);
  }

  @POST
  @Consumes("application/json")
  public Response addClassification(Classification classification)
  {

    try {
      this.dao.insert(classification);
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.created(URI.create("/classifications/1")).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getClassification(@PathParam("id") int id)
  {
    Classification cls = this.dao.get(id);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream write = new PrintStream(ops);
        JSONObject out = new JSONObject(cls);
        write.println(out.toString());
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getClassifications()
  {
    List<Classification> classifications = this.dao.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);

        for ( Classification classification : classifications)
        {
          JSONObject out = new JSONObject(classification);
          writer.println(out.toString());
        }
      }
    };
  }

}