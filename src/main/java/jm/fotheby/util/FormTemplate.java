package jm.fotheby.util;

// java
import java.lang.reflect.Field;
import java.lang.annotation.Annotation;

import java.util.ArrayList;

// JSON
import org.json.*;

public class FormTemplate
{
  private ArrayList<String> ignore;
  private ArrayList<String> capture;

  public FormTemplate()
  {
    this.ignore = new ArrayList<String>();
    this.ignore.add("GeneratedValue");
    this.ignore.add("Id");

    this.capture = new ArrayList<String>();
    this.capture.add("Embedded");
  }

  private boolean validAnnotation(String annotation)
  {
    for ( int i = 0; i < this.ignore.size(); i++ )
    {
      if ( annotation.contains(this.ignore.get(i)) )
        return false;
    }

    return true;
  }

  private boolean captureRequired(String annotation)
  {
    for ( int i = 0; i < this.capture.size(); i++ )
    {
      if ( annotation.contains(this.capture.get(i)) )
        return true;
    }

    return false;
  }

  public JSONArray getTemplate(Class cls)
  {
    Field[] fields = cls.getDeclaredFields();
    JSONArray template = new JSONArray();

    for ( int i = 0; i < fields.length; i++ )
    {
      JSONObject obj = new JSONObject();
      boolean required = true;
      String type = fields[i].getType().getSimpleName();
      String[] names = fields[i].getName().split("(?=\\p{Upper})");
      String name = "";

      for ( int k = 0; k < names.length; k++ )
      {
        name += (names[k] + " ");
      }

      Annotation[] annotation = fields[i].getDeclaredAnnotations();

      obj.put("name", name);
      obj.put("type", type);

      for ( int j = 0; j < annotation.length; j++ )
      {
        // if annotated field should be ignored?
        if ( !this.validAnnotation(annotation[j].toString()) )
        {
          required = false;
          break;
        }

        // if a drilldown is required into associated class.
        if ( this.captureRequired(annotation[j].toString()) )
        {
          try {
            Class child = fields[i].getType();
            JSONArray a = this.getTemplate(child);

            for ( int n = 0; n < a.length(); n++ )
            {
              template.put(a.getJSONObject(n));
            }
          } catch(Exception e) {
            System.out.println("e: " + e.getMessage());
            required = false;
          }
        }
      }

      if ( required )
        template.put(obj);


    }

    return template;
  }
}