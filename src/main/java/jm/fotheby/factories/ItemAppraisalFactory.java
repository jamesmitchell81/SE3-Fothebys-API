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

    ia.setAdditionalNotes(in.optString("additionalNotes"));
    ia.setAgreement(in.optBoolean("agreement", false));
    ia.setEstimatedPrice(in.optDouble("estimatedPrice"));
    ia.setAgreedPrice(in.optDouble("agreedPrice"));

    return ia;
  }
}