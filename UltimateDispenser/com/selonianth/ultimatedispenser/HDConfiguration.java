package com.selonianth.ultimatedispenser;

import java.io.File;

import org.bukkit.util.config.Configuration;

public class HDConfiguration {
	private File file;
	private Configuration configuration;
	
	public HDConfiguration(File file){
    this.file = file;
    this.configuration = new Configuration(file);
}}