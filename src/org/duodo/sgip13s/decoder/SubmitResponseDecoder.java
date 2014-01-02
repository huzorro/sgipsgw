/**
 * 
 */
package org.duodo.sgip13s.decoder;

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
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class SubmitResponseDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public SubmitResponseDecoder() {
		this(SgipPacketType.SUBMITRESPONSE);
	}

	public SubmitResponseDecoder(PacketType packetType) {
		this.packetType = packetType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.jboss.netty.handler.codec.oneone.OneToOneDecoder#decode(org.jboss
	 * .netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel,
	 * java.lang.Object)
	 */
	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Message message = (Message) msg;
		long commandId = ((Long) message.getHeader().getCommandId())
				.longValue();

		if (packetType.getCommandId() != commandId)
			return msg;

		SubmitResponseMessage responseMessage = new SubmitResponseMessage();

		responseMessage.setHeader(message.getHeader());
		responseMessage.setBodyBuffer(message.getBodyBuffer());

		ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message
				.getBodyBuffer());

		responseMessage.setResult(bodyBuffer.readUnsignedByte());
		responseMessage.setReserve(bodyBuffer.readBytes(
				SubmitResponse.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return responseMessage;
	}

}
