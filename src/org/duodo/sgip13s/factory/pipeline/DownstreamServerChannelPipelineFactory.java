/**
 * 
 */
package org.duodo.sgip13s.factory.pipeline;

import java.util.concurrent.TimeUnit;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.DefaultServerSessionFactory;
import org.duodo.netty3ext.factory.session.config.DefaultServerSessionConfigFactory;
import org.duodo.netty3ext.session.Session;
import org.duodo.sgip13s.decoder.BindRequestDecoder;
import org.duodo.sgip13s.decoder.DeliverRequestDecoder;
import org.duodo.sgip13s.decoder.HeaderDecoder;
import org.duodo.sgip13s.decoder.ReportRequestDecoder;
import org.duodo.sgip13s.decoder.SubmitRequestDecoder;
import org.duodo.sgip13s.encoder.BindResponseEncoder;
import org.duodo.sgip13s.encoder.CommonsHeaderEncoder;
import org.duodo.sgip13s.encoder.DeliverResponseEncoder;
import org.duodo.sgip13s.encoder.HeaderEncoder;
import org.duodo.sgip13s.encoder.ReportResponseEncoder;
import org.duodo.sgip13s.encoder.SubmitResponseEncoder;
import org.duodo.sgip13s.handler.BindRequestHandler;
import org.duodo.sgip13s.handler.CommonsMessageHandler;
import org.duodo.sgip13s.handler.DeliverRequestHandler;
import org.duodo.sgip13s.handler.IdleHandler;
import org.duodo.sgip13s.handler.ReportRequestHandler;
import org.duodo.sgip13s.handler.SubmitRequestHandler;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;
import org.jboss.netty.util.Timer;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class DownstreamServerChannelPipelineFactory implements
		ChannelPipelineFactory {
	private DefaultServerSessionFactory<Session> sessionFactory;
	private DefaultServerSessionConfigFactory<SessionConfig> configFactory;
	private SessionConfig config;
	private Timer timer;

	
	/**
	 * @param sessionFactory
	 * @param configFactory
	 * @param config
	 */
	public DownstreamServerChannelPipelineFactory(
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory,
			SessionConfig config) {
		this(sessionFactory, configFactory, config, new HashedWheelTimer());
	}

	/**
	 * @param sessionFactory
	 * @param configFactory
	 * @param config
	 * @param timer
	 */
	public DownstreamServerChannelPipelineFactory(
			DefaultServerSessionFactory<Session> sessionFactory,
			DefaultServerSessionConfigFactory<SessionConfig> configFactory,
			SessionConfig config, Timer timer) {
		this.sessionFactory = sessionFactory;
		this.configFactory = configFactory;
		this.config = config;
		this.timer = timer;
	}

	/* (non-Javadoc)
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
        ChannelPipeline pipeline = Channels.pipeline();
        
		pipeline.addLast("IdleStateHandler", new IdleStateHandler(timer, 0, 0,
				config.getIdleTime(), TimeUnit.SECONDS));
		pipeline.addLast("IdleHandler", new IdleHandler());
		
		pipeline.addLast("HeaderDecoder", new HeaderDecoder());
		
		pipeline.addLast("BindRequestDecoder", new BindRequestDecoder());
		pipeline.addLast("BindResponseEncoder", new BindResponseEncoder());
		
		pipeline.addLast("DeliverRequestDecoder", new DeliverRequestDecoder());
		pipeline.addLast("DeliverResponseEncoder", new DeliverResponseEncoder());

		pipeline.addLast("ReportRequestDecoder", new ReportRequestDecoder());
		pipeline.addLast("ReportResponseEncoder", new ReportResponseEncoder());
		
		pipeline.addLast("SubmitRequestDecoder", new SubmitRequestDecoder());
		pipeline.addLast("SubmitResponseEncoder", new SubmitResponseEncoder());
		
		pipeline.addLast("BindRequestHandler", new BindRequestHandler(
				sessionFactory, configFactory));
		
		pipeline.addLast("HeaderEncoder", new HeaderEncoder());
		pipeline.addLast("CommonsHeaderEncoder", new CommonsHeaderEncoder());
		pipeline.addLast("CommonsMessageHandler", new CommonsMessageHandler());
		

		pipeline.addLast("DeliverRequestHandler", new DeliverRequestHandler());
		pipeline.addLast("ReportRequestHandler", new ReportRequestHandler());
		pipeline.addLast("SubmitRequestHandler", new SubmitRequestHandler());
		
		return pipeline;
	}

}
