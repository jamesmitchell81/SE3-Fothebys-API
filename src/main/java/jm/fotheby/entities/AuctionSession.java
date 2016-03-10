package jm.fotheby.entities;

import java.io.Serializable;
import java.sql.Time;

import javax.persistenance.Entity;
import javax.persistenance.GeneratedValue;
import javax.persistenance.Id;

@Entity
public class AuctionSession implements Serializable
{
  @Id @GeneratedValue
  private int id;

  private String sessionName; // morning, evening, afternoon.
  private Time sessionStart;
  private Time sessionEnd;
}