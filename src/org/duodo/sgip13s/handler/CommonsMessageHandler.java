/**
 * 
 */
package org.duodo.sgip13s.handler;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.session.Session;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CommonsMessageHandler extends SimpleChannelHandler {
	private final Logger logger = LoggerFactory.getLogger(CommonsMessageHandler.class);

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {		
		Message message = (Message) e.getMessage();
		SessionConfig config = ((Session) ctx.getChannel().getAttachment())
				.getConfig();
		message.setChannelIds(config.getChannelIds());
		message.setChildChannelIds(ctx.getChannel().getId().toString());
		message.setLifeTime(config.getLifeTime());
		super.messageReceived(ctx, e);
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelHandler#writeRequested(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void writeRequested(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		Message message = (Message) e.getMessage();
		SessionConfig config = ((Session) ctx.getChannel().getAttachment())
				.getConfig();
		message.setChannelIds(config.getChannelIds());
		message.setChildChannelIds(ctx.getChannel().getId().toString());
		message.setLifeTime(config.getLifeTime());
		super.writeRequested(ctx, e);
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelHandler#exceptionCaught(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.ExceptionEvent)
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		logger.error("receive/decode/close exception {}", e.getCause());
		try {
			e.getChannel().close();
		} catch (Exception e1) {
			logger.error("channel close fails {}", e1.getCause());
		}		
	}

}
