/**
 * 
 */
package org.duodo.sgip13s.packet;

import org.duodo.netty3ext.packet.DataType;
import org.duodo.netty3ext.packet.PacketStructure;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum SubmitResponse implements PacketStructure {
	RESULT(SgipDataType.UNSIGNEDINT, true, 1),
	RESERVE(SgipDataType.OCTERSTRING, true, 8);
	
	private SgipDataType dataType;
    private boolean isFixFiledLength; 
    private int length;
    
    private SubmitResponse(SgipDataType dataType, boolean isFixFiledLength, int length) {
    	this.length = length;
    	this.dataType = dataType;
    	this.isFixFiledLength = isFixFiledLength;
    }
	@Override
	public DataType getDataType() {
		return dataType;
	}

	@Override
	public boolean isFixFiledLength() {
		return isFixFiledLength;
	}

	@Override
	public boolean isFixPacketLength() {
		return true;
	}

	@Override
	public int getLength() {
		return length;
	}

	@Override
	public int getBodyLength() {
		int bodyLength = 0;
		for(SubmitResponse r : SubmitResponse.values()) {
			bodyLength += r.getLength();
		}
		return bodyLength;
	}		
}
