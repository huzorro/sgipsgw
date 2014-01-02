/**
 * 
 */
package org.duodo.sgip13s.service;

import java.util.List;

import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.global.GlobalVarsInitialize;
import org.duodo.netty3ext.plugin.DefaultReceivedMsgPluginManagerService;
import org.duodo.netty3ext.service.Service;
import org.duodo.netty3ext.service.manager.ServerServices;
import org.duodo.sgip13s.global.SgipGlobalVarsInitialize;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SgipServerService implements ServerServices {
	private String configName;
	private static final GlobalVarsInitialize globalVarsInitialize = new SgipGlobalVarsInitialize();

	public SgipServerService() {
		this("sgipsession");
	}

	/**
	 * @param configName
	 */
	public SgipServerService(String configName) {
		this.configName = configName;
	}
	

	@Override
	public ServerServices downstreamGlobalVarsInit() throws Exception {
		return downstreamGlobalVarsInit(null);
	}

	@Override
	public ServerServices downstreamGlobalVarsInit(List<String> configList)
			throws Exception {
		globalVarsInitialize.downstreamSessionConfigInitialize(configList)
				.downstreamThreadPoolInitialize(configList)
				.downstreamSessionPoolInitialize(configList)
				.downstreamMessageQueueInitialize(configList)
				.downstreamServerBootstrapInitialize(configList)
				.downstreamMessagePluginManagerInitialize(configList);
		return this;
	}
	@Override
	public Service downstreamServiceInit() {
		return downstreamServiceInit(null);
	}

	@Override
	public Service downstreamServiceInit(List<String> configList) {
		return new SgipDownstreamServerService(
				GlobalVars.downstreamSessionConfigMap.get(configName), 
				GlobalVars.serverBootstrapMap, 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.responseMsgQueueMap, 
				GlobalVars.deliverMsgQueueMap, 
				GlobalVars.scheduleExecutorMap, 
				GlobalVars.sessionPoolMap, 
				GlobalVars.downstreamServicesRunningList,
				configList);
	}
	@Override
	public Service downstreamReceiveMsgPluginManagerServiceInit() {
		return downstreamReceiveMsgPluginManagerServiceInit(null);
	}

	@Override
	public Service downstreamReceiveMsgPluginManagerServiceInit(
			List<String> configList) {
		return new DefaultReceivedMsgPluginManagerService(
				GlobalVars.downstreamSessionConfigMap.get(configName), 
				GlobalVars.receiveMsgQueueMap, 
				GlobalVars.executorServiceMap, 
				GlobalVars.pluginManagerUtilMap,
				configList);
	}	
	

	@Override
	public ServerServices duplexstreamGlobalVarsInit() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerServices duplexstreamGlobalVarsInit(List<String> configList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReceiveMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReceiveMsgPluginManagerServiceInit(
			List<String> confiList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamResponseMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamResponseMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReserveDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service duplexstreamReserveDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerServices upstreamGlobalVarsInit() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ServerServices upstreamGlobalVarsInit(List<String> configList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamResponseMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamResponseMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverMsgPluginManagerServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverMsgPluginManagerServiceInit(
			List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamReserveDeliverServiceInit() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service upstreamReserveDeliverServiceInit(List<String> configList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void process() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
