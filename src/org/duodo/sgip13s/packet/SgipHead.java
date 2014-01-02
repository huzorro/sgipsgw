/**
 * 
 */
package org.duodo.sgip13s.packet;

import org.duodo.netty3ext.packet.DataType;
import org.duodo.netty3ext.packet.Head;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public enum SgipHead implements Head {
	MESSAGELENGTH(SgipDataType.UNSIGNEDINT, 4),
	COMMANDID(SgipDataType.UNSIGNEDINT, 4),
	SEQUENCENUMBER(SgipDataType.UNSIGNEDINT, 12);
	
    private DataType dataType;
    private int length;
    private SgipHead(DataType dataType, int length){
        this.dataType = dataType;
        this.length = length;
    }
    public DataType getDataType() {
        return dataType; 
    }
    public int getLength() {
        return length;
    }
    public int getHeadLength() {
        int length = 0;
        for(SgipHead head : SgipHead.values()) {
            length += head.getLength();
        }
        return length;
    }	
}
