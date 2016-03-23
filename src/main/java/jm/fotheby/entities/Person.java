package jm.fotheby.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Person
{
  @Id @GeneratedValue private Long id;
  private String title;
  private String firstName;
  private String surname;
  private String telNumber;
  private String emailAddress;
  @Embedded private Address contactAddress;

  @ManyToOne private Country country;

  public Person() {}
  public Person(String title, String firstName, String surname)
  {
    this.title = title;
    this.firstName = firstName;
    this.surname = surname;
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

  public void setTelNumber(String telNumber) {
    this.telNumber = telNumber;
  }

  public void setEmailAddress(String emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public Long getId()
  {
    return this.id;
  }

  public String getTitle() {
    return title;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getSurname() {
    return surname;
  }

  public Address getContactAddress() {
    return contactAddress;
  }

  public String getTelNumber() {
    return telNumber;
  }

  public String getEmailAddress()
  {
    return this.emailAddress;
  }

  public Country getCountry() {
    return country;
  }
}

