import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Servidor {
	
	static Map<Integer, String> salas_ = new HashMap<Integer, String>();
	public static void main(String[] args) {
		ServerSocket servidor = null;
	
		
		try {
			System.out.println("Iniciando servidor");
			servidor = new ServerSocket(9999);
			System.out.println("Servidor iniciado");
			
			while (true) {
				Socket cliente = servidor.accept();
				new GerenciadorDeClientes(cliente);
			}
			
		} catch (IOException e) {
			try {
				if(servidor != null) {
					servidor.close();
				}
			} catch (IOException e2) {}
			System.err.println("A porta está aocupada ou servidor foi fechado.");
			e.printStackTrace(); 
			
		}
		
		
	}
	public static Map<Integer, String> saleando() {
		salas_.put(1, "127.0.0.1");
		salas_.put(2, "127.0.0.2");
		salas_.put(3, "127.0.0.3");
		salas_.put(4, "127.0.0.4");
		salas_.put(5, "127.0.0.5");
		return salas_;
	}
}
