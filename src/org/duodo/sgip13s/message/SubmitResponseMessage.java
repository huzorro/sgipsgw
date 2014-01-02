/**
 * 
 */
package org.duodo.sgip13s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.duodo.sgip13s.packet.SubmitResponse;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SubmitResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = -6490291019236883524L;
	
	private short result = 0;
	private String reserve = new String(
			new byte[SubmitResponse.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	public SubmitResponseMessage() {
		this(SgipPacketType.SUBMITRESPONSE);
	}
	public SubmitResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}
	/**
	 * @return the result
	 */
	public short getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(short result) {
		this.result = result;
	}
	/**
	 * @return the reserve
	 */
	public String getReserve() {
		return reserve;
	}
	/**
	 * @param reserve the reserve to set
	 */
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("SubmitResponseMessage [result=%s, reserve=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						result, reserve, getPacketType(), getTimestamp(),
						getChannelIds(), getChildChannelIds(), getHeader(),
						Hex.encodeHexString(getBodyBuffer()));
	}


	
}
