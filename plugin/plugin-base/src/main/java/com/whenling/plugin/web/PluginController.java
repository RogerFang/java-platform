package com.whenling.plugin.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whenling.module.domain.model.Result;
import com.whenling.plugin.model.Plugin;
import com.whenling.plugin.model.PluginConfig;
import com.whenling.plugin.service.PluginConfigService;

public class PluginController<T extends Plugin> {

	@Autowired
	private PluginConfigService pluginConfigService;

	@Autowired
	private T plugin;

	/**
	 * 安装
	 */
	@RequestMapping(value = "/install", method = RequestMethod.POST)
	public @ResponseBody Result install() {
		if (!plugin.getIsInstalled()) {
			PluginConfig pluginConfig = pluginConfigService.newEntity();
			pluginConfig.setPluginId(plugin.getId());
			pluginConfig.setIsEnabled(false);
			pluginConfigService.save(pluginConfig);
		}
		return Result.success();
	}

	/**
	 * 卸载
	 */
	@RequestMapping(value = "/uninstall", method = RequestMethod.POST)
	public @ResponseBody Result uninstall() {
		if (plugin.getIsInstalled()) {
			PluginConfig pluginConfig = plugin.getPluginConfig();
			pluginConfigService.delete(pluginConfig);
		}
		return Result.success();
	}

	/**
	 * 设置
	 */
	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public @ResponseBody PluginConfig setting(ModelMap model) {
		PluginConfig pluginConfig = plugin.getPluginConfig();
		return pluginConfig;
	}

	/**
	 * 更新
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody Result update(@ModelAttribute("pluginConfig") @Valid PluginConfig pluginConfig, BindingResult result, Model model) {

		preUpdate(pluginConfig, result, model);

		if (result.hasErrors()) {
			return Result.validateError(result.getAllErrors());
		}

		pluginConfigService.save(pluginConfig);

		return Result.success();
	}

	protected void preUpdate(PluginConfig pluginConfig, BindingResult result, Model model) {

	}

	public PluginConfigService getPluginConfigService() {
		return pluginConfigService;
	}

	public T getPlugin() {
		return plugin;
	}

}
