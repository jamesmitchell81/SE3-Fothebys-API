package jm.fotheby.services;

import jm.fotheby.entities.*;

import java.util.List;
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*
import java.io.*;
import javax.persistence.*;

import org.json.*;

@Path("/classifications")
public class ClassificationResource
{
  private EntityManager em;

  public ClassificationResource(EntityManager em) { this.em = em; }

  @POST
  @Consumes("application/json")
  public Response addClassification(Classification classification)
  {

    try {
      this.em.getTransaction().begin();
      this.em.persist(classification);
      this.em.getTransaction().commit();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.created(URI.create("/classifications/" + classification.getId())).build();
  }

}