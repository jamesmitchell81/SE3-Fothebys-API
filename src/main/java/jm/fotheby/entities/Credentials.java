package jm.fotheby.entities;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;


@Embeddable
public class Credentials
{
  @Embedded
  private EmailAddress emailAddress;
  @Embedded
  private TelNumber telNumber;
  private String password;

  public void setEmailAddress(EmailAddress emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public void setTelNumber(TelNumber telNumber)
  {
    this.telNumber = telNumber;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public EmailAddress getEmailAddress()
  {
    return this.emailAddress;
  }

  public TelNumber telNumber()
  {
    return this.telNumber;
  }

  public String getPassword()
  {
    return this.password;
  }
}