/**
 * 
 */
package eu.akka.project.server.service;

import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import eu.akka.project.common.service.Foo;

/**
 * Short Description goes here. <P>
 * Explanation goes here. <P>
 * 
 */
@Stateless
public class FooBean implements Foo {
	
	/**
	 * The logger.
	 */
	private static Logger logger = Logger.getLogger(FooBean.class);

   public void bar() {
      logger.info("bar() method invoked");
   }
}
