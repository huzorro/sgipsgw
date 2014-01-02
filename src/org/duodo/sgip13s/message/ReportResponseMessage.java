/**
 * 
 */
package org.duodo.sgip13s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.packet.DeliverResponse;
import org.duodo.sgip13s.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class ReportResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = 1530100000061666385L;
	
	private short result = 0;
	private String reserve = new String(
			new byte[DeliverResponse.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	public ReportResponseMessage() {
		this(SgipPacketType.REPORTRESPONSE);
	}
	public ReportResponseMessage(PacketType packetType) {
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
				.format("ReportResponseMessage [result=%s, reserve=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						result, reserve, getPacketType(), getTimestamp(),
						getChannelIds(), getChildChannelIds(), getHeader(),
						Hex.encodeHexString(getBodyBuffer()));
	}
	
}
