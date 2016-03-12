package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;

@Entity
public class Client extends Person implements Serializable
{
  private String type;
  // ... Account details.
  // ...
  public Client() {}

  public Client(String title, String firstName, String surname)
  {
    super(title, firstName, surname);
  }

  public void setType(String type)
  {
    this.type = type; // buyer or seller.
  }

  public String getType()
  {
    return this.type;
  }
}
