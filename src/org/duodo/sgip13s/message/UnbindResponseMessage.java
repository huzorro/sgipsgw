/**
 * 
 */
package org.duodo.sgip13s.message;

import org.duodo.netty3ext.message.DefaultMessage;
import org.duodo.netty3ext.packet.PacketType;
import org.duodo.sgip13s.packet.SgipPacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class UnbindResponseMessage extends DefaultMessage {
	private static final long serialVersionUID = 4638514500085975L;
	public UnbindResponseMessage() {
		this(SgipPacketType.UNBINDRESPONSE);
	}
	public UnbindResponseMessage(PacketType packetType) {
		setPacketType(packetType);
	}

}
