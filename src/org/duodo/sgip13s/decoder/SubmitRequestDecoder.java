/**
 * 
 */
package org.duodo.sgip13s.decoder;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.message.SubmitRequestMessage;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.duodo.sgip13s.packet.SubmitRequest;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class SubmitRequestDecoder extends OneToOneDecoder {
	private PacketType packetType;

	public SubmitRequestDecoder() {
		this(SgipPacketType.SUBMITREQUEST);
	}

	public SubmitRequestDecoder(PacketType packetType) {
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

		SubmitRequestMessage requestMessage = new SubmitRequestMessage();

		requestMessage.setHeader(message.getHeader());
		requestMessage.setBodyBuffer(message.getBodyBuffer());

		ChannelBuffer bodyBuffer = ChannelBuffers.copiedBuffer(message
				.getBodyBuffer());

		requestMessage.setSpnumber(bodyBuffer.readBytes(
				SubmitRequest.SPNUMBER.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setChargenumber(bodyBuffer.readBytes(
				SubmitRequest.CHARGENUMBER.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setUsercount(bodyBuffer.readUnsignedByte());
		requestMessage.setUsernumber(bodyBuffer.readBytes(
				SubmitRequest.USERNUMBER.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setCorpid(bodyBuffer.readBytes(
				SubmitRequest.CORPID.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setServicetype(bodyBuffer.readBytes(
				SubmitRequest.SERVICETYPE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setFeetype(bodyBuffer.readUnsignedByte());
		requestMessage.setFeevalue(bodyBuffer.readBytes(
				SubmitRequest.FEEVALUE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setGivenvalue(bodyBuffer.readBytes(
				SubmitRequest.GIVENVALUE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setAgentflag(bodyBuffer.readUnsignedByte());
		requestMessage.setMorelatetomtflag(bodyBuffer.readUnsignedByte());
		requestMessage.setPriority(bodyBuffer.readUnsignedByte());
		requestMessage.setExpiretime(bodyBuffer.readBytes(
				SubmitRequest.EXPIRETIME.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setScheduletime(bodyBuffer.readBytes(
				SubmitRequest.SCHEDULETIME.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setReportflag(bodyBuffer.readUnsignedByte());
		requestMessage.setTppid(bodyBuffer.readUnsignedByte());
		requestMessage.setTpudhi(bodyBuffer.readUnsignedByte());
		requestMessage.setMessagecoding(bodyBuffer.readUnsignedByte());
		requestMessage.setMessagetype(bodyBuffer.readUnsignedByte());
		requestMessage.setMessagelength(bodyBuffer.readUnsignedInt());
		requestMessage.setMessagecontent(bodyBuffer.readBytes(
				(int) requestMessage.getMessagelength()).toString(
				GlobalVars.defaultTransportCharset));
		requestMessage.setReserve(bodyBuffer.readBytes(
				SubmitRequest.RESERVE.getLength()).toString(
				GlobalVars.defaultTransportCharset));
		
		return requestMessage;
	}

}
