package jm.fotheby.services;

import jm.fotheby.entities.Country;
import jm.fotheby.persistence.CountryDAO;
import jm.fotheby.util.HttpStatus;

import java.util.List;
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import java.io.*;
import javax.persistence.*;

import org.json.*;

@Path("/countries")
public class CountryResource
{

  @POST
  @Consumes("application/json")
  public Response createCountry(Country country)
  {
    try {
      CountryDAO dao = new CountryDAO();
      dao.insert(country);
    } catch ( Exception e) {
      return Response.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    return Response.created(URI.create("/country/1")).build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getCountry(@PathParam("id") int id)
  {
    CountryDAO dao = new CountryDAO();
    Country country = dao.get(id);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream ps = new PrintStream(ops);
        JSONObject out = new JSONObject(country);
        ps.println(out.toString());
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getAllCountries()
  {
    CountryDAO dao = new CountryDAO();
    List<Country> countries = dao.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream ps = new PrintStream(ops);
        JSONArray out = new JSONArray();

        for ( Country country : countries )
        {
          JSONObject c = new JSONObject(country);
          out.put(c);
        }

        ps.println(out.toString());
      }
    };
  }

}