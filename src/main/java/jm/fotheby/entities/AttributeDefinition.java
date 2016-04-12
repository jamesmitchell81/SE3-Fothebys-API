package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class AttributeDefinition
{
  private String name;
  private String type;
  private boolean required;
  private boolean active;

  public void setName(String name)
  {
    this.name = name;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public void setRequired(boolean required)
  {
    this.required = required;
  }

  public void setActive(boolean active)
  {
    this.active = active;
  }

  public String getName()
  {
    return this.name;
  }

  public String getType()
  {
    return this.type;
  }

  public boolean getRequired()
  {
    return this.required;
  }

  public boolean getActive()
  {
    return this.active;
  }
}