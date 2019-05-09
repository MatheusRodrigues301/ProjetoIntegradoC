package Client;

import java.io.*;
import java.net.*;
import javax.swing.*;

import Client.Util.*;
import Client.View.JanelaDeMusicas;

public class Client {
	public static void main(String[] args) {
		if (args.length > 2) {
			System.err.println("Uso esperado: java Chat [HOST [PORTA]]\n");
			return;
		}

		Socket conexao = null;
		try {
			String host = "localhost";
			int porta = 12345;

			if (args.length > 0)
				host = args[0];

			if (args.length == 2)
				porta = Integer.parseInt(args[1]);

			conexao = new Socket(host, porta);
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectOutputStream transmissor = null;
		try {
			transmissor = new ObjectOutputStream(conexao.getOutputStream());
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		ObjectInputStream receptor = null;
		try {
			receptor = new ObjectInputStream(conexao.getInputStream());
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

		Parceiro servidor = null;
		try {
			servidor = new Parceiro(conexao, receptor, transmissor);
		} catch (Exception erro) {
			System.err.println("Indique o servidor e a porta corretos!\n");
			return;
		}

//		JanelaDeMusicas janelaDeMusica = null;
//		try {
//			janelaDeMusica = new JanelaDeMusicas(servidor);
//		} catch (Exception erro) {
//		} // try/catch anterior garante ausencia de erro aqui

//        JanelaDeChat janelaDeChat =
//        janelaDeEscolhaDeNick.getJanelaDeChat ();
//        janelaDeEscolhaDeNick.setVisible      (false);
//        janelaDeEscolhaDeNick.dispose         ();


		for (;;) {
			try {
				servidor.receba(new Comunicado("COM", "ROCK", "LINKIN PARK"));
				Comunicado comunicado = servidor.envie();

				if (comunicado == null) // servidor desconectou
					break;
				else if (comunicado.getComando().equals("COM")) {
					System.out.println("TO NO GET COMANDO COM");
					String remetente = comunicado.getComplemento1();
					String texto = comunicado.getComplemento2();

//    				janelaDeChat.novaMensagem (remetente,
//											   "Voc�", // destinatario
//											   texto);
				} else if (comunicado.getComando().equals("MUS")) {
					System.out.println("TO NO GET COMANDO MUS");
//					String nick = comunicado.getComplemento1();
//					janelaDeChat.novoUsuario   (nick);
//					janelaDeChat.novaMensagem  (nick, "entrou");
//				} else if (comunicado.getComando().equals("FOI")) {
////					String nick = comunicado.getComplemento1();
////					janelaDeChat.removaUsuario (nick);
////					janelaDeChat.novaMensagem  (nick, "saiu");
				} else if (comunicado.getComando().equals("FIC")) {
					System.out.println("TO NO GET COMANDO FIC");
//					janelaDeChat.setVisible (false);

//					JOptionPane.showConfirmDialog(null/* sem janela m�e */, "Tente voltar a usar o servi�o mais tarde!",
//							"Bate-papo em manuten��o", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE,
//							null/* sem icone */);
//
//					servidor.adeus();
//					System.exit(0);
				}
			} catch (Exception erro) {
				JOptionPane.showMessageDialog(null/* sem janela m�e */, "Tente novamente mais tarde!",
						"Erro de conectividade", JOptionPane.ERROR_MESSAGE);

				System.exit(0);
			}
		}
	}
}