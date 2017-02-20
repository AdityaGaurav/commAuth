package com.imaginea.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.io.FileHandler;

public class FileUtilities {

	public boolean isReportFolderExists= false;
	
	public void deleteExisitngFolder(String fPath){
	    FileHandler.delete(new File(fPath));		
	}
	
	
	public static Properties getProperties(){
		File file = new File(System.getProperty("user.dir") + File.separator +"src"+ File.separator +"test"+ File.separator +"resources"+ File.separator +"config.properties");
		FileInputStream fileInput=null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties properties = new Properties();
		try {
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
