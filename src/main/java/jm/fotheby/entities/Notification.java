package jm.fotheby.entities;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Notification implements Serializable
{
  private static final Long serialVersionUID = 1L;

  private String subject;
  private String body;
  private Person recipient;
}