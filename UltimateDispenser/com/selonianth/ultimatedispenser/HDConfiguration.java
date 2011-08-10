package com.selonianth.ultimatedispenser;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class HDConfiguration {
	public static final Object LATEST_CONFIG_VERSION = null;
	@SuppressWarnings("unused")
	private File file;
	@SuppressWarnings("unused")
	private Configuration configuration;
	public  HDConfiguration(File file){
    this.file = file;
    this.configuration = new Configuration(file);
	}
    public static void detectConfiguration(File configFile) {
		// Find and select the configuration file.
		System.out.println("Configuration File Detected");
	}

	public String getVersion() {
		// Gives Version Number
	return "1.0.0";
		}

	public String CONFIG_FILE_NAME() {
		// Points at configuration file.
	  return "config.yml";
	}

	public boolean write(UltimateDispenser ultimateDispenser) {
		// TODO Auto-generated method stub
		return true;
	}}