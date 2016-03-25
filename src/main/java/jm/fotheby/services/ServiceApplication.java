package jm.fotheby.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@ApplicationPath("/services")
public class ServiceApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();

   // private EntityManagerFactory emf;
   // private EntityManager em;

   public ServiceApplication() {
      EntityManager clientDB = Persistence.createEntityManagerFactory("$objectdb/db/client.odb").createEntityManager();
      EntityManager categoryDB = Persistence.createEntityManagerFactory("$objectdb/db/category.odb").createEntityManager();
      EntityManager itemsDB = Persistence.createEntityManagerFactory("$objectdb/db/client.odb").createEntityManager();

      singletons.add(new ClientResource(clientDB));
      singletons.add(new CategoryResource(categoryDB));
      singletons.add(new LotItemResource(itemsDB));
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}
