/**
 * 
 */
package org.duodo.sgip13s.handler;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class IdleHandler extends IdleStateAwareChannelHandler {

	/* (non-Javadoc)
	 * @see org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler#channelIdle(org.jboss.netty.channel.ChannelHandlerContext, org.jboss.netty.handler.timeout.IdleStateEvent)
	 */
	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e)
			throws Exception {
		if (e.getState() == IdleState.ALL_IDLE) ctx.getChannel().close();
		super.channelIdle(ctx, e);
	}
	
}
