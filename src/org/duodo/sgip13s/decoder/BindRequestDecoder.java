/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.BindRequestMessage;
import org.duodo.sgip13s.packet.BindRequest;
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
public class BindRequestDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public BindRequestDecoder() {
		this(SgipPacketType.BINDREQUEST);
	}

	public BindRequestDecoder(PacketType packetType) {
		this.packetType = packetType;
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		Message message = (Message) msg;
		long commandId = ((Long) message.getHeader().getCommandId()).longValue();
		
		if (packetType.getCommandId() != commandId) return msg;
		
		BindRequestMessage requestMessage = new BindRequestMessage();

		requestMessage.setHeader(message.getHeader());
		requestMessage.setBodyBuffer(message.getBodyBuffer());

		ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message
				.getBodyBuffer());

		requestMessage.setLoginType(bodyBuffer.readUnsignedByte());
		requestMessage.setLoginName(bodyBuffer.readBytes(
				BindRequest.LOGINNAME.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setLoginPassowrd(bodyBuffer.readBytes(
				BindRequest.LOGINPASSWD.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setReserve(bodyBuffer.readBytes(
				BindRequest.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}
