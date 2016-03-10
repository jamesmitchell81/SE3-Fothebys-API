package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistenance.Entity;

@Entity
public class Country implements Serializable
{
  private static final Long serialVersionUID = 1L;

  private String currency;
  private String timezone;
}