package run;

import java.net.UnknownHostException;

import client.Client;

public class Run {

    public static void main (String[] args) throws UnknownHostException {
    	
    	Client newClient = new Client();
        
    	System.out.println(newClient.ClientIP());
        System.out.println(newClient.RandomMAC());
        System.out.println(newClient.ClientOS());
        System.out.println(newClient.ClientMAC());
        
    }
}
