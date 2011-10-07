package eu.akka.labs.client;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.ResourceBundle;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;

public class JmsMessageProducer {

	public static final String PROPERTIES_FILE = "jms-server"; //$NON-NLS-1$

	private static Logger logger = Logger.getLogger(JmsMessageProducer.class);

	private static final ResourceBundle res = ResourceBundle.getBundle(PROPERTIES_FILE);

	private static final String JBOSS_JNDI_PROVIDER_URL = res.getString("JBOSS_JNDI_PROVIDER_URL");
	private static final String JBOSS_JNDI_INITIAL_CONTEXT_FACTORY = res.getString("JBOSS_JNDI_INITIAL_CONTEXT_FACTORY");


	private static final String JBOSS_JMS_CONNECTION_FACTORY = res.getString("JBOSS_JMS_CONNECTION_FACTORY");
	private static final String JNDI_INPUT_JMS_QUEUE = res.getString("JNDI_INPUT_JMS_QUEUE");

	/**
	 * Send JMS Object Message
	 * 
	 * @param myObject
	 */
	public void sendJmsObjectMessage(Serializable myObject) {

		Context jndiContext = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		Queue queue = null;
		MessageProducer messageProducer = null;

		try {
			// [1] Create a JNDI API InitialContext object.
			Hashtable<String, String> properties = new Hashtable<String, String>(2);
	        properties.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_JNDI_INITIAL_CONTEXT_FACTORY);
			properties.put(Context.PROVIDER_URL, JBOSS_JNDI_PROVIDER_URL);
			
			logger.debug("Create Jndi Context with :" + Context.INITIAL_CONTEXT_FACTORY + "=" + JBOSS_JNDI_INITIAL_CONTEXT_FACTORY + " " + Context.PROVIDER_URL + "="
					+ JBOSS_JNDI_PROVIDER_URL);
			jndiContext = new InitialContext(properties);
			
			// [2] Look up connection factory and queue.
			logger.debug("Create connexion factory :"+ JBOSS_JMS_CONNECTION_FACTORY);
			connectionFactory = (ConnectionFactory) jndiContext.lookup(JBOSS_JMS_CONNECTION_FACTORY);
			logger.debug("Create queue :" + JNDI_INPUT_JMS_QUEUE);
			queue = (Queue) jndiContext.lookup(JNDI_INPUT_JMS_QUEUE);
			
			// [3]
			// - Create connection
			// - Create session from connection; false means session is not
			// transacted.
			// - Create sender and text message.
			// - Send messages, varying text slightly.
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);

			ObjectMessage oMessage = session.createObjectMessage(myObject);
			messageProducer.send(oMessage);

		} catch (Exception ex) {
			logger.error("Error in trying to send JMS Message", ex);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					logger.error("Error in trying to close JMS connection", e);
				}
			}
		}
	}

	/**
	 * Send JMS Text Message
	 * 
	 * @param myString
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sendJmsTextMessage(String myString) {

		Context jndiContext = null;
		ConnectionFactory connectionFactory = null;
		Connection connection = null;
		Session session = null;
		Queue queue = null;
		MessageProducer messageProducer = null;

		try {
			// [1] Create a JNDI API InitialContext object.
			Hashtable properties = new Hashtable(2);
			properties.put(Context.INITIAL_CONTEXT_FACTORY, JBOSS_JNDI_INITIAL_CONTEXT_FACTORY);
			properties.put(Context.PROVIDER_URL, JBOSS_JNDI_PROVIDER_URL);
			logger.debug("Create Jndi Context with :" + Context.INITIAL_CONTEXT_FACTORY + "=" + JBOSS_JNDI_INITIAL_CONTEXT_FACTORY + " " + Context.PROVIDER_URL + "="
					+ JBOSS_JNDI_PROVIDER_URL);
			jndiContext = new InitialContext(properties);
			
			// [2] Look up connection factory and queue.
			logger.debug("Create connexion factory :"+ JBOSS_JMS_CONNECTION_FACTORY);
			connectionFactory = (ConnectionFactory) jndiContext.lookup(JBOSS_JMS_CONNECTION_FACTORY);
			logger.debug("Create queue :" + JNDI_INPUT_JMS_QUEUE);
			queue = (Queue) jndiContext.lookup(JNDI_INPUT_JMS_QUEUE);

			// [3]
			// - Create connection
			// - Create session from connection; false means session is not
			// transacted.
			// - Create sender and text message.
			// - Send messages, varying text slightly.
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			messageProducer = session.createProducer(queue);

			TextMessage oMessage = session.createTextMessage(myString);
			messageProducer.send(oMessage);

		} catch (Exception ex) {
			logger.error("Error in trying to send JMS Message", ex);
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (JMSException e) {
					logger.error("Error in trying to close JMS connection", e);
				}
			}
		}
	}
}
