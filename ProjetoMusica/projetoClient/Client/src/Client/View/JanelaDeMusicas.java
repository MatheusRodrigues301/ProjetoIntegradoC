package Client.View;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import Client.Util.*;
import Helper.*;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

public class JanelaDeMusicas extends JFrame {

	private JPanel contentPane;
	private JTextField txtPesquisa;
	private Parceiro servidor;
	private JButton btnPesquisar;
	private JComboBox<String> cmbCategoria;
	private Lista<Musica> musicas;
	private JList<String> listMusicas;
	private JList<String> listCarrinho;
	private JButton Remover;

	// MODEL
	private DefaultListModel<String> dmMusicas = new DefaultListModel<String>();
	private DefaultListModel<String> dmCarrinho = new DefaultListModel<String>();

	public JanelaDeMusicas(Parceiro servidor) throws Exception {
		if (servidor == null) {
			JOptionPane.showMessageDialog(null/* sem janela m�e */, "Tente novamente mais tarde!",
					"Servidor indisponivel", JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.servidor = servidor;

		setTitle("Loja DuMa Musica");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtPesquisa = new JTextField();
		txtPesquisa.setBounds(140, 50, 404, 22);
		txtPesquisa.setColumns(10);
		contentPane.add(txtPesquisa);

		cmbCategoria = new JComboBox<String>();
		cmbCategoria.setBounds(12, 51, 118, 20);
		contentPane.add(cmbCategoria);

		try {
			this.servidor.receba(new Comunicado("CMB"));

			Comunicado comunicado = this.servidor.envie();

			if (comunicado.getComando().equals("EST")) {
				String estilo = comunicado.getComplemento1();

				if (estilo != null)
					this.cmbCategoria.addItem(estilo);
			}

//			for (;;) {
//				Comunicado comunicado = servidor.envie();
//
//				if (comunicado == null || comunicado.getComando().equals("FIM"))
//					break;
//				else if (comunicado.getComando().equals("EST")) {
//					String estilo = comunicado.getComplemento1();
//
//					if (estilo != null) {
//						cmbCategoria.addItem(estilo);
////						break;
//					}
//				}
//			}
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null/* sem janela m�e */, "Tente novamente mais tarde!",
					"Erro de conectividade", JOptionPane.ERROR_MESSAGE);
			this.setVisible(false);
		}

		btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String Categoria = (String) cmbCategoria.getSelectedItem();
					String pesquisa = txtPesquisa.getText();

					servidor.receba(new Comunicado("CON", Categoria, pesquisa));

					Comunicado comunicado = servidor.envie();

					if (!comunicado.getComando().equals("ERR")) {
						musicas = new Lista<Musica>();
						musicas.insereItem(new Musica(comunicado.getComplemento1(), comunicado.getComplemento2(),
								comunicado.getComplemento3(), Integer.parseInt(comunicado.getComplemento4()),
								Double.parseDouble(comunicado.getComplemento5())));
						LoadList();

						return;
					}
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela m�e */, ex.getMessage(), "Erro!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnPesquisar.setBounds(556, 49, 102, 25);
		contentPane.add(btnPesquisar);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(12, 29, 56, 16);
		contentPane.add(lblCategoria);

		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setBounds(140, 29, 56, 16);
		contentPane.add(lblPesquisa);

		this.listMusicas = new JList<String>();
		this.listMusicas.setBounds(12, 109, 264, 246);
		this.listMusicas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(this.listMusicas);

		JLabel lblMusicas = new JLabel("Musicas");
		lblMusicas.setBounds(12, 92, 56, 16);
		contentPane.add(lblMusicas);

		JButton btnFinalizarCompra = new JButton("Finalizar Compra");
		btnFinalizarCompra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFinalizarCompra.setBounds(482, 366, 176, 23);
		contentPane.add(btnFinalizarCompra);

		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (listMusicas == null)
						throw new Exception("Sem itens na lista de Musicas, fa�a uma pesquisa!");

					if (listMusicas.getSelectedValue() == null)
						throw new Exception("Selecione uma musica para adicionar ao carrinho!");

					dmCarrinho.addElement(listMusicas.getSelectedValue());
					listCarrinho.setModel(dmCarrinho);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela m�e */, ex.getMessage(), "Erro!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdicionar.setToolTipText("Adicionar ao carrinho");
		btnAdicionar.setBounds(286, 154, 98, 38);
		contentPane.add(btnAdicionar);

		this.listCarrinho = new JList<String>();
		this.listCarrinho.setBounds(394, 109, 264, 246);
		this.listCarrinho.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		contentPane.add(this.listCarrinho);

		JLabel lblCarrinho = new JLabel("Carrinho");
		lblCarrinho.setBounds(394, 93, 70, 14);
		contentPane.add(lblCarrinho);

		Remover = new JButton("Remover");
		Remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (listCarrinho == null)
						throw new Exception("Sem itens na lista de Carrinho!");

					int index = listCarrinho.getSelectedIndex();

					if (index < 0)
						throw new Exception("Selecione uma musica para remover do Carrinho!");

					dmCarrinho.removeElementAt(index);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null/* sem janela m�e */, ex.getMessage(), "Erro!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		Remover.setToolTipText("Remover ao carrinho");
		Remover.setBounds(286, 243, 98, 38);
		contentPane.add(Remover);

		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	private void LoadList() {
		try {
			if (!this.musicas.isVazia()) {
				this.listMusicas.removeAll();
				this.dmMusicas = new DefaultListModel<String>();

				while (!this.musicas.isVazia()) {
					this.dmMusicas.addElement(this.musicas.getItem().toString());
					this.musicas.removeItem();
				}
				this.listMusicas.setModel(this.dmMusicas);
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null/* sem janela m�e */, ex.getMessage(), "Erro!",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}