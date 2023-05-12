package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.*;
import model.*;
import model.dao.*;
import model.entity.*;

public class TelaLogin {

	JFrame frame = new JFrame();
	JLabel email, senha, imagem, ajuda1, ajuda2;
	JFrame telaLogin, caixa; 
	JTextField caixaEmail, caixaSenha;
	JButton botaoLogin, botaoSair, jbOk, cadastro;
	HoleriteDAO conexaoDAO = new HoleriteDAO();

	public TelaLogin() {
		initialize();
	}
	
	private void initialize() {
		
		conexaoDAO.conectar();
		conexaoDAO.conectado();

		email = criarTexto("Email: ", "Insira seu email", 100, 425, 70, 25);
		senha = criarTexto("Senha: ", "Insira sua senha", 100, 475, 72, 25);
		caixaEmail = criarCaixaDeTexto(175, 425, 300, 25);
		caixaSenha = criarCaixaSenha(175, 475, 300, 25);
		imagem = adicionarImagem("src/imagem/pessoa.png", 150, 60, 300, 300);
		botaoLogin = criarBotao("Login", 100, 550, 175, 35);
		botaoSair = criarBotao("Sair", 300, 550, 175, 35);
		cadastro = criarBotaoCadastro("Clique aqui para se cadastrar!", 150, 600, 300, 20);
		cadastro.addActionListener(e ->{
	        	frame.dispose();
	        	new TelaCadastro();
	        });
	        frame.getContentPane().add(cadastro);

//		botaoLogin.addActionListener(this);
//		botaoSair.addActionListener(this);

		frame.setTitle("• Rabisco Holerite | Login");
		frame.setSize(600, 700);
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.white);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		frame.add(imagem);
		frame.add(email);
		frame.add(senha);
		frame.add(caixaEmail);
		frame.add(caixaSenha);
		frame.add(botaoSair);
		frame.add(botaoLogin);
		frame.add(cadastro);

