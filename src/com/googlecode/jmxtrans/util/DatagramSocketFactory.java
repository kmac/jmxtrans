package com.googlecode.jmxtrans.util;

import java.net.DatagramSocket;

import org.apache.commons.pool.BaseKeyedPoolableObjectFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Allows us to pool socket connections.
 */
public class DatagramSocketFactory extends BaseKeyedPoolableObjectFactory {

	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(DatagramSocketFactory.class);

	/** constructor */
	public DatagramSocketFactory() {
	}

	/**
	 * Creates the socket and the writer to go with it.
	 */
	@Override
	public Object makeObject(Object key) throws Exception {
		return new DatagramSocket();
	}

	/**
	 * Closes the socket.
	 */
	@Override
	public void destroyObject(Object key, Object obj) throws Exception {
		DatagramSocket socket = (DatagramSocket) obj;
		socket.close();
	}

	/**
	 * Validates that the socket is good.
	 */
	@Override
	public boolean validateObject(Object key, Object obj) {
		DatagramSocket socket = (DatagramSocket) obj;
		return !socket.isClosed();
	}
}
