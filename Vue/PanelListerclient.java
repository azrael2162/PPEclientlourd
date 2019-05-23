package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import controleur.client;
import controleur.Tableau;
import modele.Modele_client;

public class PanelListerclient extends JPanel implements ActionListener{

	private JTable tablecommande;	
	private Tableau unTableau;
	
	public PanelListerclient() {
		this.setBounds(150, 50, 500, 300);
		this.setLayout(null);

		//construction de la JTable
		String entetes []= {"idu", "adresse","code_zip","datenaissa","tel", "nom","prebom","mail","valider","randToken","idum","grp"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tablecommande = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tablecommande);
		unScroll.setBounds(20,20,1080,800);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	private Object [][] getDonnees() {
		ArrayList<client> lesclients = Modele_client.selectAll();
		System.out.println(lesclients.size());

		Object donnees[][] = new Object [lesclients.size()][11];
		int i =0;
		for (client unClient : lesclients) {
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
			donnees[i][10]= unClient.getGrp();

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
