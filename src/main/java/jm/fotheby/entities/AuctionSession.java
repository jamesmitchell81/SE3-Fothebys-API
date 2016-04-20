package jm.fotheby.entities;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AuctionSession
{
  @Id @GeneratedValue
  private int id;
  private String sessionName;
  private Time sessionStart;
  private Time sessionEnd;
}