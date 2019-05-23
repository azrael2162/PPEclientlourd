package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import controleur.ModeleVoiture;
import controleur.Tableau;
import controleur.voiture;
import modele.Modele_voiture;;

public class Lister_voitures extends JPanel implements ActionListener{

	private JTable tableModele;
	private Tableau unTableau;

	
	public Lister_voitures() {
		this.setBounds(130, 50, 600, 500);
		this.setLayout(null);
		this.setSize(getMaximumSize());

		
		//construction de la JTable
		String entetes []= {"idv", "immatriculation","date_achat","nb_km","idmo"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tableModele = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableModele);
		unScroll.setBounds(20,20,900,450);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	public Object [][] getDonnees() {
		ArrayList<voiture> lesVoitures = Modele_voiture.selectAll();
	
		Object donnees[][] = new Object [lesVoitures.size()][5];
		int i = 0;
		for (voiture  uneVoiture : lesVoitures) {
			donnees[i][0] = uneVoiture.getIdv()+"";
			donnees[i][1] = uneVoiture.getImmatriculation();
			donnees[i][2] = uneVoiture.getDataAchat();
			donnees[i][3] = uneVoiture.getNbkm();
			donnees[i][4] = uneVoiture.getIdmo();
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
