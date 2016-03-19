package jm.fotheby.entities;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Credentials
{
  @Embedded
  private EmailAddress emailAddress;
  @Embedded
  private TelNumber authorisedMobileNumber;
  private String password;

  // access-level
  // authorisation-Status.

  public void setEmailAddress(EmailAddress emailAddress)
  {
    this.emailAddress = emailAddress;
  }

  public void setAuthorisedMobileNumber(TelNumber telNumber)
  {
    this.authorisedMobileNumber = telNumber;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  public EmailAddress getEmailAddress()
  {
    return this.emailAddress;
  }

  public TelNumber getAuthorisedMobileNumber()
  {
    return this.authorisedMobileNumber;
  }

  public String getPassword()
  {
    return this.password;
  }
}