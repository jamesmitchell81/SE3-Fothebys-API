package jm.fotheby.factories;

import jm.fotheby.entities.*;
import org.json.*;

public class AddressFactory
{

  public static Address buildAddress(String addressString)
  {
    JSONObject in = new JSONObject(addressString);
    Address address = new Address();

    if ( in.has("firstLine") )
    {
      address.setFirstLine(in.getString("firstLine"));
    }

    if ( in.has("secondLine") )
    {
      address.setSecondLine(in.getString("secondLine"));
    }

    if ( in.has("townCity") )
    {
      address.setTownCity(in.getString("townCity"));
    }

    if ( in.has("postalCode") )
    {
      address.setPostalCode(in.getString("postalCode"));
    }

    return address;
  }
}