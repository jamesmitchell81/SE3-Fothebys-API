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

    client.setTitle(in.optString("title"));
    client.setFirstName(in.optString("firstName"));
    client.setSurname(in.optString("surname"));

    if ( in.has("contactAddress") )
    {
      Address address = AddressFactory.buildAddress(in.getJSONObject("contactAddress").toString());
      client.setContactAddress(address);
    }

    client.setTelNumber(in.optString("telNumber"));
    client.setEmailAddress(in.optString("emailAddress"));

    CountryDAO dao = new CountryDAO();
    Country country = dao.getByShortCode(in.optString("country"));
    client.setCountry(country);

    return client;
  }
}