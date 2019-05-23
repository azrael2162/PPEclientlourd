package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Main;
import modele.Modele_moniteur;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton("Se Connecter");
	private JTextField txtLogin = new JTextField();
	private JPasswordField pwMdp = new JPasswordField();
	
	private JPanel unPanel = new JPanel();
	
	
	private static VueGenerale uneVueGenerale = new VueGenerale();
	
	public static void rendreVisible (boolean action)
	{
		uneVueGenerale.setVisible(action);
	}
	
	public VueConnexion () {
		this.setTitle("Connexion a l'application Myfilelec");
		this.setLayout(null);
		//this.getContentPane().setBackground(Color.cyan);
		this.setBounds(200, 200, 400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		
		this.unPanel.setBounds(50, 140, 250, 100);
		//this.unPanel.setBackground(Color.yellow);
		this.unPanel.setLayout(new GridLayout(3,2));
		
		this.unPanel.add(new JLabel("Login :"));
		this.unPanel.add(this.txtLogin);
		
		this.unPanel.add(new JLabel("Password :"));
		this.unPanel.add(this.pwMdp);
		
		this.unPanel.add(this.btAnnuler);
		this.unPanel.add(this.btConnexion);
				
		this.unPanel.setVisible(true);
		
		this.add(this.unPanel);
		
		ImageIcon logof = new ImageIcon("img/user.png");
		this.setIconImage(logof.getImage());
		
		ImageIcon logo = new ImageIcon("img/user.png");
		
		JLabel entete = new JLabel("",logo,JLabel.CENTER);
		entete.setBounds(30,25,100,100);
		this.add(entete);
		
		//rendre les deux boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btConnexion.addActionListener(this);
		this.txtLogin.addKeyListener(this);
		this.pwMdp.addKeyListener(this);
		
		this.setVisible(true);
		
		
	}
	
	private void Traitement() {
		String login = this.txtLogin.getText();
		String mdp = new String(this.pwMdp.getPassword());
		if (login.equals("") || mdp.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir les identifiants","Erreur",JOptionPane.ERROR_MESSAGE);
		}
		else {
			String tab[] = Modele_moniteur.verifConnexion(login, mdp);
			if(tab[2].equals("")) {
				JOptionPane.showMessageDialog(this, "Erreur identification","Erreur",JOptionPane.ERROR_MESSAGE);
				this.pwMdp.setText("");
			} else {
				JOptionPane.showMessageDialog(this, "Bienvenue " +login+ " !" ,"Succès",JOptionPane.INFORMATION_MESSAGE);
			
				//on rend la fenetre de connexion invisible et on lance la fenetre générale.
				Main.rendreVisible(false);
				uneVueGenerale.setVisible(true);
				this.txtLogin.setText("");
				this.pwMdp.setText("");
				
			
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==this.btAnnuler) {
			System.exit(1);
		} else if (e.getSource()==this.btConnexion) {
			Traitement();
		}
	}
	
	

				
	
	private void exit(int i) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			Traitement();
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
