package jm.fotheby.services;

import jm.fotheby.entities.Location;
import jm.fotheby.entities.Address;
import jm.fotheby.entities.Country;
import jm.fotheby.util.HttpStatus;

import jm.fotheby.persistence.LocationDAO;
import jm.fotheby.persistence.CountryDAO;

import java.util.List;
import java.net.URI;
import javax.ws.rs.core.*;
import javax.ws.rs.*;
import java.io.*;
import javax.persistence.*;

// JSON
import org.json.*;

/*
REFERENCE:
JAX-RS implementation based on instruction from: (Burke, 2013)
*/

@Path("/location")
public class LocationResource
{

  @POST
  @Consumes("application/json")
  public Response createNewLocation(String json)
  {
    JSONObject obj = new JSONObject(json);
    if ( !obj.has("address") || !obj.has("country") )
      return Response.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

    Location location = this.buildLocation(obj);

    try {
      LocationDAO dao = new LocationDAO();
      dao.insert(location);
    } catch (PersistenceException e) {
      return Response.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    return Response.created(URI.create("/location/" + location.getId())).build();
  }

  @PUT
  @Path("{id}")
  @Consumes("application/json")
  public Response updateLocation(@PathParam("id") int id, String json)
  {
    JSONObject obj = new JSONObject(json);
    if ( !obj.has("address") || !obj.has("country") )
      return Response.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

    Location location = this.buildLocation(obj);

    try {
      LocationDAO dao = new LocationDAO();
      dao.update(id, location);
    } catch (PersistenceException e) {
      return Response.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
    }

    return Response.noContent().build();
  }

  @GET
  @Path("{id}")
  @Produces("application/json")
  public StreamingOutput getLocation(@PathParam("id") int id)
  {
    LocationDAO dao = new LocationDAO();
    Location location = dao.get(id);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject out = new JSONObject(location);
        writer.println(out.toString());
      }
    };
  }

  @GET
  @Path("/name/{name}")
  @Produces("application/json")
  public StreamingOutput getLocation(@PathParam("name") String name)
  {
    LocationDAO dao = new LocationDAO();
    Location location = dao.get(name);

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONObject out = new JSONObject(location);
        writer.println(out.toString());
      }
    };
  }

  @GET
  @Produces("application/json")
  public StreamingOutput getLocations()
  {
    LocationDAO dao = new LocationDAO();
    List<Location> locations = dao.get();

    return new StreamingOutput() {
      public void write(OutputStream ops) throws IOException, WebApplicationException
      {
        PrintStream writer = new PrintStream(ops);
        JSONArray out = new JSONArray(locations);

        for ( Location location : locations )
        {
          JSONObject loc = new JSONObject(location);
          out.put(loc);
        }
        writer.println(out.toString());
      }
    };
  }

  private Location buildLocation(JSONObject obj)
  {

    JSONObject addr = obj.getJSONObject("address");
    Address address = new Address();
    address.setFirstLine(addr.getString("firstLine"));
    address.setSecondLine(addr.getString("secondLine"));
    address.setTownCity(addr.getString("townCity"));
    address.setPostalCode(addr.getString("postalCode"));

    CountryDAO countryDAO = new CountryDAO();
    Country country = countryDAO.get(obj.getJSONObject("country"));

    Location location = new Location();
    location.setName(obj.getString("name"));
    location.setAddress(address);
    location.setCountry(country);
    location.setTelNumber(obj.getString("telNumber"));
    location.setCapacity(obj.getInt("capacity"));

    return location;
  }

}