/**
 * 
 */
package org.duodo.sgip13s.message;

import org.apache.commons.codec.binary.Hex;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.SequenceNumber;
import org.duodo.sgip13s.packet.BindRequest;
import org.duodo.sgip13s.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class BindRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = 776190389687326556L;
	
	private short loginType = 1;
	private String loginName = new String(
			new byte[BindRequest.LOGINNAME.getLength()],
			GlobalVars.defaultTransportCharset);
	private String loginPassowrd = new String(
			new byte[BindRequest.LOGINPASSWD.getBodyLength()],
			GlobalVars.defaultTransportCharset);
	private String reserve = new String(
			new byte[BindRequest.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	public BindRequestMessage() {
		this(SgipPacketType.BINDREQUEST);
	}
	
	public BindRequestMessage(long nodeIds) {
		setPacketType(SgipPacketType.BINDREQUEST);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber(nodeIds, System.currentTimeMillis()));
		setHeader(header);
	}
	/**
	 * 
	 * @param packetType
	 */
	public BindRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber());
		setHeader(header);
	}
	/**
	 * @return the loginType
	 */
	public short getLoginType() {
		return loginType;
	}
	/**
	 * @param loginType the loginType to set
	 */
	public void setLoginType(short loginType) {
		this.loginType = loginType;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**
	 * @return the loginPassowrd
	 */
	public String getLoginPassowrd() {
		return loginPassowrd;
	}
	/**
	 * @param loginPassowrd the loginPassowrd to set
	 */
	public void setLoginPassowrd(String loginPassowrd) {
		this.loginPassowrd = loginPassowrd;
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
				.format("BindRequestMessage [loginType=%s, loginName=%s, loginPassowrd=%s, reserve=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getResponse()=%s, getRequests()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						loginType, loginName, loginPassowrd, reserve,
						getPacketType(), getChannelIds(),
						getChildChannelIds(), getResponse(),
						getHeader(), Hex.encodeHexString(getBodyBuffer()));
	}
	
}
