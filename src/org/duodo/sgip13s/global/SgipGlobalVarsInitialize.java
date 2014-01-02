/**
 * 
 */
package org.duodo.sgip13s.global;

import java.util.List;
import java.util.Map;

import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.global.DefaultGlobalVarsInitialize;
import org.duodo.netty3ext.global.GlobalVars;
import org.duodo.netty3ext.global.GlobalVarsInitialize;
import org.duodo.sgip13s.factory.config.SgipDownstreamSessionConfigMapFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SgipGlobalVarsInitialize extends DefaultGlobalVarsInitialize {
	public SgipGlobalVarsInitialize() {
		this("sgipsession");
	}
	/**
	 * @param configName
	 */
	public SgipGlobalVarsInitialize(String configName) {
		super(configName);
	}

	@Override
	public GlobalVarsInitialize downstreamSessionConfigInitialize(
			List<String> configList) throws Exception {
		new SgipDownstreamSessionConfigMapFactory<Map<String, Map<String, SessionConfig>>, SessionConfig>(
				GlobalVars.config, GlobalVars.downstreamSessionConfigMap,
				configList).create();
		return this;
	}
	
	
}
