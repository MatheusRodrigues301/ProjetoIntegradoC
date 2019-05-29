package DB.Util;

import java.net.*;
/**
 * A classe AceitadoraDeConexao � uma classe que tenta fazer a conex�o do cliente com o servidor.
 */
public class AceitadoraDeConexao extends Thread {
	private ServerSocket pedido;
	private Lista<Parceiro> usuarios;

	/**
	 * M�todo que faz uma ponte para a comunica��o do cliente com o servidor.
	 *
	 * @param porta Porta de conex�o ao servidor.
	 * @param usuarios Cliente.
	 * @throws Exception Caso ocorra um erro ao comunicar o cliente ao servidor 
	 * lan�a-se uma exce��o
	 */
	public AceitadoraDeConexao(int porta, Lista<Parceiro> usuarios) throws Exception {
		if (usuarios == null)
			throw new Exception("Usuarios ausentes");

		this.usuarios = usuarios;

		try {
			this.pedido = new ServerSocket(porta);
		} catch (Exception erro) {
			throw new Exception("Porta invalida");
		}
	}

	/**
	 * M�todo que inst�ncia uma nova thread para cada cliente tornando-os independentes.
	 */
	public void run() {
		for (;;) {
			Socket conexao = null;
			try {
				conexao = this.pedido.accept();
			} catch (Exception erro) {
				continue;
			}
			
			SupervisoraDeConexao supervisoraDeConexao = null;
			try {
				supervisoraDeConexao = new SupervisoraDeConexao(conexao, usuarios);
			} catch (Exception erro) {
			} // sei que passei parametros corretos para o construtor
			supervisoraDeConexao.start();
		}
	}
}
