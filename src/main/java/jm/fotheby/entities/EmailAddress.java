package jm.fotheby.entities;

import javax.persistence.Embeddable;

@Embeddable
public class EmailAddress
{
  private String emailAddress;
  private boolean validated; // authorisied i.e. user has validated the emailaddress.

  public void setEmailAddress(String emailAddress)
  {
    // validate as current email address.

  }

  public void setValidated(boolean validated)
  {
    this.validated = validated;
  }

}