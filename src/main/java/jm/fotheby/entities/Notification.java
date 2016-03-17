package jm.fotheby.entities;

import javax.persistence.Entity;

@Entity
public class Notification
{

  private String subject;
  private String body;
  private Person recipient;

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  public Person getRecipient() {
    return recipient;
  }

  public void setRecipient(Person recipient) {
    this.recipient = recipient;
  }
}