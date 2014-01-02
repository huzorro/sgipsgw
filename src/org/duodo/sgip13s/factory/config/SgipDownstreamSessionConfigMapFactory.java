/**
 * 
 */
package org.duodo.sgip13s.factory.config;

import java.util.List;
import java.util.Map;

import org.apache.commons.configuration.CombinedConfiguration;
import org.duodo.netty3ext.config.session.DownstreamSessionConfig;
import org.duodo.netty3ext.config.session.SessionConfig;
import org.duodo.netty3ext.factory.session.config.DownstreamSessionConfigMapFactory;

/**
 * @author huzorro(huzorro@gmail.com)
 *
 */
public class SgipDownstreamSessionConfigMapFactory<T extends Map<String, Map<String, E>>, E extends SessionConfig> extends
		DownstreamSessionConfigMapFactory<T, E> {
	/**
	 * 
	 * @param configurationBuilder
	 * @param sessionConfigMap
	 * @param configList
	 */
	@SuppressWarnings("unchecked")
	public SgipDownstreamSessionConfigMapFactory(
			CombinedConfiguration configurationBuilder, T sessionConfigMap,
			List<String> configList) {
		this(configurationBuilder, sessionConfigMap, "downstream", "sgipsession", (Class<E>) DownstreamSessionConfig.class, configList);
	}

	/**
	 * @param configurationBuilder
	 * @param sessionConfigMap
	 * @param sessionType
	 * @param configName
	 * @param sessionConfig
	 * @param configList
	 */
	public SgipDownstreamSessionConfigMapFactory(
			CombinedConfiguration configurationBuilder, T sessionConfigMap,
			String sessionType, String configName, Class<E> sessionConfig,
			List<String> configList) {
		super(configurationBuilder, sessionConfigMap, sessionType, configName,
				sessionConfig, configList);
	}

}
