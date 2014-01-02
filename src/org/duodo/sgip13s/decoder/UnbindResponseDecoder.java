/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.UnbindResponseMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class UnbindResponseDecoder extends OneToOneDecoder {
	private PacketType packetType;
	
	public UnbindResponseDecoder() {
		this(SgipPacketType.UNBINDRESPONSE);
	}
	public UnbindResponseDecoder(PacketType packetType) {
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
		
		UnbindResponseMessage responseMessage = new UnbindResponseMessage();
		
		responseMessage.setHeader(message.getHeader());
		responseMessage.setBodyBuffer(message.getBodyBuffer());

		return responseMessage;
	}

}
