package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Date;

import javax.persistenance.Entity;

@Entity
public class Employee extends Person implements Serializable
{
  // .. works at location.
  // ... Role.
}