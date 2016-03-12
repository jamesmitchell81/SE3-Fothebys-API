package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person implements Serializable
{
  // private static final Long serialVersionUID = 1L;

  // @Id @GeneratedValue
  private int id;

  private String title; // enum?
  private String firstName;
  private String surname;
  /* @Embedable? */ // private Address contactAddress;
  /* @Embedable? */ // private TelNumber telNumber;

  /* ManyToOne ... */ // private Country country;
  // private String emailAddress; // username
  // private String password;

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

  public int getId()
  {
    return this.id;
  }
}

