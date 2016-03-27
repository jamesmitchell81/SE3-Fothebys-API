package jm.fotheby.services;

import jm.fotheby.entities.*;

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

  public ItemImageResource(EntityManager em) { this.em = em; }

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


  // Reference: https://github.com/svanimpe/jaxrs-images/blob/master/src/java/resources/ImageService.java
  @POST
  @Path("{itemReference}/{filename}")
  @Consumes({"image/png", "image/jpg"})
  public Response createItemImage(InputStream ipstream,
                                  @HeaderParam("Content-Type") String filetype,
                                  @HeaderParam("Content-Length") int size,
                                  @PathParam("itemReference") Long itemReference,
                                  @PathParam("filename") String filename)
  {
    System.out.println(size);

    // if not item reference
    //  return badRequest.
    try {
      // byte[] image = IOUtils.toByteArray(ipstream);
      byte[] image = this.readImage(ipstream, size);

      ItemImage itemImage = new ItemImage(itemReference);
      itemImage.setImage(image);
      itemImage.setFilename(filename);

      em.getTransaction().begin();
      em.persist(itemImage);
      em.getTransaction().commit();
    } catch (PersistenceException e) {

      System.out.println(e.getMessage());
      return Response.status(422).build();
    }

    // return Response.created(URI.create("/lot-items/" + item.getId())).build();
    return Response.created(URI.create("/item-images/reference/filename/ext")).build();
  }

}