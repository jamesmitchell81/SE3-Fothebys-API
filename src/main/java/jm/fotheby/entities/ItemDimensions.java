package jm.fotheby.entities;

import javax.persistence.Embeddable;

import org.json.*;

@Embeddable
public class ItemDimensions
{
  private double length;
  private double width;
  private double height;
  private String baseMeasure;

  public ItemDimensions(double width, double height)
  {
    this.width = width;
    this.height = height;
  }

  public ItemDimensions(double width, double height, double length)
  {
    this(width, height);
    this.length = length;
  }

  public ItemDimensions(JSONObject json)
  {
    this.width =  json.has("width")  ? json.getInt("width") : 0;
    this.height = json.has("height") ? json.getInt("height") : 0;
    this.length = json.has("length") ? json.getInt("length") : 0;
    this.baseMeasure = json.has("baseMeasure") ? json.getString("baseMeasure") : "";
  }

  public void setLength(double length)
  {
    this.length = length;
  }

  public void setWidth(double width)
  {
    this.width = width;
  }

  public void setHeight(double height)
  {
    this.height = height;
  }

  public void setBaseMeasure(String baseMeasure)
  {
    this.baseMeasure = baseMeasure;
  }

  public double getWidth()
  {
    return this.width;
  }

  public double getHeight()
  {
    return this.height;
  }

  public double getLength()
  {
    return this.length;
  }

  public String getBaseMeasure() {
    return baseMeasure;
  }

  public String getDimensions()
  {
    return this.width + "; " + this.height + "; " + this.length;
  }
}