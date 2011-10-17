package eu.akka.labs.jms.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import eu.akka.labs.jms.common.MyMessage;

/**
 * Short Description goes here.
 * <P>
 * Explanation goes here.
 * <P>
 * 
 */
public class JmsLabsServlet extends javax.servlet.http.HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The logger.
	 */
	private static Logger logger = Logger.getLogger(JmsLabsServlet.class);

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_FORBIDDEN);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		JmsMessageProducer producer = new JmsMessageProducer();

		String msg = request.getParameter("m");
		if (msg != null) {
			logger.info("Paramater m received:" + msg);
			producer.sendJmsTextMessage(msg);
		}

		String objMsg = request.getParameter("o");
		if (objMsg != null) {
			logger.info("Paramater o received:" + objMsg);
			MyMessage mm = new MyMessage();
			mm.setMessage(objMsg);
			producer.sendJmsObjectMessage(mm);
		}

	}
}
