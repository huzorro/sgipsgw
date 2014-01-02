/**
 * 
 */
package org.duodo.sgip13s.packet;

import org.duodo.netty3ext.packet.PacketStructure;
import org.duodo.netty3ext.packet.PacketType;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum SgipPacketType implements PacketType {
	BINDREQUEST(0x00000001L, BindRequest.class),
	BINDRESPONSE(0x80000001L, BindResponse.class),
	UNBINDREQUEST(0x00000002L, UnbindRequest.class),
	UNBINDRESPONSE(0x80000002L, UnbindResponse.class),
	SUBMITREQUEST(0x00000003L, SubmitRequest.class),
	SUBMITRESPONSE(0x80000003L, SubmitResponse.class),
	DELIVERREQUEST(0x00000004L, DeliverRequest.class),
	DELIVERRESPONSE(0x80000004L, DeliverResponse.class),
	REPORTREQUEST(0x00000005L, ReportRequest.class),
	REPORTRESPONSE(0x80000005L, ReportResponse.class);

    private long commandId;
    private Class<? extends PacketStructure> packetStructure;
    
    private SgipPacketType(long commandId, Class<? extends PacketStructure> packetStructure) {
        this.commandId = commandId;
        this.packetStructure = packetStructure;
    }
    public long getCommandId() {
        return commandId;
    }
    public PacketStructure[] getPacketStructures() {
    	return packetStructure.getEnumConstants();
    }

    public long getAllCommandId() {
        long defaultId = 0x0;
        long allCommandId = 0x0;
        for(SgipPacketType packetType : SgipPacketType.values()) {
            allCommandId |= packetType.commandId;
        }
        return allCommandId ^ defaultId;
    }
}
