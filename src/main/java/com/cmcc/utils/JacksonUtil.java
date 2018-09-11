package com.cmcc.utils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
	/*	这是需要的依赖包
	<dependency>
	<groupId>com.fasterxml.jackson.core</groupId>
	<artifactId>jackson-databind</artifactId>
	<version>2.5.3</version>
	</dependency>*/
public class JacksonUtil {

	/**
	    所有类型都可以转为Json
	 * @param obj
	 * @return
	 */
	public static  String Object2Json(Object obj) {
		String jsonStr = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			jsonStr = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
		return jsonStr;	
	}
	
    /**
     * JSON数据类型转为Bean
     * @param jsonStr
     * @param obj
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
	public static <T> T  Json2Bean(String jsonStr,Class<T> obj) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		T t = mapper.readValue(jsonStr, obj);
		return t;
	}
	
	/**
	 * Map 转为 Json
	 * @param jsonStr
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static  Map<Object,Object> Json2Map(String jsonStr) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<Object,Object> map = mapper.readValue(jsonStr, Map.class);
		return map;
		
	}
	
	/**
	 * Json 转为 Lsit
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static List<Object> Json2List(String jsonStr) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		List<Object> list = mapper.readValue(jsonStr, List.class);
		return list;
	}
	
}
