package run;

import java.net.UnknownHostException;
import java.security.Security;

import client.Client;

public class Run {

    public static void main (String[] args) throws UnknownHostException {
    	Security.setProperty("networkaddress.cache.ttl", "0");
    	Client newClient = new Client();
        
    	System.out.println(newClient.ClientIP());
        System.out.println(newClient.ClientOS());
        System.out.println(newClient.ClientMAC());
        newClient.ExecuteShellCmd(newClient.ShellCommand(newClient.ClientOS(), newClient.RandomMAC()));
        
    }
}
