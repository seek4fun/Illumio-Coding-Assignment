package OAFirewall;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class main {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			Firewall myFirewall = new Firewall("test.csv");
			System.out.println(myFirewall.accept_packet("inbound", "tcp", 80, "192.168.1.2"));
            System.out.println(myFirewall.accept_packet("inbound", "udp", 53, "192.168.2.1"));
            System.out.println(myFirewall.accept_packet("outbound", "tcp", 10234, "192.168.10.11"));
            System.out.println(myFirewall.accept_packet("inbound", "tcp", 81, "192.168.1.2"));
            System.out.println(myFirewall.accept_packet("outbound", "udp", 2001, "52.12.48.92"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error input filename.");
			e.printStackTrace();
		}
	}

}
