package jm.fotheby.test;

import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class CustomerResourceTest
{

  // private EntityManagerFactory emf;
  // private EntityManager em;

  @Test
  public void testCustomerResource() throws Exception
  {
    Client client = ClientBuilder.newClient();
    // try {

    //   System.out.println("*** Create a new Customer ***");

    //   String xml = "<customer>"
    //   + "<first-name>Bill</first-name>"
    //   + "<last-name>Burke</last-name>"
    //   + "</customer>";

    //   Response response = client.target("http://localhost:8080/services/customers")
    //   .request().post(Entity.xml(xml));

    //   if (response.getStatus() != 201) throw new RuntimeException("Failed to create");
    //   String location = response.getLocation().toString();

    //   System.out.println("Location: " + location);
    //   response.close();

    //   System.out.println("*** GET Created Customer **");
    //   String customer = client.target(location).request().get(String.class);
    //   System.out.println(customer);

    //   String updateCustomer = "<customer>"
    //   + "<first-name>William</first-name>"
    //   + "<last-name>Burke</last-name>"
    //   + "</customer>";

    //   response = client.target(location).request().put(Entity.xml(updateCustomer));

    //   if (response.getStatus() != 204) throw new RuntimeException("Failed to update");

    //   response.close();
    //   System.out.println("**** After Update ***");
    //   customer = client.target(location).request().get(String.class);
    //   System.out.println(customer);
    // } finally {
    //   client.close();
    // }
  }
}
