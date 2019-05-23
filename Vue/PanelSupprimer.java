package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.client;
import controleur.Tableau;
import modele.Modele_client;

public class PanelSupprimer extends JPanel implements ActionListener{

	private JTable tableUser;	
	private JPanel unPanel = new JPanel();
	private Tableau unTableau;
	
	public PanelSupprimer() {
		this.setBounds(130, 50, 370, 300);
		this.setLayout(null);
		
		//construction de la JTable
		String entetes []= {"iduser", "Nom","Prénom","email", "login","password","grp_id"};
		Object donnees [][] = this.getDonnees(); //matrice
		
		this.unTableau = new Tableau(donnees, entetes);
		
		this.tableUser = new JTable(this.unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableUser);
		unScroll.setBounds(20,20,330,200);
		this.add(unScroll);
		
		//ajouter le click sur la table 
		this.tableUser.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent m) {
				int ligne = tableUser.getSelectedRow();
				int idu = Integer.parseInt(tableUser.getValueAt(ligne, 0).toString());
				int retour = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer ?","Confirmation", JOptionPane.YES_NO_OPTION);
				if(retour==0) {
					client unClient = new client(idu,"", "", "", "","","","","","",0,retour);
					Modele_client.delete(unClient);
					JOptionPane.showMessageDialog(null, "Suppression réussie !");
					unTableau.delete(ligne);
				}
				
			}
		});
		
		
		this.setVisible(false);
	}
	private Object [][] getDonnees() {
		ArrayList<client> lesClients = Modele_client.selectAll();
		Object donnees[][] = new Object [lesClients.size()][11];
		int i =0;
		for (client unClient : lesClients) {
				donnees[i][0] = unClient.getIdu()+"";
				donnees[i][1] = unClient.getAdresse();
				donnees[i][2] = unClient.getCode_zip();
				donnees[i][3] = unClient.getDatenaissa();
				donnees[i][4] = unClient.getTel();
				donnees[i][5] = unClient.getPrenom();
				donnees[i][6] = unClient.getMail();
				donnees[i][7] = unClient.getRandToken();
				donnees[i][8] = unClient.getValider();
				donnees[i][9] = unClient.getIdum();
				donnees[i][10] =unClient.getGrp();
			i++;
		}
		return donnees;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	public void actualiser() {
		unTableau.setDonnees(this.getDonnees());
	}
}
