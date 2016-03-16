package jm.fotheby.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/services")
public class ServiceApplication extends Application {
   private Set<Object> singletons = new HashSet<Object>();

   public ServiceApplication() {
      singletons.add(new CustomerResource());
      singletons.add(new ClientResource());
      singletons.add(new CategoryResource());
   }

   @Override
   public Set<Object> getSingletons() {
      return singletons;
   }
}
