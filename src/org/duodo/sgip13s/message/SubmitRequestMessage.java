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
import org.duodo.sgip13s.packet.SgipPacketType;
import org.duodo.sgip13s.packet.SubmitRequest;

/**
 * @author huzorro(huzorro@gmail.com)
 * 
 */
public class SubmitRequestMessage extends DefaultMessage {
	private static final long serialVersionUID = 5265747696709571791L;

	private String spnumber = new String(
			new byte[SubmitRequest.SPNUMBER.getLength()],
			GlobalVars.defaultTransportCharset);
	private String chargenumber = new String(
			new byte[SubmitRequest.CHARGENUMBER.getLength()],
			GlobalVars.defaultTransportCharset);
	private short usercount = 1;
	private String usernumber = new String(
			new byte[SubmitRequest.USERNUMBER.getLength()],
			GlobalVars.defaultTransportCharset);
	private String corpid = new String(
			new byte[SubmitRequest.CORPID.getLength()],
			GlobalVars.defaultTransportCharset);
	private String servicetype = new String(
			new byte[SubmitRequest.SERVICETYPE.getLength()],
			GlobalVars.defaultTransportCharset);
	private short feetype = 2;
	private String feevalue = new String(
			new byte[SubmitRequest.FEEVALUE.getLength()],
			GlobalVars.defaultTransportCharset);
	private String givenvalue = new String(
			new byte[SubmitRequest.GIVENVALUE.getLength()],
			GlobalVars.defaultTransportCharset);
	private short agentflag = 0;
	private short morelatetomtflag = 0;
	private short priority = 9;
	private String expiretime = new String(
			new byte[SubmitRequest.EXPIRETIME.getLength()],
			GlobalVars.defaultTransportCharset);
	private String scheduletime = new String(
			new byte[SubmitRequest.SCHEDULETIME.getLength()],
			GlobalVars.defaultTransportCharset);
	private short reportflag = 1;
	private short tppid = 0;
	private short tpudhi = 0;
	private short messagecoding = 15;
	private short messagetype = 0;
	private long messagelength = 120;
	private String messagecontent = new String(new byte[(int)messagelength],
			GlobalVars.defaultTransportCharset);
	private String reserve = new String(
			new byte[SubmitRequest.RESERVE.getLength()],
			GlobalVars.defaultTransportCharset);
	
	private byte[] msgContentBytes = new byte[(int)messagelength];
	
	public SubmitRequestMessage() {
		this(SgipPacketType.SUBMITREQUEST);
	}

	public SubmitRequestMessage(long nodeIds) {
		setPacketType(SgipPacketType.SUBMITREQUEST);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber(nodeIds, System.currentTimeMillis()));
		setHeader(header);
	}
	
	public SubmitRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber());
		setHeader(header);
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
	 * @return the chargenumber
	 */
	public String getChargenumber() {
		return chargenumber;
	}

	/**
	 * @param chargenumber the chargenumber to set
	 */
	public void setChargenumber(String chargenumber) {
		this.chargenumber = chargenumber;
	}

	/**
	 * @return the usercount
	 */
	public short getUsercount() {
		return usercount;
	}

	/**
	 * @param usercount the usercount to set
	 */
	public void setUsercount(short usercount) {
		this.usercount = usercount;
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
	 * @return the corpid
	 */
	public String getCorpid() {
		return corpid;
	}

	/**
	 * @param corpid the corpid to set
	 */
	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	/**
	 * @return the servicetype
	 */
	public String getServicetype() {
		return servicetype;
	}

	/**
	 * @param servicetype the servicetype to set
	 */
	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	/**
	 * @return the feetype
	 */
	public short getFeetype() {
		return feetype;
	}

	/**
	 * @param feetype the feetype to set
	 */
	public void setFeetype(short feetype) {
		this.feetype = feetype;
	}

	/**
	 * @return the feevalue
	 */
	public String getFeevalue() {
		return feevalue;
	}

	/**
	 * @param feevalue the feevalue to set
	 */
	public void setFeevalue(String feevalue) {
		this.feevalue = feevalue;
	}

	/**
	 * @return the givenvalue
	 */
	public String getGivenvalue() {
		return givenvalue;
	}

	/**
	 * @param givenvalue the givenvalue to set
	 */
	public void setGivenvalue(String givenvalue) {
		this.givenvalue = givenvalue;
	}

	/**
	 * @return the agentflag
	 */
	public short getAgentflag() {
		return agentflag;
	}

	/**
	 * @param agentflag the agentflag to set
	 */
	public void setAgentflag(short agentflag) {
		this.agentflag = agentflag;
	}

	/**
	 * @return the morelatetomtflag
	 */
	public short getMorelatetomtflag() {
		return morelatetomtflag;
	}

	/**
	 * @param morelatetomtflag the morelatetomtflag to set
	 */
	public void setMorelatetomtflag(short morelatetomtflag) {
		this.morelatetomtflag = morelatetomtflag;
	}

	/**
	 * @return the priority
	 */
	public short getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(short priority) {
		this.priority = priority;
	}

	/**
	 * @return the expiretime
	 */
	public String getExpiretime() {
		return expiretime;
	}

	/**
	 * @param expiretime the expiretime to set
	 */
	public void setExpiretime(String expiretime) {
		this.expiretime = expiretime;
	}

	/**
	 * @return the scheduletime
	 */
	public String getScheduletime() {
		return scheduletime;
	}

	/**
	 * @param scheduletime the scheduletime to set
	 */
	public void setScheduletime(String scheduletime) {
		this.scheduletime = scheduletime;
	}

	/**
	 * @return the reportflag
	 */
	public short getReportflag() {
		return reportflag;
	}

	/**
	 * @param reportflag the reportflag to set
	 */
	public void setReportflag(short reportflag) {
		this.reportflag = reportflag;
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
		if(((0 | 4 | 8) & this.messagecoding) == this.messagecoding) setTpudhi((short)1);
	}

	/**
	 * @return the messagetype
	 */
	public short getMessagetype() {
		return messagetype;
	}

	/**
	 * @param messagetype the messagetype to set
	 */
	public void setMessagetype(short messagetype) {
		this.messagetype = messagetype;
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
				.format("SubmitRequestMessage [spnumber=%s, chargenumber=%s, usercount=%s, usernumber=%s, corpid=%s, servicetype=%s, feetype=%s, feevalue=%s, givenvalue=%s, agentflag=%s, morelatetomtflag=%s, priority=%s, expiretime=%s, scheduletime=%s, reportflag=%s, tppid=%s, tpudhi=%s, messagecoding=%s, messagetype=%s, messagelength=%s, messagecontent=%s, reserve=%s, getPacketType()=%s, getTimestamp()=%s, getChannelIds()=%s, getChildChannelIds()=%s, getLifeTime()=%s, isTerminationLife()=%s, getResponse()=%s, getRequests()=%s, getHeader()=%s, getBodyBuffer()=%s]",
						spnumber, chargenumber, usercount, usernumber, corpid,
						servicetype, feetype, feevalue, givenvalue, agentflag,
						morelatetomtflag, priority, expiretime, scheduletime,
						reportflag, tppid, tpudhi, messagecoding, messagetype,
						messagelength, messagecontent, reserve,
						getPacketType(), getTimestamp(), getChannelIds(),
						getChildChannelIds(), getLifeTime(),
						isTerminationLife(), getResponse(), getRequests(),
						getHeader(), Hex.encodeHexString(getBodyBuffer()));
	}

}