		ImageIcon iconetela = new ImageIcon("src/imagem/cadeado.png");
		frame.setIconImage(iconetela.getImage());
	}

	public JButton criarBotao(String texto, int esq, int topo, int larg, int alt) {
		JButton jb = new JButton();
		jb.setBounds(esq, topo, larg, alt);
		jb.setText(texto);
		jb.setVisible(true);
		jb.setBackground(Color.black);
		jb.setForeground(Color.white);
		jb.setFont(new Font("Arial", Font.BOLD, 18));
		return jb;
	}
	public JButton criarBotaoCadastro(String texto, int esq, int topo, int larg, int alt) {
		JButton jb = new JButton();
		jb.setBounds(esq, topo, larg, alt);
		jb.setText(texto);
		jb.setVisible(true);
        jb.setOpaque(false);
        jb.setContentAreaFilled(false);
        jb.setBorderPainted(false);
		jb.setBackground(Color.white);
		jb.setForeground(Color.black);
		jb.setFont(new Font("Arial", Font.BOLD, 12));
		return jb;
	}

	/**
	 * Adiciona uma imagem na tela
	 *
	 * @param endImagem
	 * @param esq
	 * @param topo
	 * @param larg
	 * @param alt
	 * @return
	 */
	public JLabel adicionarImagem(String endImagem, int esq, int topo, int larg, int alt) {
		ImageIcon image = new ImageIcon(endImagem);
		JLabel label = new JLabel(); // Cria um label
		label.setIcon(image);
		label.setBounds(esq, topo, larg, alt);
		return label;
	}

	/**
	 * Cria uma caixa de texto
	 *
	 * @param esq
	 * @param topo
	 * @param larg
	 * @param alt
	 * @return
	 */
	public JTextField criarCaixaDeTexto(int esq, int topo, int larg, int alt) {
		JTextField jt = new JTextField();
		jt.setBounds(esq, topo, larg, alt);
		jt.setFont(new Font("Arial", Font.PLAIN, 15));
		jt.setHorizontalAlignment(JTextField.LEFT);
		jt.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(jt);
		jt.setVisible(true);
		return jt;
	}

	public JPasswordField criarCaixaSenha(int esq, int topo, int larg, int alt) {
		JPasswordField jpf = new JPasswordField();
		jpf.setBounds(esq, topo, larg, alt);
		jpf.setFont(new Font("Arial", Font.PLAIN, 15));
		jpf.setBorder(BorderFactory.createLineBorder(Color.black));
		frame.add(jpf);
		jpf.setVisible(true);
		return jpf;
	}

	/**
	 * Exibe o texto na tela
	 *
	 * @param texto
	 * @param textoEscondido
	 * @param esq
	 * @param topo
	 * @param larg
	 * @param alt
	 * @return
	 */
	public JLabel criarTexto(String texto, String textoEscondido, int esq, int topo, int larg, int alt) {
		JLabel jl = new JLabel(texto);
		jl.setBounds(esq, topo, larg, alt); // Define o tamanho do texto na tela
		jl.setOpaque(false);
//		jl.setBackground(new Color(255,255,255));
		jl.setForeground(new Color(0, 0, 0));
		jl.setFont(new Font("Arial", Font.BOLD, 19));
		jl.setToolTipText(textoEscondido);
		jl.setHorizontalAlignment(SwingConstants.LEFT);
		jl.setVerticalAlignment(SwingConstants.TOP);
		frame.add(jl);
		jl.setVisible(true);
		return jl;
	}

	public JLabel criarTextoAjuda(String texto, String textoEscondido, int esq, int topo, int larg, int alt) {
		JLabel jl = new JLabel(texto);
		jl.setBounds(esq, topo, larg, alt); // Define o tamanho do texto na tela
		jl.setOpaque(false);
//		jl.setBackground(new Color(255,255,255));
		jl.setForeground(Color.red);
		jl.setFont(new Font("Arial", Font.BOLD, 15));
		jl.setToolTipText(textoEscondido);
		jl.setHorizontalAlignment(SwingConstants.LEFT);
		jl.setVerticalAlignment(SwingConstants.TOP);
		frame.add(jl);
		jl.setVisible(true);
		return jl;
	}

	public JFrame caixaDeMensagem(String texto) {
		JFrame caixa = new JFrame();
		caixa.setTitle("Caixa de mensagem");
		caixa.setSize(400, 200);
		caixa.setLayout(null);
		caixa.getContentPane().setBackground(Color.white);
		caixa.setResizable(false);
		caixa.setVisible(true);
		caixa.setLocationRelativeTo(null);
		ImageIcon java = new ImageIcon("src/imagem/java.png");
		caixa.setIconImage(java.getImage());
		jbOk = new JButton("Ok");
		jbOk.setBounds(50, 100, 300, 35);
		jbOk.setVisible(true);
		jbOk.setBackground(Color.black);
		jbOk.setForeground(Color.white);
		jbOk.setFont(new Font("Arial", Font.BOLD, 18));
		jbOk.addActionListener(this);
		JLabel jl = new JLabel(texto);
		jl.setBounds(50, 50, 300, 35); // Define o tamanho do texto na tela
		jl.setOpaque(false);
		jl.setVerticalAlignment(JLabel.CENTER);
//		jl.setBackground(new Color(255,255,255));
		jl.setForeground(new Color(0, 0, 0));
		jl.setFont(new Font("Arial", Font.BOLD, 16));
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.TOP);
		caixa.add(jbOk);
		caixa.add(jl);
		return caixa;
	}
}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		 Funcionario funcionario = new Funcionario(caixaEmail.getText(), caixaSenha.getText());
//		 Contexto.contexto.setFuncionario(conexaoDAO.loginFuncionario(funcionario));
//		if (e.getSource() == botaoLogin) {
//			try {
//				if (Contexto.contexto.getFuncionario() != null) {
//					caixa = caixaDeMensagem("Logado com sucesso!");
//				} else {
//					caixa = caixaDeMensagem("Senha ou o email incorretos!");
//					caixaEmail.setText(null);
//					caixaSenha.setText(null);
//				}
//			} catch (NullPointerException erro) {
//				erro.printStackTrace();
//			}
//		}
//
//		if (e.getSource() == botaoSair) {
//			System.exit(0);
//		}
//		if (e.getSource() == jbOk) {
//			try {
//				if (Contexto.contexto.getFuncionario() != null) {
//					frame.dispose();
//					caixa.dispose();
//					if (Contexto.contexto.getFuncionario().getPrivilegio().equals("adm")) {
//						new TelaMenuAdm().initialize();
//					} else {
//						new TelaMenuFuncionario().initialize();
//					}
//				} else {
//					caixa.dispose();
//				}
//			} catch (NullPointerException erro) {
//				erro.printStackTrace();
//			}
//		}
//	}
//	public Funcionario getFuncionarioLogado() {
//	    return Contexto.contexto.getFuncionario();
//	}
//}