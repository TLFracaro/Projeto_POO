package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
public class TelaMenuFuncionario extends Contexto implements ActionListener {

	JFrame frame = new JFrame();
	JButton consultHolerite, histoHolerite, botaoSair, botaoSuporte;
	JLabel bemVindo, dataHora, labelHora;
	
	public void initialize() {
		frame.setTitle(" • Rabisco Holerite | Menu Funcionario");
		frame.setSize(600, 400); 
		frame.setLayout(null);
		frame.getContentPane().setBackground(Color.white);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

		bemVindo = criarTexto("Bem vindo(a) " + Contexto.contexto.getFuncionario().getNome() + "!", 35, 25, 200, 35);
		botaoSair = criarBotaoSair(525, 10);
		consultHolerite = criarBotao("Consultar meu holerite", 100, 115, 400, 50);
		histoHolerite = criarBotao("Historico de meus holerites", 100, 215, 400, 50);
		labelHora = iniciarRelogio(35, 315, 250, 35);

		botaoSair.addActionListener(this);
		consultHolerite.addActionListener(this);
		histoHolerite.addActionListener(this);

		frame.add(botaoSair);
		frame.add(consultHolerite);
		frame.add(histoHolerite);
		frame.add(bemVindo);
		frame.add(labelHora);

		ImageIcon rabisco = new ImageIcon("src/imagem/rabisco.png");
		frame.setIconImage(rabisco.getImage());
	}
	
	public JButton criarBotaoSair(int esq, int topo) {
		JButton jb = new JButton();
		jb.setLocation(esq, topo);
		jb.setSize(50, 50);
		jb.setVisible(true);
		jb.setOpaque(false);
		jb.setContentAreaFilled(false);
		jb.setBorderPainted(false);
		ImageIcon iconeSair = new ImageIcon("src/imagem/sair.png");
		jb.setIcon(iconeSair);
		return jb;
	}

	public JButton criarBotao(String texto, int esq, int topo, int larg, int alt) {
		JButton jb = new JButton(texto);
		jb.setBounds(esq, topo, larg, alt);
		jb.setContentAreaFilled(false);
		jb.setFocusPainted(false);
		jb.setOpaque(true);
		jb.setBackground(Color.black);
		jb.setForeground(Color.white);
		jb.setFont(new Font("Arial", Font.BOLD, 17));
		return jb;
	}
	
	public JLabel iniciarRelogio(int esq, int topo, int larg, int alt) {
	    labelHora = new JLabel();
	    labelHora.setFont(new Font("Arial", Font.BOLD, 15));
	    labelHora.setForeground(Color.BLACK);
	    labelHora.setBounds(esq, topo, larg, alt);
	    
	    Date horaAtual = new Date();
	    labelHora.setText(horaAtual.toString());

	    Timer timer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            Date horaAtual = new Date();
	            labelHora.setText(horaAtual.toString());
	        }
	    });
	    timer.start();
	    return labelHora;
	}
	
	public JLabel criarTexto(String texto, int esq, int topo, int larg, int alt) {
		JLabel jl = new JLabel(texto);
		jl.setOpaque(false);
		jl.setBounds(esq, topo, larg, alt);
		jl.setForeground(Color.black);
		jl.setFont(new Font("Arial", Font.BOLD, 19));
		jl.setVisible(true);
		return jl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Date horaAtual = new Date();
		labelHora.setText(horaAtual.toString());
		if (e.getSource() == botaoSair) {
			frame.dispose();
			new TelaLogin().initialize();
		}
		if (e.getSource() == botaoSuporte) {
			frame.dispose();
			new TelaChamados();
		}
		if (e.getSource() == consultHolerite) {
			frame.dispose();
			new TelaConsultaHolerite().initialize();
		}
		if (e.getSource() == histoHolerite) {
			frame.dispose();
			new Historico().initialize();
		}
	}

}
