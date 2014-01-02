/**
 * 
 */
package org.duodo.sgip13s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.packet.BindResponse;
import org.duodo.sgip13s.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class BindResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = -5351270042541088206L;
	
	private short result = 0;
	private String reserve = new String(
			new byte[BindResponse.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	public BindResponseMessage() {
		this(SgipPacketType.BINDRESPONSE);
	}
	public BindResponseMessage(PacketType packetType) {
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
				.format("BindResponseMessage [result=%s, reserve=%s, getPacketType()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						result, reserve, getPacketType(), getChannelIds(),
						getChildChannelIds(), getHeader(),
						Hex.encodeHexString(getBodyBuffer()));
	}
	
	
}
