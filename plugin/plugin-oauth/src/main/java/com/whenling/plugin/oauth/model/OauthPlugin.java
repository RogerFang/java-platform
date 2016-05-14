package com.whenling.plugin.oauth.model;

import java.util.Map;

import com.whenling.plugin.model.Plugin;
import com.whenling.plugin.model.PluginConfig;

public abstract class OauthPlugin extends Plugin {

	public static final String CLIENT_ID_ATTRIBUTE_NAME = "client_id";

	public static final String CLIENT_SECRET_ATTRIBUTE_NAME = "client_secret";

	/** ICON属性名称 */
	public static final String ICON_ATTRIBUTE_NAME = "icon";

	/** 描述属性名称 */
	public static final String DESCRIPTION_ATTRIBUTE_NAME = "description";

	/**
	 * 获取客户端ID
	 * 
	 * @return
	 */
	public String getClientId() {
		PluginConfig pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(CLIENT_ID_ATTRIBUTE_NAME) : null;
	}

	/**
	 * 获取客户端密钥
	 * 
	 * @return
	 */
	public String getClientSecret() {
		PluginConfig pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(CLIENT_SECRET_ATTRIBUTE_NAME) : null;
	}

	/**
	 * 获取LOGO
	 * 
	 * @return LOGO
	 */
	public String getLogo() {
		PluginConfig pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(ICON_ATTRIBUTE_NAME) : null;
	}

	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		PluginConfig pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(DESCRIPTION_ATTRIBUTE_NAME) : null;
	}

	public String getRedirectUri() {
		return getSiteUrl() + "/oauth/api/" + getId();
	}

	public abstract String getAuthorizationUrl();

	public abstract Map<String, Object> getAuthorizationParameterMap();

	public abstract String getAccessToken(String code);

	public abstract OauthUser getOauthUser(String accessToken);
}
