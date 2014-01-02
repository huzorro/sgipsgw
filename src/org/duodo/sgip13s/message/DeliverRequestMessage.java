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
import org.duodo.sgip13s.packet.DeliverRequest;
import org.duodo.sgip13s.packet.SgipPacketType;
import org.duodo.sgip13s.packet.SubmitRequest;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class DeliverRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = -605827022369453415L;

	private String usernumber = new String(
			new byte[DeliverRequest.USERNUMBER.getLength()],
			GlobalVars.defaultTransportCharset);
	private String spnumber = new String(
			new byte[DeliverRequest.SPNUMBER.getLength()],
			GlobalVars.defaultTransportCharset);
	private short tppid = 0;
	private short tpudhi = 0;
	private short messagecoding = 15;
	private long messagelength = 120;
	private String messagecontent = new String(new byte[(int) messagelength],
			GlobalVars.defaultTransportCharset);
	private String reserve = new String(
			new byte[SubmitRequest.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	private byte[] msgContentBytes = new byte[(int) messagelength];	

	public DeliverRequestMessage() {
		this(SgipPacketType.DELIVERREQUEST);
	}
	
	public DeliverRequestMessage(long nodeIds) {
		setPacketType(SgipPacketType.DELIVERREQUEST);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber(nodeIds, System.currentTimeMillis()));
		setHeader(header);
	}
	
	public DeliverRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber());
		setHeader(header);
	}

	/**
	 * @return the usernumber
	 */
	public String getUsernumber() {
		return usernumber;
	}

	/**
	 * @param usernumber the usernumber to set
	 */
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}

	/**
	 * @return the spnumber
	 */
	public String getSpnumber() {
		return spnumber;
	}

	/**
	 * @param spnumber the spnumber to set
	 */
	public void setSpnumber(String spnumber) {
		this.spnumber = spnumber;
	}

	/**
	 * @return the tppid
	 */
	public short getTppid() {
		return tppid;
	}

	/**
	 * @param tppid the tppid to set
	 */
	public void setTppid(short tppid) {
		this.tppid = tppid;
	}

	/**
	 * @return the tpudhi
	 */
	public short getTpudhi() {
		return tpudhi;
	}

	/**
	 * @param tpudhi the tpudhi to set
	 */
	public void setTpudhi(short tpudhi) {
		this.tpudhi = tpudhi;
	}

	/**
	 * @return the messagecoding
	 */
	public short getMessagecoding() {
		return messagecoding;
	}

	/**
	 * @param messagecoding the messagecoding to set
	 */
	public void setMessagecoding(short messagecoding) {
		this.messagecoding = messagecoding;
	}

	/**
	 * @return the messagelength
	 */
	public long getMessagelength() {
		return messagelength;
	}

	/**
	 * @param messagelength the messagelength to set
	 */
	public void setMessagelength(long messagelength) {
		this.messagelength = messagelength;
	}

	/**
	 * @return the messagecontent
	 */
	public String getMessagecontent() {
		return messagecontent;
	}

	/**
	 * @param messagecontent the messagecontent to set
	 */
	public void setMessagecontent(String messagecontent) {
		this.messagecontent = messagecontent;
        setMsgContentBytes(this.messagecontent.getBytes(GlobalVars.defaultTransportCharset));
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
	/**
	 * @return the msgContentBytes
	 */
	public byte[] getMsgContentBytes() {
		return msgContentBytes;
	}
	/**
	 * @param msgContentBytes the msgContentBytes to set
	 */
	public void setMsgContentBytes(byte[] msgContentBytes) {
		this.msgContentBytes = msgContentBytes;
		setMessagelength(this.msgContentBytes.length);
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String
				.format("DeliverRequestMessage [usernumber=%s, spnumber=%s, tppid=%s, tpudhi=%s, messagecoding=%s, messagelength=%s, messagecontent=%s, reserve=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getLifeTime()=%s, isTerminationLife()=%s, getResponse()=%s, getRequests()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						usernumber, spnumber, tppid, tpudhi, messagecoding,
						messagelength, messagecontent, reserve,
						getPacketType(), getTimestamp(), getChannelIds(),
						getChildChannelIds(), getLifeTime(),
						isTerminationLife(), getResponse(), getRequests(),
						getHeader(), Hex.encodeHexString(getBodyBuffer()));
	}
	
	
}
