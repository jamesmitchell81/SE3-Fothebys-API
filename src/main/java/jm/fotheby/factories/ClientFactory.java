package jm.fotheby.factories;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;
import org.json.*;

public class ClientFactory
{
  public static Client buildBasicClient(String clientString)
  {
    JSONObject in = new JSONObject(clientString);
    Client client = new Client();

    if ( in.has("title") )
      client.setTitle(in.getString("title"));

    if ( in.has("firstName") )
      client.setTitle(in.getString("firstName"));

    if ( in.has("surname") )
      client.setTitle(in.getString("surname"));

    if ( in.has("contactAddress") )
    {
      Address address = AddressFactory.buildAddress(in.getJSONObject("contactAddress").toString());
      client.setContactAddress(address);
    }

    if ( in.has("telNumber") )
      client.setTitle(in.getString("telNumber"));

    if ( in.has("emailAddress") )
      client.setTitle(in.getString("emailAddress"));

    if ( in.has("country") )
    {
      CountryDAO dao = new CountryDAO();
      Country country = dao.getByShortCode(in.getString("country"));
    }

    return client;
  }
}