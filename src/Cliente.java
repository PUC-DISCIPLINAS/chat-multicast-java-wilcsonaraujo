import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	public static void main(String[] args) {
		try {
			Socket cliente = new Socket("127.0.0.1", 9999);
			
			cliente.getInputStream();
			
			/**
			 * Mensagens lidas do servidor
			 */
			new Thread() {
				@Override
				public void run() {
					try {
						BufferedReader leitor =  new BufferedReader(new InputStreamReader(cliente.getInputStream()));
						PrintWriter escritor = new PrintWriter(cliente.getOutputStream());
						
						while (true) {
							String mensagem = leitor.readLine();
							if(mensagem == null || mensagem.isEmpty()) continue;
							System.out.println("[SERVIDOR]: "+ mensagem);							
						}
						
					} catch (IOException e) {
						System.out.println("Impossível ler a mensagem do servidor.");
						e.printStackTrace();
					}
				}
			}.start();
			
			/**
			 * Escrevendo para o servidor
			 */
			PrintWriter escritor = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader leitorTeclado = new BufferedReader(new InputStreamReader(System.in));
			
			String mensagemTeclado = "";
			while (true) {
				mensagemTeclado = leitorTeclado.readLine();
				if(mensagemTeclado == null || mensagemTeclado.length() == 0 ) {
					continue;
				}
				escritor.println(mensagemTeclado);
				if(mensagemTeclado.equalsIgnoreCase("::sair")) {
					System.exit(0);
				}
			}
			
		} catch (UnknownHostException e) {
			System.out.println("O endereço passado é inválido");
			e.printStackTrace();
		} catch(IOException e) {
			System.out.println("O servidor está fora do ar");
			e.printStackTrace();
		}
	}
}
