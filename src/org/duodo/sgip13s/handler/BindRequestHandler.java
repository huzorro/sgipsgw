/**
 * 
 */
package org.duodo.sgip13s.handler;

import java.net.InetSocketAddress;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.DefaultServerSessionFactory;
import org.duodo.netty3ext.factory.session.config.DefaultServerSessionConfigFactory;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.duodo.sgip13s.message.BindRequestMessage;
import org.duodo.sgip13s.message.BindResponseMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class BindRequestHandler extends SimpleChannelUpstreamHandler {
	private PacketType packetType;
	private DefaultServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> configFactory;

	public BindRequestHandler(
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory) {
		this(SgipPacketType.BINDREQUEST, sessionFactory, configFactory);
	}
	
	public BindRequestHandler(
			PacketType packetType, 
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory) {
		this.packetType = packetType;
		this.sessionFactory = sessionFactory;
		this.configFactory = configFactory;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#messageReceived(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.MessageEvent)
	 */
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
        Message message = (Message) e.getMessage();
        long commandId = ((Long) message.getHeader().getCommandId()).longValue();
        if(commandId != packetType.getCommandId()){
            super.messageReceived(ctx, e);
            return;
        }  
        
        BindRequestMessage requestMessage = (BindRequestMessage) e.getMessage();
        
		SessionConfig netConfig = configFactory
				.setHost(
						((InetSocketAddress) ctx.getChannel()
								.getRemoteAddress()).getAddress()
								.getHostAddress())
				.setUser(requestMessage.getLoginName().trim())
				.setVersion(requestMessage.getLoginType()).create();
		if(netConfig == null) {
			ctx.getChannel().close();
			return;
		}
		
        sessionFactory.setChannel(ctx.getChannel());
        sessionFactory.setConfig(netConfig);
        
        Session session = sessionFactory.create();
        if(session != null) {
        	ctx.getChannel().setAttachment(session);        
        } else {
        	ctx.getChannel().close();
        	return;
        } 
        
        BindResponseMessage responseMessage = new BindResponseMessage();
        responseMessage.setRequest(requestMessage);
        
        if(!netConfig.getPasswd().equals(requestMessage.getLoginPassowrd().trim())) {
        	responseMessage.setResult((short) 1);
        	ctx.getChannel().write(responseMessage);
        	ctx.getChannel().close();
        	return;
        }
        
        ctx.getChannel().write(responseMessage);
        
        session.getLoginFuture().setSuccess();
	}
	
	
}
