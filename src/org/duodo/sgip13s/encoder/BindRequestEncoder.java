/**
 * 
 */
package org.duodo.sgip13s.encoder;

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
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class BindRequestEncoder extends OneToOneEncoder {
	private PacketType packetType;

	public BindRequestEncoder() {
		this(SgipPacketType.BINDREQUEST);
	}

	public BindRequestEncoder(PacketType packetType) {
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

		BindRequestMessage requestMessage = (BindRequestMessage) message;

		ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();

		bodyBuffer.writeByte(requestMessage.getLoginType());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getLoginName().getBytes(GlobalVars.defaultTransportCharset),
				BindRequest.LOGINNAME.getLength(), 0));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getLoginPassowrd()
				.getBytes(GlobalVars.defaultTransportCharset),
				BindRequest.LOGINPASSWD.getLength(), 0));
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getReserve()
				.getBytes(GlobalVars.defaultTransportCharset),
				BindRequest.RESERVE.getLength(), 0));
		
		requestMessage.setBodyBuffer(bodyBuffer.copy().array());
		
		ChannelBuffer msgBuffer = ChannelBuffers.dynamicBuffer();
		
		msgBuffer.writeBytes(message.getHeader().getHeadBuffer());
		msgBuffer.writeBytes(message.getBodyBuffer());
		
		return msgBuffer;
	}

}
