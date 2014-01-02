/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.DeliverRequestMessage;
import org.duodo.sgip13s.packet.DeliverRequest;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class DeliverRequestDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public DeliverRequestDecoder() {
		this(SgipPacketType.DELIVERREQUEST);
	}

	public DeliverRequestDecoder(PacketType packetType) {
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

		DeliverRequestMessage requestMessage = new DeliverRequestMessage();

		requestMessage.setHeader(message.getHeader());
		requestMessage.setBodyBuffer(message.getBodyBuffer());

		ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message
				.getBodyBuffer());

		requestMessage.setUsernumber(bodyBuffer.readBytes(
				DeliverRequest.USERNUMBER.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setSpnumber(bodyBuffer.readBytes(
				DeliverRequest.SPNUMBER.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setTppid(bodyBuffer.readUnsignedByte());
		requestMessage.setTpudhi(bodyBuffer.readUnsignedByte());
		requestMessage.setMessagecoding(bodyBuffer.readUnsignedByte());
		requestMessage.setMessagelength(bodyBuffer.readUnsignedInt());
		requestMessage.setMessagecontent(bodyBuffer.readBytes(
				(int) requestMessage.getMessagelength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setReserve(bodyBuffer.readBytes(
				DeliverRequest.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}
