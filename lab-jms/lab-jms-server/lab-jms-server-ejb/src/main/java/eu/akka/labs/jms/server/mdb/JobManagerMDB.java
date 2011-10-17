package eu.akka.labs.jms.server.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import eu.akka.labs.jms.common.MyMessage;

/**
 * 
 * @author AKKA Technologies
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/IncomingJobsQueue"),
		@ActivationConfigProperty(propertyName = "maxSession", propertyValue = "5") })
public class JobManagerMDB implements MessageListener {

	/**
	 * The logger.
	 */
	private static Logger logger = Logger.getLogger(JobManagerMDB.class);

	/**
	 * injection Message Driven Context
	 */
	@Resource
	private MessageDrivenContext mdc;

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.jms.MessageListener#onMessage(javax.jms.Message)
	 */
	@Override
	public void onMessage(Message message) {

		try {

			// Exemple pour un TextMessage
			if (message instanceof TextMessage) {
				TextMessage messageReceived = (TextMessage) message;
				logger.info("Received message: " + message);
				logger.info("Message text: " + messageReceived.getText());

				// TODO Ajouter la logique metier ici...

			}

			// Exemple pour un TextMessage
			else if (message instanceof ObjectMessage) {
				ObjectMessage om = (ObjectMessage) message;

				if (om.getObject() instanceof MyMessage) {
					MyMessage msg = (MyMessage) om.getObject();
					
					logger.info("Received message: " + msg);
					logger.info("Message text: " + msg.getMessage());
	
					//TODO Ajouter la logique metier ici...
					
				} else {
					logger.warn("Expected MyMessage but received : "
							+ message.getClass().getName());
				}
			} else {
				logger.warn("Expected ObjectMessage or TextMessage but received : "
						+ message.getClass().getName());
			}
			
		} catch (Throwable t) {
			logger.error("Unexpected error !!", t);
			mdc.setRollbackOnly();
		}
	}
}
