package jm.fotheby.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;



@ApplicationPath("/services")
public class ServiceApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();
   // private Set<Object> classes = new HashSet<Object>();

   public ServiceApplication()
   {
      // singletons.add(new ClientResource());
      singletons.add(new CategoryResource());
      // singletons.add(new LotItemResource());
      // singletons.add(new ItemImageResource());
      singletons.add(new ClassificationResource());
      singletons.add(new ExpertResource());
      singletons.add(new CountryResource());
      singletons.add(new LocationResource());
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
