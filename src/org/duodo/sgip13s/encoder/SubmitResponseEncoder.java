/**
 * 
 */
package org.duodo.sgip13s.encoder;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.SubmitResponseMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.duodo.sgip13s.packet.SubmitResponse;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class SubmitResponseEncoder extends OneToOneEncoder {
	private PacketType packetType;

	public SubmitResponseEncoder() {
		this(SgipPacketType.SUBMITRESPONSE);
	}

	public SubmitResponseEncoder(PacketType packetType) {
		this.packetType = packetType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss
	 * .netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel,
	 * java.lang.Object)
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

		SubmitResponseMessage responseMessage = (SubmitResponseMessage) message;

		ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();

		bodyBuffer.writeByte(responseMessage.getResult());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(responseMessage.getReserve()
				.getBytes(GlobalVars.defaultTransportCharset),
				SubmitResponse.RESERVE.getLength(), 0));
		
		responseMessage.setBodyBuffer(bodyBuffer.copy().array());
		
		ChannelBuffer msgBuffer = ChannelBuffers.dynamicBuffer();
		
		msgBuffer.writeBytes(responseMessage.getHeader().getHeadBuffer());
		msgBuffer.writeBytes(responseMessage.getBodyBuffer());
		
		return msgBuffer;
	}
}
