/**
 * 
 */
package eu.akka.openwis.dataservice.service.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import eu.akka.project.common.service.Foo;

/**
 * Short Description goes here. <P>
 * Explanation goes here. <P>
 * 
 */
public class TestEJBRemoting {

   public static void main(String[] args) {
      try {
         Properties env = new Properties();
         env.put(Context.PROVIDER_URL, "jnp://localhost:1099");
         env.put(Context.URL_PKG_PREFIXES, "org.jboss.naming:org.jnp.interfaces");
         env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jnp.interfaces.NamingContextFactory");
         env.put("jnp.disableDiscovery", "true");
         Context context = new InitialContext(env);
         Foo foo = (Foo) context.lookup("myproject/FooBean/remote");
         int i = 0;
         while (true) {
            i++;
            foo.bar();
            if (i % 1000 == 0)
               System.out.println(i);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

}
