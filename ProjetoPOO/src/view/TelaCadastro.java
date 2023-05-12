package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class TelaCadastro {

	private JFrame frmRabiscoHolerite;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textField_16;
	private JTextField textField_17;


	public TelaCadastro() {
		initialize();
	}

	private void initialize() {
		frmRabiscoHolerite = new JFrame();
		frmRabiscoHolerite.getContentPane().setBackground(Color.WHITE);
		frmRabiscoHolerite.setTitle("• Rabisco Holerite | Cadastro");
		frmRabiscoHolerite.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCadastro.class.getResource("/imagem/cadeado.png")));
		frmRabiscoHolerite.setBackground(Color.WHITE);
		frmRabiscoHolerite.setVisible(true);
		frmRabiscoHolerite.setBounds(100, 100, 900, 550);
		frmRabiscoHolerite.setLocationRelativeTo(null);
		frmRabiscoHolerite.getContentPane().setLayout(null);
		
		JButton jbv = new JButton();
		jbv.setBounds(10, 11, 130, 25);
		jbv.setText("Voltar");
		jbv.setVisible(true);
		jbv.setOpaque(false);
        jbv.setContentAreaFilled(false);
        jbv.setBorderPainted(false);
        jbv.setForeground(Color.black);
        jbv.setFont(new Font("Arial", Font.BOLD, 18));
        ImageIcon voltarIcon = new ImageIcon("src/imagem/voltar.png");
        jbv.setIcon(voltarIcon);
        jbv.addActionListener(e ->{
        	frmRabiscoHolerite.dispose();
        	new TelaLogin();
        });
        frmRabiscoHolerite.getContentPane().add(jbv);
		
		JButton jb = new JButton("Cadastrar");
		jb.setBounds(300,434,300,50);
		jb.setVisible(true);
		jb.setBackground(Color.BLACK);
		jb.setForeground(new Color(255, 255, 255));
		jb.setFont(new Font("Arial", Font.BOLD, 18));
		frmRabiscoHolerite.getContentPane().add(jb);
		
		JLabel lblNewLabel = new JLabel("• Dados para cadastro:");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel.setBounds(30, 50, 228, 30);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome completo:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(58, 102, 101, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("CPF:");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(58, 134, 32, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("RG:");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_2.setBounds(58, 166, 25, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("CEP:");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_3.setBounds(499, 101, 32, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Logradouro:");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4.setBounds(499, 134, 78, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Bairro:");
		lblNewLabel_1_4_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4_1.setBounds(499, 167, 42, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_4_1_1 = new JLabel("Cidade:");
		lblNewLabel_1_4_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4_1_1.setBounds(499, 200, 49, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4_1_1);
		
		JLabel lblNewLabel_1_4_1_1_1 = new JLabel("UF:");
		lblNewLabel_1_4_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4_1_1_1.setBounds(499, 233, 22, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4_1_1_1);
		
		JLabel lblNewLabel_1_4_1_1_1_1 = new JLabel("Número:");
		lblNewLabel_1_4_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4_1_1_1_1.setBounds(499, 266, 53, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4_1_1_1_1);
		
		JLabel lblNewLabel_1_4_1_1_1_1_1 = new JLabel("País:");
		lblNewLabel_1_4_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4_1_1_1_1_1.setBounds(499, 299, 31, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4_1_1_1_1_1);
		
		JLabel lblEndereoDe = new JLabel("• Endereço de Cobrança:");
		lblEndereoDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblEndereoDe.setBounds(469, 50, 251, 30);
		frmRabiscoHolerite.getContentPane().add(lblEndereoDe);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Data de Nasc.:");
		lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(58, 198, 131, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Email:");
		lblNewLabel_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(58, 230, 38, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Senha:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(58, 262, 45, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Função:");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1.setBounds(58, 294, 52, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_1 = new JLabel("Telefone 1:");
		lblNewLabel_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1_1.setBounds(58, 326, 72, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1_2 = new JLabel("Telefone 2:");
		lblNewLabel_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1_2.setBounds(58, 358, 72, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_1_1_1_2_1 = new JLabel("Telefone 3:");
		lblNewLabel_1_1_1_1_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1_2_1.setBounds(58, 390, 72, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_1_1_1_1_1_2_1);
		
		textField = new JTextField();
		textField.setBounds(165, 101, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(165, 133, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(165, 165, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(165, 197, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(165, 229, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(165, 261, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(165, 293, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_6);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(165, 325, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_7);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(165, 389, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_8);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(165, 357, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_9);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(606, 100, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_10);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(606, 133, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(606, 166, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_12);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(606, 199, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_13);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(606, 232, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_14);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(606, 265, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(606, 298, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_16);
		
		JLabel lblNewLabel_2 = new JLabel("*");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setToolTipText("");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(45, 104, 13, 14);
		lblNewLabel_2.setOpaque(false);
		lblNewLabel_2.setBackground(null);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("*");
		lblNewLabel_2_1.setToolTipText("");
		lblNewLabel_2_1.setOpaque(false);
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setForeground(Color.RED);
		lblNewLabel_2_1.setBackground((Color) null);
		lblNewLabel_2_1.setBounds(45, 136, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("*");
		lblNewLabel_2_2.setToolTipText("");
		lblNewLabel_2_2.setOpaque(false);
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setForeground(Color.RED);
		lblNewLabel_2_2.setBackground((Color) null);
		lblNewLabel_2_2.setBounds(45, 168, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("*");
		lblNewLabel_2_3.setToolTipText("");
		lblNewLabel_2_3.setOpaque(false);
		lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_3.setForeground(Color.RED);
		lblNewLabel_2_3.setBackground((Color) null);
		lblNewLabel_2_3.setBounds(45, 200, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("*");
		lblNewLabel_2_4.setToolTipText("");
		lblNewLabel_2_4.setOpaque(false);
		lblNewLabel_2_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_4.setForeground(Color.RED);
		lblNewLabel_2_4.setBackground((Color) null);
		lblNewLabel_2_4.setBounds(45, 232, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_4);
		
		JLabel lblNewLabel_2_5 = new JLabel("*");
		lblNewLabel_2_5.setToolTipText("");
		lblNewLabel_2_5.setOpaque(false);
		lblNewLabel_2_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_5.setForeground(Color.RED);
		lblNewLabel_2_5.setBackground((Color) null);
		lblNewLabel_2_5.setBounds(45, 264, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_5);
		
		JLabel lblNewLabel_2_6 = new JLabel("*");
		lblNewLabel_2_6.setToolTipText("");
		lblNewLabel_2_6.setOpaque(false);
		lblNewLabel_2_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_6.setForeground(Color.RED);
		lblNewLabel_2_6.setBackground((Color) null);
		lblNewLabel_2_6.setBounds(45, 296, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_6);
		
		JLabel lblNewLabel_2_7 = new JLabel("*");
		lblNewLabel_2_7.setToolTipText("");
		lblNewLabel_2_7.setOpaque(false);
		lblNewLabel_2_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_7.setForeground(Color.RED);
		lblNewLabel_2_7.setBackground((Color) null);
		lblNewLabel_2_7.setBounds(45, 328, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_7);
		
		JLabel lblNewLabel_2_8 = new JLabel("*");
		lblNewLabel_2_8.setToolTipText("");
		lblNewLabel_2_8.setOpaque(false);
		lblNewLabel_2_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_8.setForeground(Color.RED);
		lblNewLabel_2_8.setBackground((Color) null);
		lblNewLabel_2_8.setBounds(486, 103, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_8);
		
		JLabel lblNewLabel_2_9 = new JLabel("*");
		lblNewLabel_2_9.setToolTipText("");
		lblNewLabel_2_9.setOpaque(false);
		lblNewLabel_2_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_9.setForeground(Color.RED);
		lblNewLabel_2_9.setBackground((Color) null);
		lblNewLabel_2_9.setBounds(486, 268, 13, 14);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_2_9);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(606, 327, 240, 20);
		frmRabiscoHolerite.getContentPane().add(textField_17);
		
		JLabel lblNewLabel_1_4_1_1_1_1_1_1 = new JLabel("Complemento: ");
		lblNewLabel_1_4_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1_4_1_1_1_1_1_1.setBounds(499, 328, 95, 17);
		frmRabiscoHolerite.getContentPane().add(lblNewLabel_1_4_1_1_1_1_1_1);
		frmRabiscoHolerite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
