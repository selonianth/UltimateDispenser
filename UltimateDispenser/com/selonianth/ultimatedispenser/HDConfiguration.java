package com.selonianth.ultimatedispenser;

import java.io.File;

import org.bukkit.util.config.Configuration;

import sun.text.normalizer.VersionInfo;

public class HDConfiguration {
	public static final Object LATEST_CONFIG_VERSION = null;
	private File file;
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
		// TODO Auto-generated method stub
	return "1.0.0";
		}

	public static void CONFIG_FILE_NAME() {
		// TODO Auto-generated method stub
		
	}

	public boolean write(UltimateDispenser ultimateDispenser) {
		// TODO Auto-generated method stub
		return true;
	}}