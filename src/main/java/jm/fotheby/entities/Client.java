package jm.fotheby.entities;

import javax.persistence.Entity;
import javax.persistence.Embedded;

@Entity
public class Client extends Person
{
  @Embedded
  private PaymentDetails accountDetails;
  @Embedded
  private Credentials credentials;

  public Client() {}
  public Client(String title, String firstName, String surname)
  {
    super(title, firstName, surname);
  }

  public void setAccount(PaymentDetails account)
  {
    this.accountDetails = account;
  }

  public void setCredentials(Credentials credentials)
  {
    this.credentials = credentials;
  }

  public PaymentDetails getAccountDetails()
  {
    return this.accountDetails;
  }

  public Credentials getCredentials()
  {
    return this.credentials;
  }

}
