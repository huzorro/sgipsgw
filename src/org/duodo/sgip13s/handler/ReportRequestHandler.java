/**
 * 
 */
package org.duodo.sgip13s.handler;

import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.session.Session;
import org.duodo.sgip13s.message.ReportRequestMessage;
import org.duodo.sgip13s.message.ReportResponseMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class ReportRequestHandler extends SimpleChannelUpstreamHandler {
	private PacketType packetType;

	public ReportRequestHandler() {
		this(SgipPacketType.REPORTREQUEST);
	}
	
	public ReportRequestHandler(PacketType packetType) {
		this.packetType = packetType;
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
        
        ReportRequestMessage requestMessage = (ReportRequestMessage) message;
        ReportResponseMessage responseMessage = new ReportResponseMessage();
        
        responseMessage.setRequest(requestMessage);
        
        ctx.getChannel().write(responseMessage);
        
		((Session) ctx.getChannel().getAttachment()).receive(requestMessage
				.setResponse(responseMessage));
	}
	
	
}
