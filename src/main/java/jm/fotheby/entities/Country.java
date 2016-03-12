package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Country implements Serializable
{
  private static final Long serialVersionUID = 1L;

  private String name;
  private String currency;
  private String timezone;

  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return this.name;
  }
}