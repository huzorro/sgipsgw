/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.DefaultSequenceNumberUtil;
import org.duodo.sgip13s.message.ReportRequestMessage;
import org.duodo.sgip13s.packet.ReportRequest;
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
public class ReportRequestDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public ReportRequestDecoder() {
		this(SgipPacketType.REPORTREQUEST);
	}

	public ReportRequestDecoder(PacketType packetType) {
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

		ReportRequestMessage requestMessage = new ReportRequestMessage();

		requestMessage.setHeader(message.getHeader());
		requestMessage.setBodyBuffer(message.getBodyBuffer());

		ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message
				.getBodyBuffer());

		requestMessage
				.setSequenceNumber(DefaultSequenceNumberUtil
						.bytes2SequenceN(bodyBuffer.readBytes(
								ReportRequest.SUBMITSEQUENCENUMBER.getLength())
								.array()));
		requestMessage.setReporttype(bodyBuffer.readUnsignedByte());
		requestMessage.setUsernumber(bodyBuffer.readBytes(
				ReportRequest.USERNUMBER.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setState(bodyBuffer.readUnsignedByte());
		requestMessage.setErrorcode(bodyBuffer.readUnsignedByte());
		requestMessage.setReserve(bodyBuffer.readBytes(
				ReportRequest.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}
