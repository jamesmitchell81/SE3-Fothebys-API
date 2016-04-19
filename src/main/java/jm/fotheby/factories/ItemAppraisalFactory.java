package jm.fotheby.factories;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;

import org.json.*;

public class ItemAppraisalFactory
{

  public static ItemAppraisal buildAppraisal(String data)
  {
    JSONObject in = new JSONObject(data);
    ItemAppraisal ia = new ItemAppraisal();

    if ( in.has("client") )
    {
      ClientDAO cdao = new ClientDAO();
      Client client = cdao.get(in.getInt("client"));
      ia.setClient(client);
    }

    if ( in.has("expert") )
    {
      ExpertDAO exdao = new ExpertDAO();
      Expert expert = exdao.get(in.getInt("expert"));
      ia.setExpert(expert);
    }

    if ( in.has("appraisalDate") )
    {
      // make date
      // ia.setAppraisalDate();
    }

    if ( in.has("additionalNotes") )
    {
      ia.setAdditionalNotes(in.getString("additionalNotes"));
    }

    if ( in.has("agreement") )
    {
      ia.setAgreement(in.getBoolean("agreement"));
    }

    if ( in.has("estimatedPrice") )
    {
      ia.setEstimatedPrice(in.getDouble("estimatedPrice"));
    }

    if ( in.has("agreedPrice") )
    {
      ia.setAgreedPrice(in.getDouble("agreedPrice"));
    }

    return ia;
  }
}