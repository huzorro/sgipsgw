/**
 * 
 */
package org.duodo.sgip13s.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.DefaultServerSessionFactory;
import org.duodo.netty3ext.factory.session.config.DefaultServerSessionConfigFactory;
import org.duodo.netty3ext.factory.tcp.NettyTcpServerFactory;
import org.duodo.netty3ext.future.QFuture;
import org.duodo.netty3ext.message.Message;
import org.duodo.netty3ext.pool.session.SessionPool;
import org.duodo.netty3ext.queue.BdbQueueMap;
import org.duodo.netty3ext.service.Service;
import org.duodo.netty3ext.session.Session;
import org.duodo.netty3ext.tcp.server.NettyTcpServer;
import org.duodo.sgip13s.factory.pipeline.DownstreamServerChannelPipelineFactory;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SgipDownstreamServerService implements Service {
    private final Logger logger = LoggerFactory.getLogger(SgipDownstreamServerService.class);
    private Map<String, SessionConfig> configMap;
    private Map<SessionConfig, ServerBootstrap> serverBootstrapMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap;
    private Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap;
    private Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap;
    private Map<SessionConfig, SessionPool>  sessionPoolMap;
    private List<String> configList;
    private List<SessionConfig> upstreamServicesRunningList;

    
    /**
     * 
     * @param configMap
     * @param serverBootstrapMap
     * @param requestMsgQueueMap
     * @param responseMsgQueueMap
     * @param deliverMsgQueueMap
     * @param scheduleExecutorMap
     * @param sessionPoolMap
     * @param upstreamServicesRunningList
     */
	public SgipDownstreamServerService(            
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<SessionConfig, SessionPool>  sessionPoolMap,
            List<SessionConfig> upstreamServicesRunningList
            ) {
		this(configMap, serverBootstrapMap, receiveMsgQueueMap,
				responseMsgQueueMap, deliverMsgQueueMap, scheduleExecutorMap,
				sessionPoolMap, upstreamServicesRunningList, null);
	}    
    /**
     * 
     * @param configMap
     * @param serverBootstrapMap
     * @param requestMsgQueueMap
     * @param responseMsgQueueMap
     * @param deliverMsgQueueMap
     * @param scheduleExecutorMap
     * @param sessionPoolMap
     * @param upstreamServicesRunningList
     * @param configList
     */
	public SgipDownstreamServerService(            
            Map<String, SessionConfig> configMap,
            Map<SessionConfig, ServerBootstrap> serverBootstrapMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> receiveMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> responseMsgQueueMap,
            Map<Object, BdbQueueMap<Long, QFuture<Message>>> deliverMsgQueueMap,
            Map<SessionConfig, ScheduledExecutorService> scheduleExecutorMap,
            Map<SessionConfig, SessionPool>  sessionPoolMap,
            List<SessionConfig> upstreamServicesRunningList,
            List<String> configList
            ) {
        this.configMap = configMap;
        this.serverBootstrapMap = serverBootstrapMap;
        this.receiveMsgQueueMap = receiveMsgQueueMap;
        this.responseMsgQueueMap = responseMsgQueueMap;
        this.deliverMsgQueueMap = deliverMsgQueueMap;
        this.scheduleExecutorMap = scheduleExecutorMap;
        this.sessionPoolMap = sessionPoolMap;
        this.upstreamServicesRunningList = upstreamServicesRunningList;
        this.configList = configList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
        try {
            process();
        } catch (Exception e) {
            logger.error("Cmpp Downstream Server Service failed", e.getCause());
            Runtime.getRuntime().exit(-1);
        }

	}

	/* (non-Javadoc)
	 * @see org.duodo.netty3ext.service.Service#process()
	 */
	@Override
	public void process() throws Exception {
        for(SessionConfig config : configMap.values()) {
        	if(configList != null && !configList.contains(config.getChannelIds())) continue;
        	create(config);
        	upstreamServicesRunningList.add(config);
        }

	}
	
	protected void create(SessionConfig config) throws Exception {
		DefaultServerSessionFactory<Session> sessionFactory = new DefaultServerSessionFactory<Session>(
				receiveMsgQueueMap.get(config),
				responseMsgQueueMap.get(config),
				deliverMsgQueueMap.get(config),
				scheduleExecutorMap.get(config), sessionPoolMap.get(config));
		DefaultServerSessionConfigFactory<SessionConfig> configFactory = new DefaultServerSessionConfigFactory<SessionConfig>(
				config);
		ChannelPipelineFactory pipelineFactory = new DownstreamServerChannelPipelineFactory(
				sessionFactory, configFactory, config);
		NettyTcpServerFactory<NettyTcpServer<Channel>> tcpServerFactory = new NettyTcpServerFactory<NettyTcpServer<Channel>>(
				config.getHost(), config.getPort(), pipelineFactory,
				serverBootstrapMap.get(config));
		NettyTcpServer<Channel> tcpServer = tcpServerFactory.create();

		tcpServer.bind();
	}	

}
