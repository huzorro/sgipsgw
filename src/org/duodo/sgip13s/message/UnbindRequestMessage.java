/**
 * 
 */
package org.duodo.sgip13s.message;

import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.netty3ext.util.SequenceNumber;
import org.duodo.sgip13s.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class UnbindRequestMessage extends DefaultMessage {
	
	private static final long serialVersionUID = 6344903835739798820L;
	public UnbindRequestMessage() {
		this(SgipPacketType.UNBINDREQUEST);
	}
	
	public UnbindRequestMessage(long nodeIds) {
		setPacketType(SgipPacketType.UNBINDREQUEST);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber(nodeIds, System.currentTimeMillis()));
		setHeader(header);
	}
	
	public UnbindRequestMessage(PacketType packetType) {
		setPacketType(packetType);
    	Header header = new DefaultHead();
		header.setSequenceId(new SequenceNumber());
		setHeader(header);
	}

}
