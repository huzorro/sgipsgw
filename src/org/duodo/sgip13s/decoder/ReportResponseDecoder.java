/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.ReportResponseMessage;
import org.duodo.sgip13s.packet.ReportResponse;
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
public class ReportResponseDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public ReportResponseDecoder() {
		this(SgipPacketType.REPORTRESPONSE);
	}

	public ReportResponseDecoder(PacketType packetType) {
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

		ReportResponseMessage responseMessage = new ReportResponseMessage();

		responseMessage.setHeader(message.getHeader());
		responseMessage.setBodyBuffer(message.getBodyBuffer());

		ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message
				.getBodyBuffer());

		responseMessage.setResult(bodyBuffer.readUnsignedByte());
		responseMessage.setReserve(bodyBuffer.readBytes(
				ReportResponse.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return responseMessage;
	}

}
