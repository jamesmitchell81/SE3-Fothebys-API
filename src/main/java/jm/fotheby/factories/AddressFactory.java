package jm.fotheby.factories;

import jm.fotheby.entities.*;
import org.json.*;

public class AddressFactory
{

  public static Address buildAddress(String addressString)
  {
    JSONObject in = new JSONObject(addressString);
    Address address = new Address();

    address.setFirstLine(in.optString("firstLine"));
    address.setSecondLine(in.optString("secondLine"));
    address.setTownCity(in.optString("townCity"));
    address.setPostalCode(in.optString("postalCode"));

    return address;
  }
}