package eu.akka.labs.ejb31.server;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.apache.log4j.Logger;

/**
 * This Bean is used to initialize things at application server startup and free resources at application server shutdown. 
 * By using the @Startup annotation, you can force the container to instantiate the singleton instance during 
 * application initialization.
 * 
 * @author AKKA
 */
@Singleton
@Startup
@SuppressWarnings("unused")
public class MyStartupBean{

   Logger logger = Logger.getLogger(MyStartupBean.class);
	

   /**
    * @PostConstruct method that is guaranteed to be called at startup time.
    */
   @PostConstruct
   private void startup() { 
	   logger.info("@PostConstruct startup : done");
   }

   /**
    * @PreDestroy method for a singleton is guaranteed to be called when the application is shutting down.
    */
   @PreDestroy
   private void shutdown() { 
	   logger.info("@PreDestroy shutdown : done");
   }
}
