package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import java.awt.Toolkit;

public class Historico extends Contexto {

	private JFrame telaHistorico;

	public Historico() {
		initialize();
	}

	public void initialize() {
		telaHistorico = new JFrame();
		telaHistorico.setIconImage(Toolkit.getDefaultToolkit().getImage(Historico.class.getResource("/Imagem/rabisco.png")));
		telaHistorico.setResizable(false);
		telaHistorico.setTitle("• Rabisco Holerite | Histórico de Holerite");
		telaHistorico.getContentPane().setBackground(Color.WHITE);
		telaHistorico.setBackground(Color.WHITE);
		telaHistorico.setSize(600, 400);
		telaHistorico.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaHistorico.getContentPane().setLayout(null);
		telaHistorico.setLocationRelativeTo(null);

		JButton jb = new JButton();
		jb.setText("Buscar");
		jb.setBounds(150, 250, 300, 50);
		jb.setContentAreaFilled(false);
		jb.setFocusPainted(false);
		jb.setOpaque(true);
		jb.setBackground(Color.black);
		jb.setForeground(Color.white);
		jb.setFont(new Font("Arial", Font.BOLD, 17));
		telaHistorico.getContentPane().add(jb);

		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setMaximumRowCount(24);
		comboBox.setFont(new Font("Arial", Font.PLAIN, 12));
		comboBox.setBounds(103, 121, 400, 50);
		comboBox.setBackground(Color.black);
		comboBox.setBorder(BorderFactory.createLineBorder(Color.black));
		telaHistorico.getContentPane().add(comboBox);

		JButton jbv = new JButton();
		jbv.setBounds(5, 10, 130, 25);
		jbv.setText("Voltar");
		jbv.setVisible(true);
		jbv.setOpaque(false);
		jbv.setContentAreaFilled(false);
		jbv.setBorderPainted(false);
		jbv.setForeground(Color.black);
		jbv.setFont(new Font("Arial", Font.BOLD, 18));
		ImageIcon voltarIcon = new ImageIcon("src/imagem/voltar.png");
		jbv.setIcon(voltarIcon);
		jbv.addActionListener(e -> {
			if (Contexto.contexto.getFuncionario().getPrivilegio() == true) {
				telaHistorico.dispose();
				new TelaMenuAdm().initialize();
			} else {
				telaHistorico.dispose();
				new TelaMenuFuncionario().initialize();
			}
		});
		telaHistorico.getContentPane().add(jbv);
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					telaHistorico.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
