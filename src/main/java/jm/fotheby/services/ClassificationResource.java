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

  @POST
  @Consumes("application/json")
  public Response addClassification(Classification classification)
  {

    try {
      ClassificationDAO dao = new ClassificationDAO();
      dao.insert(classification);
    } catch (PersistenceException e) {
      return Response.status(422).build();
    }

    return Response.created(URI.create("/classifications/1")).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getClassification(@PathParam("id") int id)
  {
    ClassificationDAO dao = new ClassificationDAO();
    Classification cls = dao.get(id);

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
    ClassificationDAO dao = new ClassificationDAO();
    List<Classification> classifications = dao.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONArray out = new JSONArray();

        for ( Classification classification : classifications)
        {
          JSONObject cls = new JSONObject(classification);
          out.put(cls);
        }
        writer.println(out.toString());
      }
    };
  }

}