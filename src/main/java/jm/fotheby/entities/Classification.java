package jm.fotheby.entities;

import javax.persistence.*;

import javax.jdo.annotations.Unique;

@Entity
@Table(name="CLASSIFICATION")
public class Classification
{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  private int id;
  @Unique private String name;

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