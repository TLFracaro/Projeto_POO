package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class TelaConsultaHolerite extends Contexto implements Printable{

	 JFrame telaHolerite;

	public void initialize() {
		telaHolerite = new JFrame();
		telaHolerite.getContentPane().setBackground(Color.WHITE);
		telaHolerite.setBackground(Color.WHITE);
		telaHolerite.setIconImage(Toolkit.getDefaultToolkit().getImage(TelaConsultaHolerite.class.getResource("/Imagem/rabisco.png")));
		telaHolerite.setTitle("• Rascunho Holerite | Consulta Holerite");
		telaHolerite.setSize(600,650);
		telaHolerite.setLocationRelativeTo(null);
		telaHolerite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		telaHolerite.getContentPane().setLayout(null);
		
		painelHolerite();
		
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
        jbv.addActionListener(e ->{
    			if(Contexto.contexto.getFuncionario().getPrivilegio().equals("adm")) {
    				telaHolerite.dispose();
    				new TelaMenuAdm().initialize();
    			} else{
    				telaHolerite.dispose();
    				new TelaMenuFuncionario().initialize();
    			}
        });
        telaHolerite.getContentPane().add(jbv);
		
        painelHolerite();
        
		JButton jb = new JButton();
        jb.setText("Imprimir");
        jb.setBounds(60,540,200,50);
        jb.setContentAreaFilled(false);
        jb.setFocusPainted(false);
        jb.setOpaque(true);
        jb.setBackground(Color.black);
        jb.setForeground(Color.white);
        jb.setFont(new Font("Arial", Font.BOLD, 17));
        telaHolerite.getContentPane().add(jb);
        
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrinterJob job = PrinterJob.getPrinterJob();
                Printable printable = new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex > 0) {
                            return NO_SUCH_PAGE;
                        }
                        Graphics2D g2d = (Graphics2D) graphics;
                        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                        painelHolerite().printAll(g2d);
                        return PAGE_EXISTS;
                    }
                };
                job.setPrintable(printable);
                if (job.printDialog()) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        
        JButton jbs = new JButton();
        jbs.setText("Salvar");
        jbs.setBounds(321,540,200,50);
        jbs.setContentAreaFilled(false);
        jbs.setFocusPainted(false);
        jbs.setOpaque(true);
        jbs.setBackground(Color.black);
        jbs.setForeground(Color.white);
        jbs.setFont(new Font("Arial", Font.BOLD, 17));
        telaHolerite.getContentPane().add(jbs);
        jbs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Salvar holerite");
                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    if (!file.getName().endsWith(".pdf")) {
                        file = new File(file.getPath() + ".pdf");
                    }
                    try {
                        file.createNewFile();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    FileOutputStream fos = null;
                    try {
                        fos = new FileOutputStream(file);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    
                    com.itextpdf.text.Document document = new com.itextpdf.text.Document(PageSize.A4, 36, 36, 36, 36);
                    try {
                        PdfWriter writer = PdfWriter.getInstance(document, fos);
                        document.open();
                        
                        PdfContentByte canvas = writer.getDirectContent();
                        
                        JLayeredPane painel = painelHolerite();
                        PdfTemplate template = canvas.createTemplate(painel.getWidth(), painel.getHeight());
                        
                        Graphics2D g2d = template.createGraphics(painel.getWidth(), painel.getHeight());
                        painel.printAll(g2d);
                        g2d.dispose();
                        
                        canvas.addTemplate(template, 0, PageSize.A4.getHeight() - painel.getHeight());
                        
                        document.close();
                        fos.close();
                    } catch (DocumentException | IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					telaHolerite.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TelaConsultaHolerite() {
		initialize();
	}
	
	public JLayeredPane painelHolerite() {
		JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(15, 46, 556, 483);
        telaHolerite.getContentPane().add(layeredPane);
        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 535, 33);
        layeredPane.add(panel);
        panel.setBackground(Color.WHITE);
        panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel.setLayout(null);
        JLabel lblNewLabel = new JLabel("HOLERITE DE PAGAMENTO MENSAL");
        lblNewLabel.setBounds(126, 9, 247, 17);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setBackground(new Color(255, 255, 255));
        panel.add(lblNewLabel);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(10, 41, 314, 97);
        layeredPane.add(panel_1);
        panel_1.setBackground(Color.WHITE);
        panel_1.setLayout(null);
        panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        
        JLabel lblNewLabel_1 = new JLabel("EMPREGADOR");
        lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1.setBounds(10, 10, 85, 14);
        panel_1.add(lblNewLabel_1);
        
        JLabel lblNewLabel_1_1 = new JLabel("Nome: ");
        lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_1.setBounds(10, 28, 34, 13);
        panel_1.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Endereço: ");
        lblNewLabel_1_1_1.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_1_1.setBounds(10, 48, 52, 13);
        panel_1.add(lblNewLabel_1_1_1);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("CNPJ: ");
        lblNewLabel_1_1_2.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_1_2.setBounds(10, 68, 32, 13);
        panel_1.add(lblNewLabel_1_1_2);
        
        JLabel lblNewLabel_1_1_2_1 = new JLabel("XX. XXX. XXX/0001-XX");
        lblNewLabel_1_1_2_1.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_1_2_1.setBounds(46, 68, 121, 13);
        panel_1.add(lblNewLabel_1_1_2_1);
        
        JLabel lblNewLabel_1_1_2_1_1 = new JLabel("XXXXXXXXXXXXXXXXXX");
        lblNewLabel_1_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_1_2_1_1.setBounds(66, 48, 238, 13);
        panel_1.add(lblNewLabel_1_1_2_1_1);
        
        JLabel lblNewLabel_1_1_3 = new JLabel("XXXXXXXXXXXXXX");
        lblNewLabel_1_1_3.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_1_3.setBounds(46, 28, 258, 13);
        panel_1.add(lblNewLabel_1_1_3);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setBounds(321, 41, 224, 97);
        layeredPane.add(panel_1_1);
        panel_1_1.setBackground(Color.WHITE);
        panel_1_1.setLayout(null);
        panel_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        
        JLabel lblNewLabel_1_2 = new JLabel("Referente ao Mês / Ano");
        lblNewLabel_1_2.setFont(new Font("Arial", Font.PLAIN, 14));
        lblNewLabel_1_2.setBounds(37, 25, 150, 17);
        panel_1_1.add(lblNewLabel_1_2);
        
        JLabel lblNewLabel_1_2_1 = new JLabel("janeiro-XX");
        lblNewLabel_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_2_1.setFont(new Font("Arial", Font.BOLD, 16));
        lblNewLabel_1_2_1.setBounds(73, 53, 79, 19);
        panel_1_1.add(lblNewLabel_1_2_1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setBounds(10, 137, 535, 45);
        layeredPane.add(panel_2);
        panel_2.setBackground(Color.WHITE);
        panel_2.setLayout(null);
        panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        
        JLabel lblNewLabel_1_3 = new JLabel("CÓDIGO");
        lblNewLabel_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3.setBounds(10, 9, 48, 14);
        panel_2.add(lblNewLabel_1_3);
        
        JLabel lblNewLabel_1_3_1 = new JLabel("NOME DO FUNCIONÁRIO");
        lblNewLabel_1_3_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_1.setBounds(100, 9, 142, 14);
        panel_2.add(lblNewLabel_1_3_1);
        
        JLabel lblNewLabel_1_3_1_2 = new JLabel("CBO");
        lblNewLabel_1_3_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_1_2.setBounds(303, 9, 26, 14);
        panel_2.add(lblNewLabel_1_3_1_2);
        
        JLabel lblNewLabel_1_3_1_2_1 = new JLabel("FUNÇÃO");
        lblNewLabel_1_3_1_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_1_2_1.setBounds(377, 9, 50, 14);
        panel_2.add(lblNewLabel_1_3_1_2_1);
        
        JLabel lblNewLabel_1_3_4 = new JLabel("XXXX");
        lblNewLabel_1_3_4.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_3_4.setBounds(10, 24, 28, 13);
        panel_2.add(lblNewLabel_1_3_4);
        
        JLabel lblNewLabel_1_3_1_1 = new JLabel("XXXXXXXXXXXXXXXX");
        lblNewLabel_1_3_1_1.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_3_1_1.setBounds(100, 24, 112, 13);
        panel_2.add(lblNewLabel_1_3_1_1);
        
        JLabel lblNewLabel_1_3_1_2_2 = new JLabel("XXXXXXX");
        lblNewLabel_1_3_1_2_2.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_3_1_2_2.setBounds(292, 24, 49, 13);
        panel_2.add(lblNewLabel_1_3_1_2_2);
        
        JLabel lblNewLabel_1_3_1_2_1_1 = new JLabel("XXXXXXXXXXXXXX");
        lblNewLabel_1_3_1_2_1_1.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_3_1_2_1_1.setBounds(377, 24, 98, 13);
        panel_2.add(lblNewLabel_1_3_1_2_1_1);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setBounds(10, 181, 50, 22);
        layeredPane.add(panel_2_1);
        panel_2_1.setLayout(null);
        panel_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2 = new JLabel("Cód.");
        lblNewLabel_1_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2.setBounds(12, 5, 26, 14);
        panel_2_1.add(lblNewLabel_1_3_2);
        
        JPanel panel_2_1_1 = new JPanel();
        panel_2_1_1.setBounds(58, 181, 201, 22);
        layeredPane.add(panel_2_1_1);
        panel_2_1_1.setLayout(null);
        panel_2_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_1 = new JLabel("Descrição");
        lblNewLabel_1_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_1.setBounds(72, 4, 56, 14);
        panel_2_1_1.add(lblNewLabel_1_3_2_1);
        
        JPanel panel_2_1_1_1 = new JPanel();
        panel_2_1_1_1.setBounds(255, 181, 87, 22);
        layeredPane.add(panel_2_1_1_1);
        panel_2_1_1_1.setLayout(null);
        panel_2_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_1_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_1_1 = new JLabel("Referência");
        lblNewLabel_1_3_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_1_1.setBounds(13, 5, 60, 14);
        panel_2_1_1_1.add(lblNewLabel_1_3_2_1_1);
        
        JPanel panel_2_1_1_1_1 = new JPanel();
        panel_2_1_1_1_1.setBounds(340, 181, 104, 22);
        layeredPane.add(panel_2_1_1_1_1);
        panel_2_1_1_1_1.setLayout(null);
        panel_2_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_1_1_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_1_1_1 = new JLabel("Proventos");
        lblNewLabel_1_3_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_1_1_1.setBounds(24, 5, 55, 14);
        panel_2_1_1_1_1.add(lblNewLabel_1_3_2_1_1_1);
        
        JPanel panel_2_1_1_1_2 = new JPanel();
        panel_2_1_1_1_2.setBounds(442, 181, 103, 22);
        layeredPane.add(panel_2_1_1_1_2);
        panel_2_1_1_1_2.setLayout(null);
        panel_2_1_1_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_1_1_2.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_1_1_2 = new JLabel("Descontos");
        lblNewLabel_1_3_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_1_1_2.setBounds(21, 5, 60, 14);
        panel_2_1_1_1_2.add(lblNewLabel_1_3_2_1_1_2);
        
        JPanel panel_2_2 = new JPanel();
        panel_2_2.setBounds(10, 357, 332, 71);
        layeredPane.add(panel_2_2);
        panel_2_2.setLayout(null);
        panel_2_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_2.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_3 = new JLabel("MENSAGENS");
        lblNewLabel_1_3_3.setFont(new Font("Arial", Font.PLAIN, 10));
        lblNewLabel_1_3_3.setBounds(10, 10, 66, 13);
        panel_2_2.add(lblNewLabel_1_3_3);
        
        JPanel panel_2_2_1 = new JPanel();
        panel_2_2_1.setBounds(10, 426, 535, 48);
        layeredPane.add(panel_2_2_1);
        panel_2_2_1.setLayout(null);
        panel_2_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_2_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_3_1 = new JLabel("Salário Base");
        lblNewLabel_1_3_3_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1.setBounds(20, 9, 61, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1);
        
        JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Base Cál. INSS");
        lblNewLabel_1_3_1_1_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_1_1_1.setBounds(101, 9, 72, 13);
        panel_2_2_1.add(lblNewLabel_1_3_1_1_1);
        
        JLabel lblNewLabel_1_3_1_2_2_1 = new JLabel("Base Cál. FGTS");
        lblNewLabel_1_3_1_2_2_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_1_2_2_1.setBounds(193, 9, 76, 13);
        panel_2_2_1.add(lblNewLabel_1_3_1_2_2_1);
        
        JLabel lblNewLabel_1_3_1_2_2_1_1 = new JLabel("FGTS do Mês");
        lblNewLabel_1_3_1_2_2_1_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_1_2_2_1_1.setBounds(289, 9, 65, 13);
        panel_2_2_1.add(lblNewLabel_1_3_1_2_2_1_1);
        
        JLabel lblNewLabel_1_3_1_2_2_1_2 = new JLabel("Base Cál. IRFF");
        lblNewLabel_1_3_1_2_2_1_2.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_1_2_2_1_2.setBounds(374, 9, 70, 13);
        panel_2_2_1.add(lblNewLabel_1_3_1_2_2_1_2);
        
        JLabel lblNewLabel_1_3_1_2_2_1_2_1 = new JLabel("Faixa IRRF");
        lblNewLabel_1_3_1_2_2_1_2_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_1_2_2_1_2_1.setBounds(464, 9, 51, 13);
        panel_2_2_1.add(lblNewLabel_1_3_1_2_2_1_2_1);
        
        JLabel lblNewLabel_1_3_3_1_1 = new JLabel("XXX,XX");
        lblNewLabel_1_3_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_1_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1_1.setBounds(31, 28, 38, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1_1);
        
        JLabel lblNewLabel_1_3_3_1_1_1 = new JLabel("XXX,XX");
        lblNewLabel_1_3_3_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_1_1_1.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1_1_1.setBounds(118, 28, 38, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1_1_1);
        
        JLabel lblNewLabel_1_3_3_1_1_2 = new JLabel("XXX,XX");
        lblNewLabel_1_3_3_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_1_1_2.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1_1_2.setBounds(212, 28, 38, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1_1_2);
        
        JLabel lblNewLabel_1_3_3_1_1_3 = new JLabel("XXX,XX");
        lblNewLabel_1_3_3_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_1_1_3.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1_1_3.setBounds(302, 28, 38, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1_1_3);
        
        JLabel lblNewLabel_1_3_3_1_1_4 = new JLabel("XXX,XX");
        lblNewLabel_1_3_3_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_1_1_4.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1_1_4.setBounds(390, 28, 38, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1_1_4);
        
        JLabel lblNewLabel_1_3_3_1_1_5 = new JLabel("XXX,XX");
        lblNewLabel_1_3_3_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_1_1_5.setFont(new Font("Arial", Font.PLAIN, 11));
        lblNewLabel_1_3_3_1_1_5.setBounds(470, 28, 38, 13);
        panel_2_2_1.add(lblNewLabel_1_3_3_1_1_5);
        
        JPanel panel_2_1_2 = new JPanel();
        panel_2_1_2.setBounds(10, 201, 50, 159);
        layeredPane.add(panel_2_1_2);
        panel_2_1_2.setLayout(null);
        panel_2_1_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_2.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_2 = new JLabel("000");
        lblNewLabel_1_3_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2.setBounds(14, 5, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2);
        
        JLabel lblNewLabel_1_3_2_2_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1.setBounds(14, 20, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1.setBounds(14, 35, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1.setBounds(14, 50, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1.setBounds(14, 65, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1.setBounds(14, 80, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1.setBounds(14, 95, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1.setBounds(14, 110, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1.setBounds(14, 125, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1.setBounds(14, 140, 21, 14);
        panel_2_1_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1);
        
        JPanel panel_2_1_2_1 = new JPanel();
        panel_2_1_2_1.setBounds(58, 201, 201, 159);
        layeredPane.add(panel_2_1_2_1);
        panel_2_1_2_1.setLayout(null);
        panel_2_1_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_2_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_2_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_2.setBounds(7, 5, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_2);
        
        JLabel lblNewLabel_1_3_2_2_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_2.setBounds(7, 20, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_2.setBounds(7, 35, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_2.setBounds(7, 50, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_2.setBounds(7, 65, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_2.setBounds(7, 80, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_2.setBounds(7, 95, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_2.setBounds(7, 110, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_2.setBounds(7, 125, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_2);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_1 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_1.setBounds(7, 140, 184, 14);
        panel_2_1_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_1);
        
        JPanel panel_2_1_2_2 = new JPanel();
        panel_2_1_2_2.setBounds(255, 201, 87, 159);
        layeredPane.add(panel_2_1_2_2);
        panel_2_1_2_2.setLayout(null);
        panel_2_1_2_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_2_2.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_2_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_3.setBounds(10, 5, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_3);
        
        JLabel lblNewLabel_1_3_2_2_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_3.setBounds(10, 20, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_3.setBounds(10, 35, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_3.setBounds(10, 50, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_3.setBounds(10, 65, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_3.setBounds(10, 80, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_3.setBounds(10, 95, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_3.setBounds(10, 110, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_3.setBounds(10, 125, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_3);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_2 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_2.setBounds(10, 140, 67, 14);
        panel_2_1_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_2);
        
        JPanel panel_2_1_2_2_1 = new JPanel();
        panel_2_1_2_2_1.setBounds(340, 200, 104, 159);
        layeredPane.add(panel_2_1_2_2_1);
        panel_2_1_2_2_1.setLayout(null);
        panel_2_1_2_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_2_2_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_2_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_4.setBounds(10, 6, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_4);
        
        JLabel lblNewLabel_1_3_2_2_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_4.setBounds(10, 21, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_4.setBounds(10, 36, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_4.setBounds(10, 51, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_4.setBounds(10, 66, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_4.setBounds(10, 81, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_4.setBounds(10, 96, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_4.setBounds(10, 111, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_4.setBounds(10, 126, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_4);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_3 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_3.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_3.setBounds(10, 141, 84, 14);
        panel_2_1_2_2_1.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_3);
        
        JPanel panel_2_1_2_2_2 = new JPanel();
        panel_2_1_2_2_2.setBounds(442, 200, 103, 159);
        layeredPane.add(panel_2_1_2_2_2);
        panel_2_1_2_2_2.setLayout(null);
        panel_2_1_2_2_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_2_2_2.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_2_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_5.setBounds(10, 6, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_5);
        
        JLabel lblNewLabel_1_3_2_2_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_5.setBounds(10, 21, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_5.setBounds(10, 36, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_5.setBounds(10, 51, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_5.setBounds(10, 66, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_5.setBounds(10, 81, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_5.setBounds(10, 96, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_5.setBounds(10, 111, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_5 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_5.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_5.setBounds(10, 126, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_5);
        
        JLabel lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_4 = new JLabel("000");
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_4.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_4.setBounds(10, 141, 83, 14);
        panel_2_1_2_2_2.add(lblNewLabel_1_3_2_2_1_1_1_1_1_1_1_1_1_4);
        
        JPanel panel_2_2_2 = new JPanel();
        panel_2_2_2.setBounds(340, 357, 104, 45);
        layeredPane.add(panel_2_2_2);
        panel_2_2_2.setLayout(null);
        panel_2_2_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_2_2.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_3_2_2 = new JLabel("R$");
        lblNewLabel_1_3_3_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_2_2.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_3_2_2.setBounds(6, 23, 89, 16);
        panel_2_2_2.add(lblNewLabel_1_3_3_2_2);
        
        JLabel lblNewLabel_1_3_3_2 = new JLabel("Total de Vencimentos");
        lblNewLabel_1_3_3_2.setBounds(6, 7, 90, 12);
        panel_2_2_2.add(lblNewLabel_1_3_3_2);
        lblNewLabel_1_3_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_2.setFont(new Font("Arial", Font.PLAIN, 9));
        
        JPanel panel_2_2_2_1 = new JPanel();
        panel_2_2_2_1.setBounds(442, 357, 103, 45);
        layeredPane.add(panel_2_2_2_1);
        panel_2_2_2_1.setLayout(null);
        panel_2_2_2_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_2_2_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_3_2_1 = new JLabel("Total de Descontos");
        lblNewLabel_1_3_3_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_2_1.setFont(new Font("Arial", Font.PLAIN, 9));
        lblNewLabel_1_3_3_2_1.setBounds(11, 9, 81, 12);
        panel_2_2_2_1.add(lblNewLabel_1_3_3_2_1);
        
        JLabel lblNewLabel_1_3_3_2_2_1 = new JLabel("R$");
        lblNewLabel_1_3_3_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_3_2_2_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_3_2_2_1.setBounds(9, 23, 88, 16);
        panel_2_2_2_1.add(lblNewLabel_1_3_3_2_2_1);
        
        JPanel panel_2_1_1_1_1_1 = new JPanel();
        panel_2_1_1_1_1_1.setBounds(340, 399, 104, 30);
        layeredPane.add(panel_2_1_1_1_1_1);
        panel_2_1_1_1_1_1.setLayout(null);
        panel_2_1_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_1_1_1_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_1_1_1_1 = new JLabel("Valor Líquido ->");
        lblNewLabel_1_3_2_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNewLabel_1_3_2_1_1_1_1.setBounds(5, 5, 99, 20);
        panel_2_1_1_1_1_1.add(lblNewLabel_1_3_2_1_1_1_1);
        
        JPanel panel_2_1_1_1_1_1_1 = new JPanel();
        panel_2_1_1_1_1_1_1.setBounds(442, 399, 103, 30);
        layeredPane.add(panel_2_1_1_1_1_1_1);
        panel_2_1_1_1_1_1_1.setLayout(null);
        panel_2_1_1_1_1_1_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
        panel_2_1_1_1_1_1_1.setBackground(Color.WHITE);
        
        JLabel lblNewLabel_1_3_2_1_1_1_1_1 = new JLabel("R$");
        lblNewLabel_1_3_2_1_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_3_2_1_1_1_1_1.setFont(new Font("Arial", Font.PLAIN, 15));
        lblNewLabel_1_3_2_1_1_1_1_1.setBounds(5, 5, 89, 20);
        panel_2_1_1_1_1_1_1.add(lblNewLabel_1_3_2_1_1_1_1_1);
        return layeredPane; 
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		// TODO Auto-generated method stub
		return 0;
	}
}
