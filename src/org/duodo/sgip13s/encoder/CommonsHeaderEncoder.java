/**
 * 
 */
package org.duodo.sgip13s.encoder;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.message.DefaultHead;
import org.duodo.netty3ext.message.Header;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.session.Session;
import org.duodo.netty3ext.util.SequenceNumber;
import org.duodo.sgip13s.packet.SgipHead;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class CommonsHeaderEncoder extends OneToOneEncoder {

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.codec.oneone.OneToOneEncoder#encode(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.channel.Channel, java.lang.Object)
	 */
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object msg) throws Exception {
		if(!(msg instanceof Message)) return msg;
        Message message = (Message) msg;
        
        Header header = new DefaultHead();
        header.setCommandId(message.getPacketType().getCommandId());
        header.setHeadLength(SgipHead.COMMANDID.getHeadLength());
		header.setBodyLength(message.getPacketType().getPacketStructures().length > 0 ? message
				.getPacketType().getPacketStructures()[0].getBodyLength() : 0);
        header.setPacketLength(header.getHeadLength() + header.getBodyLength());
        
        SessionConfig config = ((Session) ctx.getChannel().getAttachment()).getConfig();
        
		header.setSequenceId(message.getRequest() != null ? message
				.getRequest().getHeader().getSequenceId()
				: (message.getHeader() != null ? message.getHeader().getSequenceId()
						: new SequenceNumber(config.getGateId(), System.currentTimeMillis())));
		
        message.setHeader(header);
        return message;		
	}

}
