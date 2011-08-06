package com.selonianth.ultimatedispenser;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class HDConfiguration {
	public static final Object LATEST_CONFIG_VERSION = null;
	private File file;
	private Configuration configuration;
	
	public HDConfiguration(File file){
    this.file = file;
    this.configuration = new Configuration(file);
}

	public static HDConfiguration detectConfiguration(File configFile) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object getVersion() {
		// TODO Auto-generated method stub
		return null;
	}

	public HDConfiguration upgrade() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void CONFIG_FILE_NAME() {
		// TODO Auto-generated method stub
		
	}

	public boolean write(UltimateDispenser ultimateDispenser) {
		// TODO Auto-generated method stub
		return false;
	}}