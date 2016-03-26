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
  private String classification;

  public void setClassification(String classification)
  {
    this.classification = classification;
  }

  public int getId()
  {
    return id;
  }

  public String getClassification()
  {
    return this.classification;
  }
}