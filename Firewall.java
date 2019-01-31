package OAFirewall;

import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class Firewall {
	//use a hashmap to store parsed rules
	private HashMap<String, List<FirewallRule>> rulesMap;
	
	public Firewall(String fileName) throws IOException {
		File inputfile = new File(fileName);
		BufferedReader bufferedReader = new BufferedReader(new FileReader(inputfile));
		rulesMap = new HashMap<String, List<FirewallRule>>();
		
		String onerule;
		while ((onerule = bufferedReader.readLine()) != null){
			//System.out.println(onerule);
			String[] fields = onerule.split(",");
			
			if(fields.length == 4) {
				//parse the direction and protocol of the rule
				String direction = fields[0];
				if(fields[0].charAt(0) != 'i' && fields[0].charAt(0) != 'o') {
					direction = fields[0].substring(1);
				}
				String protocol = fields[1];
				
				//parse the port of the rule
				int port[];
				if(fields[2].contains("-")) {
					port = new int[2];
					port[0] = Integer.parseInt(fields[2].split("-")[0]);
					port[1] = Integer.parseInt(fields[2].split("-")[1]);
				} else {
					port = new int[1];
					port[0] = Integer.parseInt(fields[2].split("-")[0]);
				}
				
				//parse the IP address of the rule
				long[] IPAddress;
				if(fields[3].contains("-")) {
					IPAddress = new long[2];
					IPAddress[0] = Long.parseLong((fields[3].split("-")[0].replaceAll("\\.", "")));
					IPAddress[1] = Long.parseLong((fields[3].split("-")[1].replaceAll("\\.", "")));
				} else {
					IPAddress = new long[1];
					IPAddress[0] = Long.parseLong((fields[3].split("-")[0].replaceAll("\\.", "")));
				}
				
				//update the map
				String keyStr = direction + protocol;
				//System.out.println(direction);
				
				List<FirewallRule> tempList = rulesMap.getOrDefault(keyStr, new ArrayList<>());
				tempList.add(new FirewallRule(direction, protocol, port, IPAddress));
				rulesMap.put(keyStr, tempList);
			}
			
		}
		
//		for(Entry<String, List<FirewallRule>> entry : rulesMap.entrySet()) {
//			System.out.print("keyStr: " + entry.getKey() + " ruleList:");
//			for(FirewallRule rule : entry.getValue()) {
//				System.out.print(rule.direction + " " + rule.protocol + " ");
//				if(rule.port.length > 1) {
//					System.out.print(rule.port[0] + "-" + rule.port[1] + " ");
//				} else {
//					System.out.print(rule.port[0] + " ");
//				}
//				if(rule.ip_address.length > 1) {
//					System.out.print(rule.ip_address[0] + "-" + rule.ip_address[1] + " |");
//				} else {
//					System.out.print(rule.ip_address[0] + " |");
//				}
//			}
//			System.out.println();
//		}
		
		
	}
	
	public boolean accept_packet(String direction, String protocol, int port, String ip_address) {
		String keyStr = direction + protocol;
		if(!rulesMap.containsKey(keyStr)) {
			return false;
		}
		for(FirewallRule curRule : rulesMap.get(keyStr)) {
			if(curRule.port.length == 1 && port != curRule.port[0]) {
				return false;
			}
			if(curRule.port.length == 2 && (port < curRule.port[0] || port > curRule.port[1])) {
				return false;
			}
			if(curRule.ip_address.length == 1 && Long.parseLong(ip_address.replaceAll("\\.", "")) != curRule.ip_address[0]) {
				return false;
			}
			if(curRule.ip_address.length == 2 && (Long.parseLong(ip_address.replaceAll("\\.", "")) < curRule.ip_address[0] || Long.parseLong(ip_address.replaceAll("\\.", "")) > curRule.ip_address[1])) {
				return false;
			}
		}
		
		return true;
	}
}
