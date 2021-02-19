import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
	
	MulticastSocket socket = null;
	byte[] buffer = null;
	DatagramSocket receivePacket = null;
	Scanner scan = null;
	
	public static void main (String[] args) {
		Cliente server = new Cliente();
		server.initializeVariable();
		server.connecting();
	}
	
	private void initializeVariable() {
		try {
			socket = new MulticastSocket();
			buffer = new byte[Constantes.BUFFER_SIZE];
			scan = new Scanner(System.in);
			
			log("Servidor Running...");
		} catch (SocketException s) {
			log("initializeVariable : " + s.toString());
		} catch (Exception e) {
			log("initializeVariable : " + e.toString());
		}
	}
	
	private String readFromKeyboard() {
		String line = scan.nextLine();
		return line;
	}
	
	private void send(String message) {
		try {
			InetAddress ip = InetAddress.getByName(Constantes.IP);
			
			buffer = message.getBytes();
			DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length, ip, Constantes.PORT);
			
			socket.send(packetSend);
			log("Message Sent");
			
		} catch (IOException e) {
			log("send: " + e.toString());
		}
	}
	
	private void connecting() {
		String data = readFromKeyboard();
		send(data);
		
		buffer =  new byte[Constantes.BUFFER_SIZE];
	}
	
	private void log(String message) {
		System.out.println(message);
	}
}