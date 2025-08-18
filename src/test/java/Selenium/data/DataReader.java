package Selenium.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap() throws IOException
	{
		String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//Selenium//data//PurchaseOrder.json"),StandardCharsets.UTF_8);
		
		//FileUtils.readFileToString(new File("")): Read Json file and convert into the string.
		//JsonContent: store the data
		
		//String convert to HashMap.
		//Jackson Databid: this is a dependency which helps to convert string content to HashMap.
		//Add this dependencies to Pom file, Go to maven repository > search for Jackson Databid > add to pom file.
		
		ObjectMapper mapper = new ObjectMapper(); //call this class.
		
		List<HashMap<String,String>> data = mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>(){	
			
		});
		
		return data;
		
	}
}
