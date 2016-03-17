package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class ItemDimensions
{
  private double length;
  private double width;
  private double height;
  // private String!!! baseMeasure

  public ItemDimensions() {}
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

  public String getDimensions()
  {



  }
}