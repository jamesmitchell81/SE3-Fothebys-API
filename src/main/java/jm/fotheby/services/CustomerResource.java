package jm.fotheby.services;

import jm.fotheby.entities.Customer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

@Path("/customers")
public class CustomerResource
{
   private Map<Integer, Customer> customerDB = new ConcurrentHashMap<Integer, Customer>();
   // private AtomicInteger idCounter = new AtomicInteger();

   private EntityManagerFactory emf;
   private EntityManager em;

   public CustomerResource()
   {

      try {
         this.emf = Persistence.createEntityManagerFactory("$objectdb/db/customer.odb");
         this.em = emf.createEntityManager();

      } catch (Exception e)
      {
         System.out.println(e.getMessage());
      }

   }

   @POST
   @Consumes("application/json")
   // public Response createCustomer(InputStream is)
   public Response createCustomer(Customer customer)
   {
      // Customer customer = this.readCustomer(is);
      // // customer.setId(idCounter.incrementAndGet());

      try {
         em.getTransaction().begin();
         em.persist(customer);
         em.getTransaction().commit();
      } finally {
         if ( em.getTransaction().isActive() )
         {
            em.getTransaction().rollback();
         }
      }

      System.out.println("Created customer " + customer.getId());
      return Response.created(URI.create("/customers/" + customer.getId())).build();
   }

   @GET
   @Path("{id}")
   @Produces("application/xml")
   public StreamingOutput getCustomer(@PathParam("id") int id)
   {
      List<Customer> custList = em.createQuery("SELECT g FROM Customer g", Customer.class).getResultList();
      Customer customer = custList.get(id - 1);

      if (customer == null) {
         throw new WebApplicationException(Response.Status.NOT_FOUND);
      }
      return new StreamingOutput() {
         public void write(OutputStream outputStream) throws IOException, WebApplicationException {
            outputCustomer(outputStream, customer);
         }
      };
   }

   @GET
   @Produces("application/xml")
   public StreamingOutput getCustomers()
   {
      return new StreamingOutput() {
         public void write(OutputStream outputStream) throws IOException, WebApplicationException
         {
            List<Customer> customerList = em.createQuery("SELECT g FROM Customer g", Customer.class).getResultList();

            for ( Customer customer : customerList)
            {
               outputCustomer(outputStream, customer);
            }
         }
      };
   }


   @PUT
   @Path("{id}")
   @Consumes("application/xml")
   public void updateCustomer(@PathParam("id") int id, InputStream is) {
      Customer update = readCustomer(is);
      Customer current = em.find(Customer.class, id);

      if (current == null) throw new WebApplicationException(Response.Status.NOT_FOUND);

      try {
         em.getTransaction().begin();
         current.setFirstName(update.getFirstName());
         current.setLastName(update.getLastName());
         em.getTransaction().commit();
      } finally {
         if ( em.getTransaction().isActive() )
         {
            em.getTransaction().rollback();
         }
      }

      System.out.println(current.toString());

   }


   protected void outputCustomer(OutputStream os, Customer cust) throws IOException {
      PrintStream writer = new PrintStream(os);
      writer.println("<customer id=\"" + cust.getId() + "\">");
      writer.println("   <first-name>" + cust.getFirstName() + "</first-name>");
      writer.println("   <last-name>" + cust.getLastName() + "</last-name>");
      writer.println("</customer>");
   }

   protected Customer readCustomer(InputStream is) {
      try {
         DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
         Document doc = builder.parse(is);
         Element root = doc.getDocumentElement();
         Customer cust = new Customer();

         // if ( root.getAttribute("id") != null && !root.getAttribute("id").trim().equals("") )
         // {
         //    cust.setId(Integer.valueOf(root.getAttribute("id")));
         // }

         NodeList nodes = root.getChildNodes();
         for (int i = 0; i < nodes.getLength(); i++)
         {
            Element element = (Element) nodes.item(i);
            if (element.getTagName().equals("first-name"))
            {
               cust.setFirstName(element.getTextContent());
            }
            else if (element.getTagName().equals("last-name"))
            {
               cust.setLastName(element.getTextContent());
            }
         }
         return cust;
      }
      catch (Exception e) {
         throw new WebApplicationException(e, Response.Status.BAD_REQUEST);
      }
   }

}
