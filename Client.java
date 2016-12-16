package socket_;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
 
	private static Socket socket;
	static Scanner input = new Scanner(System.in);
	static String sayi;
	 public static void main(String args[])
	    {
	        try
	        {
	            String host = "localhost";
	            int port = 25000;
	            InetAddress address = InetAddress.getByName(host);
	            socket = new Socket(address, port);
	 
	            //Send the message to the server
	            OutputStream os = socket.getOutputStream();
	            OutputStreamWriter osw = new OutputStreamWriter(os);
	            BufferedWriter bw = new BufferedWriter(osw);
	 
	          
	            String msj="5";

	            String sendMessage = msj+ "\n";
	            bw.write(sendMessage);
	            bw.flush();
	            System.out.println("Sunucuya mesaj gönderin : "+sendMessage);
	 
	            //Get the return message from the server
	            InputStream is = socket.getInputStream();
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String message = br.readLine();
	            System.out.println("Sunucudan alýnan mesaj : " +message);
	        }
	        catch (Exception exception)
	        {
	            exception.printStackTrace();
	        }
	        finally
	        {
	            //Closing the socket
	            try
	            {
	                socket.close();
	            }
	            catch(Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	}

