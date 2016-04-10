package jm.fotheby.entities;

import javax.persistence.*;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name="PERSON_TYPE")
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="PERSON")
public class Person
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  protected Long id;
  protected String title;
  protected String firstName;
  protected String surname;
  protected String telNumber;
  protected String emailAddress;
  @Embedded protected Address contactAddress;
  @ManyToOne protected Country country;

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

  public void setTelNumber(String telNumber)
  {
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

  public String getFullName()
  {
    return this.title + " " + this.firstName + " " + this.surname;
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

