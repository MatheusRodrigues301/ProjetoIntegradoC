package DB.Util;

import java.io.*;
import java.net.*;

import Helper.Comunicado;

/**
 * A classe Parceiro � a classe para a cria��o de um parceiro para a 
 * conex�o dele com o servidor.
 */
public class Parceiro {
	private Socket conexao;
	private ObjectInputStream receptor;
	private ObjectOutputStream transmissor;

	/**
	 * Instancia um novo parceiro para sua conex�o com o servidor.
	 *
	 * @param conexao Socket de conex�o com o servidor.
	 * @param receptor Receptor da informa��o.
	 * @param transmissor Transmissor da informa��o
	 * @throws Exception Caso occora um erro ao instanciar o novo parceiro lan�a-se uma excess�o.
	 */
	public Parceiro(Socket conexao, ObjectInputStream receptor, ObjectOutputStream transmissor) throws Exception //se o parrametro nulos
	{
		if (conexao == null)
			throw new Exception("Conexao ausente");

		if (receptor == null)
			throw new Exception("Receptor ausente");

		if (transmissor == null)
			throw new Exception("Transmissor ausente");

		this.conexao = conexao;
		this.receptor = receptor;
		this.transmissor = transmissor;
	}

	/**
	 * M�todo responsavel por fazer com que o Cliente transmita informa��es ao Servidor.
	 *
	 * @param x Comunicado a ser transmitido.
	 * @throws Exception Caso ocorra um erro ao transmitir o Comunicado lan�a-se uma exce��o.
	 */
	public void receba(Comunicado x) throws Exception {
		try {
			this.transmissor.writeObject(x);
			this.transmissor.flush();
		} catch (IOException erro) {
			throw new Exception("Erro de transmissao");
		}
	}

	/**
	 * M�todo que espera o retorno do Servidor com as informa��es ao Cliente.
	 * 
	 * @return Comunicado a ser recebido pelo Cliente.
	 * @throws Exception Em caso de erro na recep��o do comunicado lan�a-se exce��o.
	 */
	public Comunicado envie() throws Exception {
		try {
			return (Comunicado) this.receptor.readObject();
		} catch (Exception erro) {
			throw new Exception("Erro de recepcao");
		}
	}
}