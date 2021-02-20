import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GerenciadorDeClientes extends Thread{
	
	private Socket cliente;
	private String nomeCliente;
	private BufferedReader leitor;
	private PrintWriter escritor;
	Map<Integer, String> salas_ = Servidor.saleando();
	Map<String, GerenciadorDeClientes> clientes = new HashMap<String, GerenciadorDeClientes>();

	public GerenciadorDeClientes (Socket cliente) {
		this.cliente = cliente;
		start();
	}
	
	@Override
	public void run() {
		try {
			leitor = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			escritor = new PrintWriter(cliente.getOutputStream(), true);
			
			escritor.println("Escreva seu nome/apelido:");
			String msg = leitor.readLine();
			this.nomeCliente = msg.toLowerCase().replace(",", "");
			escritor.println("Olá "+ this.nomeCliente);
			clientes.put(this.nomeCliente, this);
			
			while (true) {
				msg = leitor.readLine();	
				if(msg.equalsIgnoreCase("::salas")) {
					escritor.println("Salas disponíveis: ");
					for (int i = 1; i < salas_.size(); i++) {
						escritor.println(salas_.get(i));
					}
				} else if(msg.equalsIgnoreCase("::sair")) {
					this.cliente.close();
				}else if(msg.toLowerCase().startsWith("::msg")){
					String nomeDestinatario = msg.substring(5, msg.length());
					System.out.println("Enviado para "+ nomeDestinatario);
					GerenciadorDeClientes destinatario = clientes.get(nomeDestinatario);
					if(destinatario == null) {
						escritor.println("O cliente informado não está na sala.");
					}else{
						escritor.println("Digite a menságem para "+ destinatario.getNomeCliente());
						destinatario.getEscritor().println(this.nomeCliente + "disse "+ leitor.readLine());
					}
					
				}else if(msg.equals("::ListarClientes")) {
					StringBuffer str = new StringBuffer();
					for (String c: clientes.keySet()) {
						str.append(c);
						str.append(",");
					}
					str.delete(str.length()-1, str.length());
					escritor.println(str.toString());
				}
				else {
					escritor.println("["+this.nomeCliente+"]: "+ msg);
				}
			}
			
		} catch (IOException e) {
			System.out.println("O cliente fechou a conexão.");
			e.printStackTrace();
		}
		
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}

	public PrintWriter getEscritor() {
		return escritor;
	}
}
