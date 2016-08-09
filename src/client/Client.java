
package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;


public class Client {

    // Returns the client IP address
    public String ClientIP() throws UnknownHostException {

        InetAddress deviceip = InetAddress.getLocalHost();
        return String.valueOf(deviceip);
    }



    // Return the clients MAC Address
    public String ClientMAC(){

        String finalclmac = null;
        try {
            InetAddress clientip;
            clientip = InetAddress.getLocalHost();

            NetworkInterface network = NetworkInterface.getByInetAddress(clientip);

            byte[] clmac = network.getHardwareAddress();

            StringBuilder clmacsb = new StringBuilder();

            for(int i = 0; i < clmac.length; i++){
                clmacsb.append(String.format("%02X%s", clmac[i], (i < clmac.length - 1) ? "-" : ""));
            }
             finalclmac = String.valueOf(clmacsb);
        }
        catch (UnknownHostException e){
            System.out.println(e);
        } catch (SocketException e){
            System.out.println(e);
        }
        return finalclmac;
    }


    // Generates and returns a random MAC Address
    public String RandomMAC(){

        Random rand = new Random();
        byte[] macAddr = new byte[6];
        rand.nextBytes(macAddr);

        macAddr[0] = (byte)(macAddr[0] & (byte)254);  //zeroing last 2 bytes to make it unicast and locally adminstrated

        StringBuilder sb = new StringBuilder(18);
        for(byte b : macAddr){

            if(sb.length() > 0)
                sb.append(":");

            sb.append(String.format("%02x", b));
        }


        return sb.toString();
    }

    
    // When Called Returns the Operating System as a String
    public String ClientOS(){
        String os = System.getProperty("os.name");

        return os ;
    }
    
    // When called takes a command as a string and returns terminal output as string
    public String ExecuteShellCmd(String command){
		
    	StringBuffer output = new StringBuffer();
    	
    	Process p;
    	try{
    		p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
                            new BufferedReader(new InputStreamReader(p.getInputStream()));

                        String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	
    	
    	return output.toString();
    	
    }
    
    public String ShellCommand (String os, String randmac){
		if (os.equals("Mac OS X")){
			os = "sudo ifconfig en0 ether " + randmac;
		} else {
			os = "";
		}
    	
    	return os;
    	
    }

}