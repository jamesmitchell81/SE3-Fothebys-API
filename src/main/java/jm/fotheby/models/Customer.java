package jm.fotheby.models;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable
{
  private static final Long serialVersionUID = 1L;

  @Id @GeneratedValue
  int id;
  private String firstName;
  private String lastName;

  // public void setId(int id)
  // {
  //   this.id = id;
  // }

  public int getId()
  {
    return id;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  public String getLastName()
  {
    return lastName;
  }

  @Override
  public String toString()
  {
    return this.firstName + " " + this.lastName;
  }
}