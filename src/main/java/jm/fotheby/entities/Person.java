package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person
{
  // private static final Long serialVersionUID = 1L;

  @Id @GeneratedValue
  private Long id;
  private String title;
  private String firstName;
  private String surname;
  @Embedded
  private Address contactAddress;
  @Embedded
  private TelNumber telNumber;
  ManyToOne
  private Country country;
  @Embedded
  private EmailAddress emailAddress;
  private String password;

  public Person() {}
  public Person(String title, String firstName, String surname)
  {
    this.title = title;
    this.firstName = firstName;
    this.surname = surname;
  }

  public void setId(int id)
  {
    this.id = id;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  public void setSurname(String surname)
  {
    this.surname = surname;
  }

  public void setContactAddress(Address address)
  {
    this.contactAddress = address;
  }

  public int getId()
  {
    return this.id;
  }
}

