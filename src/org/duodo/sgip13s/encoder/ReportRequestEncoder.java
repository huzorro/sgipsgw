/**
 * 
 */
package org.duodo.sgip13s.encoder;

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
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.google.common.primitives.Bytes;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class ReportRequestEncoder extends OneToOneEncoder {
	private PacketType packetType;

	public ReportRequestEncoder() {
		this(SgipPacketType.REPORTREQUEST);
	}

	public ReportRequestEncoder(PacketType packetType) {
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

		ReportRequestMessage requestMessage = (ReportRequestMessage) message;

		ChannelBuffer bodyBuffer = ChannelBuffers.dynamicBuffer();

		bodyBuffer.writeBytes(DefaultSequenceNumberUtil
				.sequenceN2Bytes(requestMessage.getSequenceNumber()));
		bodyBuffer.writeByte(requestMessage.getReporttype());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage
				.getUsernumber().getBytes(GlobalVars.defaultTransportCharset),
				ReportRequest.USERNUMBER.getLength(), 0));
		bodyBuffer.writeByte(requestMessage.getState());
		bodyBuffer.writeByte(requestMessage.getErrorcode());
		bodyBuffer.writeBytes(Bytes.ensureCapacity(requestMessage.getReserve()
				.getBytes(GlobalVars.defaultTransportCharset),
				ReportRequest.RESERVE.getLength(), 0));
		
		requestMessage.setBodyBuffer(bodyBuffer.copy().array());
		
		ChannelBuffer msgBuffer = ChannelBuffers.dynamicBuffer();
		
		msgBuffer.writeBytes(requestMessage.getHeader().getHeadBuffer());
		msgBuffer.writeBytes(requestMessage.getBodyBuffer());
		
		return msgBuffer;
	}

}
