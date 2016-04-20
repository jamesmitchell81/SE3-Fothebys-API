package jm.fotheby.factories;

import jm.fotheby.entities.*;
import jm.fotheby.persistence.*;
import jm.fotheby.util.*;

import java.util.*;

import org.json.*;

public class ItemFactory
{
  public static Item buildItem(String data)
  {
    Item item = new Item();
    JSONObject obj = new JSONObject(data);

    if ( obj.has("category") )
    {
      JSONObject co = new JSONObject(obj.getString("category"));
      CategoryDAO catDAO = new CategoryDAO();
      Category category = catDAO.get(co);
      item.setCategory(category);
    }

    if ( obj.has("classifications") )
    {
      JSONArray classifications = new JSONArray(obj.getString("classifications"));
      ClassificationDAO clsDAO = new ClassificationDAO();
      List<Classification> list = new ArrayList<Classification>();

      for ( int i = 0; i < classifications.length(); i++ )
      {
        Classification classification = clsDAO.get(classifications.getJSONObject(i));
        list.add(classification);
      }

      item.setClassifications(list);
    }

    if ( obj.has("images") )
    {
      JSONArray imageIds = new JSONArray(obj.getString("images"));
      int id = imageIds.getInt(0);
      ArrayList<Long> imageList = new ArrayList();
      Database db = new Database();
      db.connect();
      for ( int i = 0; i < imageIds.length(); i++ )
      {
        id = imageIds.getInt(i);
        Image image = db.getEntityManager().find(Image.class, id);
        ItemImage itemImage = new ItemImage();
        itemImage.setImage(image);

        db.getEntityManager().getTransaction().begin();
        db.getEntityManager().persist(itemImage);
        db.getEntityManager().getTransaction().commit();

        imageList.add(itemImage.getId());
      }
      item.setImages(imageList);
      db.close();
    }

    if ( obj.has("attributes") )
    {
      Set<ItemAttribute> attributes = new HashSet<ItemAttribute>();
      JSONArray attrs = new JSONArray(obj.getString("attributes"));

      for ( int i = 0; i < attrs.length(); i++ )
      {
        String name = attrs.getJSONObject(i).getString("name");
        Object value = attrs.getJSONObject(i).get("value");

        ItemAttribute attr = new ItemAttribute(name, value);
        attributes.add(attr);
      }
      item.setAttributes(attributes);
    }

    if ( obj.has("dimensions") )
    {
      JSONObject dim = new JSONObject(obj.getString("dimensions"));
      ItemDimensions dimensions = new ItemDimensions(dim);
      item.setDimensions(dimensions);
    }

    if ( obj.has("productionDate") )
    {
      JSONObject d = new JSONObject(obj.getString("productionDate"));
      DatePeriod dp = new DatePeriod(d);
      item.setProductionDate(dp);
    }

    item.setItemName(obj.optString("itemName"));
    item.setTextualDescription(obj.optString("textualDescription"));
    item.setProvenanceDetails(obj.optString("provenanceDetails"));
    item.setAuthenticated(obj.optBoolean("authenticated", false));

    return item;

  }
}