package jm.fotheby.services;

import jm.fotheby.entities.*;
import jm.fotheby.util.Database;

import java.util.Base64;

import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import java.io.*;
import javax.persistence.*;

import org.json.*;

/*
REFERENCE:
JAX-RS implementation based on instruction from: (Burke, 2013)
*/

@Path("/item-images")
public class ItemImageResource
{
  private EntityManager em;


  @POST
  @Consumes("*/*")
  public Response createImage(String input,
                              @HeaderParam("Content-Type") String filetype,
                              @HeaderParam("Content-Length") long size)
  {
    JSONObject obj = new JSONObject(input);
    String imageData = obj.get("data").toString();
    byte[] decoded = Base64.getDecoder().decode(imageData);
    Image image = new Image();

    try {
      image.setFilename(obj.get("filename").toString());
      image.setExtension(obj.get("extension").toString());
      image.setSize(size);
      image.setImage(decoded);

      Database db = new Database();
      db.connect();
      db.getEntityManager().getTransaction().begin();
      db.getEntityManager().persist(image);
      db.getEntityManager().getTransaction().commit();
      db.close();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    // need to return the id of the stored image.
    return Response.created(URI.create("/item-images/" + image.getId())).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getImage(@PathParam("id") long id)
  {

    Database db = new Database();
    db.connect();
    Image image = db.getEntityManager().find(Image.class, id);
    // db.close();
    String encoded = Base64.getEncoder().encodeToString(image.getImage());

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject data = new JSONObject();

        String dataURL = "data:image/" + image.getExtension() + ";base64, " + encoded;
        data.put("id", image.getId());
        data.put("filename", image.getFilename());
        // data.put("data", encoded);
        data.put("extension", image.getExtension());
        data.put("dataURL", dataURL);

        writer.println(data.toString());
      }
    };
  }

  @DELETE
  @Path("{id}")
  public Response deleteImage(@PathParam("id") int id)
  {
    try {
      Database db = new Database();
      db.connect();
      Image image = db.getEntityManager().find(Image.class, id);
      db.getEntityManager().getTransaction().begin();
      db.getEntityManager().remove(image);
      db.getEntityManager().getTransaction().commit();
      db.close();
    } catch (PersistenceException e) {
      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    return Response.ok().build();
  }



}