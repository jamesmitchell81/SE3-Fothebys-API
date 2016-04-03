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

@Path("/item-images")
public class ItemImageResource
{
  private EntityManager em;

  public ItemImageResource() {}

  // Reference: http://stackoverflow.com/questions/1264709/convert-inputstream-to-byte-array-in-java
  private byte[] readImage(InputStream ipstream, int length)
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    int read;
    int offset = 0;
    byte[] data = new byte[length];

    try {
    // java: https://docs.oracle.com/javase/7/docs/api/java/io/InputStream.html
    // read(byte[] b, int offset, int length)
    // Reads up to len bytes of data from the input stream into an array of bytes.
    while ( (read = ipstream.read(data, offset, length)) != -1 )
    {
      // java: https://docs.oracle.com/javase/7/docs/api/java/io/ByteArrayOutputStream.html
      // write(byte[] data, int offset, int length)
      // Writes len bytes from the specified byte array starting at offset off to this byte array output stream.
      out.write(data, offset, read);
    }
    // out.flush();

    } catch (IOException e) {
      System.out.println(e.getMessage());
    }

    return out.toByteArray();
  }

  @POST
  @Consumes("*/*")
  // public Response createImage(InputStream ipstream,
  //                             @HeaderParam("Content-Type") String filetype,
  //                             @HeaderParam("Content-Length") int size)
  public Response createImage(String input,
                              @HeaderParam("Content-Type") String filetype,
                              @HeaderParam("Content-Length") long size)
  {

    byte[] decoded = Base64.getDecoder().decode(input);
    Image image = new Image();

    // System.out.println(image);
    try {
      // byte[] image = this.readImage(ipstream, size);
      image.setFilename("james");
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
    db.close();
    String encoded = Base64.getEncoder().encodeToString(image.getImage());

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject data = new JSONObject();

        data.put("id", image.getId());
        data.put("filename", image.getFilename());
        data.put("data", encoded);

        writer.println(data.toString());
      }
    };
  }

  @DELETE
  @Path("{id}")
  public Response deleteImage(@PathParam("id") int id)
  {
    Database db = new Database();
    db.connect();

    Image image = db.getEntityManager().find(Image.class, id);

    db.getEntityManager().getTransaction().begin();
    db.getEntityManager().remove(image);
    db.getEntityManager().getTransaction().commit();
    db.close();

    return Response.ok().build();
  }



}