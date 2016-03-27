package jm.fotheby.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.jdo.annotations.Unique;

@Entity
@Table(name="CLASSIFICATION")
public class Classification
{
  @Id @GeneratedValue
  private int id;

  @Unique
  private String name;

  public int getId()
  {
    return id;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getName()
  {
    return this.name;
  }
}