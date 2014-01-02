/**
 * 
 */
package org.duodo.sgip13s.plugin;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import net.xeoh.plugins.base.annotations.meta.Author;
import net.xeoh.plugins.base.annotations.meta.Version;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.plugin.ReceivedMessageHandlerPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author huzorro(huzorro@gmail.com)
 *
 */
@Author(name = "huzorro(huzorro@gmail.com)")
@Version(version = 10000)
@PluginImplementation
public class LogReceivedMessageHandlerPlugin implements
		ReceivedMessageHandlerPlugin {
	private Logger logger = LoggerFactory.getLogger(LogReceivedMessageHandlerPlugin.class);

	@Override
	public boolean received(Message message) {		
		logger.info(
				"dump/message/elapsed({})MILLIS {}",
				message.getResponse().getTimestamp() - message.getTimestamp(),
				new String(message.toString().getBytes(
						GlobalVars.defaultLocalCharset),
						GlobalVars.defaultLocalCharset));
		return true;
	}

}
