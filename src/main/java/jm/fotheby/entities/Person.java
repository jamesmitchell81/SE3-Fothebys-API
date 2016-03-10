package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistenance.Entity;
import javax.persistenance.GeneratedValue;
import javax.persistenance.Id;

@Entity
public class Person implements Serializable
{
  private static final Long serialVersionUID = 1L;

  @Id @GeneratedValue
  private int id;

  private String title; // enum?
  private String firstName;
  private String surname;
  private Address contactAddress;
  private TelNumber telNumber;
  private String emailAddress;
  private String password;
  private Date created;
  private Date updated;
}

