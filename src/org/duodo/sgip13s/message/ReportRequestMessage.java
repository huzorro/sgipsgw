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
import org.duodo.sgip13s.packet.ReportRequest;
import org.duodo.sgip13s.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class ReportRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = 4460557848888343195L;
	
	private SequenceNumber sequenceNumber = new SequenceNumber();
	private short reporttype = 0;
	private String usernumber = new String(
			new byte[ReportRequest.USERNUMBER.getLength()],
			GlobalVars.defaultTransportCharset);
	private short state = 0;
	private short errorcode = 0;
	private String reserve = new String(
			new byte[ReportRequest.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);

	public ReportRequestMessage() {
		this(SgipPacketType.REPORTREQUEST);
	}
	
	public ReportRequestMessage(long nodeIds) {
		setPacketType(SgipPacketType.REPORTREQUEST);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber(nodeIds, System.currentTimeMillis()));
		setHeader(header);
	}
	public ReportRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber());
		setHeader(header);
	}


	/**
	 * @return the sequenceNumber
	 */
	public SequenceNumber getSequenceNumber() {
		return sequenceNumber;
	}

	/**
	 * @param sequenceNumber the sequenceNumber to set
	 */
	public void setSequenceNumber(SequenceNumber sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	/**
	 * @return the reporttype
	 */
	public short getReporttype() {
		return reporttype;
	}

	/**
	 * @param reporttype the reporttype to set
	 */
	public void setReporttype(short reporttype) {
		this.reporttype = reporttype;
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
	 * @return the state
	 */
	public short getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(short state) {
		this.state = state;
	}

	/**
	 * @return the errorcode
	 */
	public short getErrorcode() {
		return errorcode;
	}

	/**
	 * @param errorcode the errorcode to set
	 */
	public void setErrorcode(short errorcode) {
		this.errorcode = errorcode;
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

	@Override
	public String toString() {
		return String
				.format("ReportRequestMessage [sequenceNumber=%s, reporttype=%s, usernumber=%s, state=%s, errorcode=%s, reserve=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getLifeTime()=%s, isTerminationLife()=%s, getResponse()=%s, getRequests()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						sequenceNumber, reporttype, usernumber, state,
						errorcode, reserve, getPacketType(), getTimestamp(),
						getChannelIds(), getChildChannelIds(), getLifeTime(),
						isTerminationLife(), getResponse(), getRequests(),
						getHeader(), Hex.encodeHexString(getBodyBuffer()));
	}
	
}
