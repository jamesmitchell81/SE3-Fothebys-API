package jm.fotheby.services;

import jm.fotheby.entities.Country;
import jm.fotheby.persistence.CountryDAO;

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

  private CountryDAO dao;

  public CountryResource(EntityManager em)
  {
    this.dao = new CountryDAO(em);
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getCountry(@PathParam("id") int id)
  {
    Country country = this.dao.get(id);

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

    List<Country> countries = this.dao.get();

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