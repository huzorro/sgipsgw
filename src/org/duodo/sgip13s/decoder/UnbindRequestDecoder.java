/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.UnbindRequestMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class UnbindRequestDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public UnbindRequestDecoder() {
		this(SgipPacketType.UNBINDREQUEST);
	}
	
	public UnbindRequestDecoder(PacketType packetType) {
		this.packetType = packetType;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Message message = (Message) msg;
		long commandId = ((Long) message.getHeader().getCommandId()).longValue();
		
		if (packetType.getCommandId() != commandId) return msg;
		
		UnbindRequestMessage requestMessage = new UnbindRequestMessage();
		
		requestMessage.setHeader(message.getHeader());
		requestMessage.setBodyBuffer(message.getBodyBuffer());
		
		return requestMessage;
	}

}
