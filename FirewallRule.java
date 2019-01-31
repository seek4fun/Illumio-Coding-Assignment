package OAFirewall;

public class FirewallRule {
	
	String direction;
	String protocol;
	int[] port;
	long[] ip_address;
	
	public FirewallRule(String direction, String protocol, int[] port, long[] ip_address) {
		this.direction = direction;
		this.protocol = protocol;
		this.port = port;
		this.ip_address = ip_address;
	}
}
