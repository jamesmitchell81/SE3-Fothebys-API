package jm.fotheby.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@ApplicationPath("/services")
public class ServiceApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();
   // private Set<Object> classes = new HashSet<Object>();

   public ServiceApplication()
   {
      EntityManager clientDB = Persistence.createEntityManagerFactory("$objectdb/db/client.odb").createEntityManager();
      EntityManager categoryDB = Persistence.createEntityManagerFactory("$objectdb/db/category.odb").createEntityManager();
      EntityManager itemDB = Persistence.createEntityManagerFactory("$objectdb/db/item.odb").createEntityManager();
      EntityManager imageDB = Persistence.createEntityManagerFactory("$objectdb/db/image.odb").createEntityManager();
      EntityManager classDB = Persistence.createEntityManagerFactory("$objectdb/db/classification.odb").createEntityManager();
      EntityManager expertDB = Persistence.createEntityManagerFactory("$objectdb/db/expert.odb").createEntityManager();
      EntityManager countryDB = Persistence.createEntityManagerFactory("$objectdb/db/country.odb").createEntityManager();
      EntityManager locationDB = Persistence.createEntityManagerFactory("$objectdb/db/location.odb").createEntityManager();

      singletons.add(new ClientResource(clientDB));
      singletons.add(new CategoryResource(categoryDB));
      singletons.add(new LotItemResource(itemDB));
      singletons.add(new ItemImageResource(imageDB));
      singletons.add(new ClassificationResource(classDB));
      singletons.add(new ExpertResource(expertDB));
      singletons.add(new CountryResource(countryDB));
      singletons.add(new LocationResource(locationDB));
   }

   @Override
   public Set<Object> getSingletons()
   {
      return singletons;
   }

   // @Override
   // public Set<Object> getClasses()
   // {
   //    return classes;
   // }
}
