/**
 * 
 */
package org.duodo.sgip13s.factory.message;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.Factory;
import org.duodo.netty3ext.message.Message;
import org.duodo.sgip13s.message.BindRequestMessage;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class BindRequestFacotry<T extends Message> implements Factory<T> {
	private SessionConfig config;

	public BindRequestFacotry(SessionConfig config) {
		this.config = config;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T create() throws Exception {
		BindRequestMessage message = new BindRequestMessage();
		message.setLoginName(config.getUser());
		message.setLoginPassowrd(config.getPasswd());
		message.setLoginType(config.getVersion());
		return (T) message;
	}


}
