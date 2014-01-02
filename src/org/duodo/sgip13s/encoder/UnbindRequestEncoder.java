/**
 * 
 */
package org.duodo.sgip13s.encoder;

import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.UnbindRequestMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class UnbindRequestEncoder extends OneToOneEncoder {
	private PacketType packetType;
	
	public UnbindRequestEncoder() {
		this(SgipPacketType.UNBINDREQUEST);
	}
	public UnbindRequestEncoder(PacketType packetType) {
		this.packetType = packetType;
	}
	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if (!(msg instanceof Message))
			return msg;
		Message message = (Message) msg;
		long commandId = ((Long) message.getHeader().getCommandId())
				.longValue();
		if (commandId != packetType.getCommandId())
			return msg;
	
		UnbindRequestMessage requestMessage = (UnbindRequestMessage) message;
		
		ChannelBuffer msgBuffer = ChannelBuffers.dynamicBuffer();
		
		msgBuffer.writeBytes(requestMessage.getHeader().getHeadBuffer());
		
		return msgBuffer;
	}

}
