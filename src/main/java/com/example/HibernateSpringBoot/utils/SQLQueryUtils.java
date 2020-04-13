package com.example.HibernateSpringBoot.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class SQLQueryUtils {
	
	private static final Map<String,String> queriesMap = new HashMap<>();
	
	private static boolean queriesLoaded = false;
	
	private static String dbType="mysql";
	
	private static final String[] queriesFiles = {
			"queries/queries_employees.xml",
			"queries/queries_departments.xml"
	};
	
	private static String getDbType(){
		try{
			Resource resource = new ClassPathResource("application.properties");
			Properties props = new Properties();
			props.load(resource.getInputStream());
			return props.getProperty("dbType");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	private static void loadQueries(){
		
		try{
			String dbType = getDbType();
			System.out.println("db type "+dbType);
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			for(String queryPath: queriesFiles){
				Resource resource = new ClassPathResource(queryPath);
				File file = resource.getFile();
				System.out.println("file name "+file.getName());
				readXmlQueries(docBuilder, file);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception occured in reading xml file ");
		}
				
	}
	
	private static void readXmlQueries(DocumentBuilder docBuilder, File file) throws Exception{
		
		Document doc = docBuilder.parse(file);
		doc.getDocumentElement().normalize();
		NodeList nList = doc.getElementsByTagName("sql-query");

		for(int count =0; count<nList.getLength();count++){
			Node nNode = nList.item(count);
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element)nNode;
				String queryKey = element.getElementsByTagName("query-name").item(0).getTextContent();
				String queryValue = element.getElementsByTagName("query").item(0).getTextContent();
				queriesMap.put(queryKey,queryValue);
			}
		}
				
	}
	
	public static String getQuery(String queryKey){
		if(!queriesLoaded){
			loadQueries();
		}
		queriesLoaded=true;
		return queriesMap.get(queryKey);
	}
}
