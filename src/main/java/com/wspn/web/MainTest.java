package com.wspn.web;

 import java.net.URI;
 import java.net.URISyntaxException;
 import java.util.HashMap;
 import java.util.Map;

 public class MainTest {
 	public static void main(String[] args) throws URISyntaxException {
 		Map<String, String> httpHeaders = new HashMap<>();
 		httpHeaders.put("Origin", "chrome-extension://omalebghpgejjiaoknljcfmglgbpocdp");
 		HashMap<Integer, String> mapMME = new HashMap<>();
 		HashMap<String, Integer> mapResult = new HashMap<>();
 		ExampleClient cilentForENB = new ExampleClient(new URI("ws://10.108.145.150:9001"), httpHeaders,0,1000,"enb","{\"message\": \"ue_get\",\"stats\":true}"); 
 		cilentForENB.setMapMME(mapMME);
 		cilentForENB.setMapResult(mapResult);
 		cilentForENB.setConnectionLostTimeout(0);
 		cilentForENB.connect();
 		ExampleClient cilentForMME = new ExampleClient(new URI("ws://10.108.145.150:9000"), httpHeaders,0,5000,"mme","{\"message\": \"ue_get\",\"stats\":true}");
 		cilentForMME.setConnectionLostTimeout(0);
 		cilentForMME.setMapMME(mapMME);
 		cilentForMME.setMapResult(mapResult);
 		cilentForMME.connect();
 		// while (!c.getReadyState().equals(READYSTATE.OPEN)) {
 		// }
 	}
 }