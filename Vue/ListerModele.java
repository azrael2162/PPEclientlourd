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
import modele.Modele_voiture;;

public class ListerModele extends JPanel implements ActionListener{

	private JTable tableModele;
	private Tableau unTableau;

	
	public ListerModele() {
		this.setBounds(130, 50, 600, 500);
		this.setLayout(null);
		this.setSize(getMaximumSize());

		
		//construction de la JTable
		String entetes []= {"idmo", "Nom","cylindre"};
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
		ArrayList<ModeleVoiture> lesModeleVoitures = Modele_voiture.selectAllmodele_voiture();
	
		Object donnees[][] = new Object [lesModeleVoitures.size()][3];
		int i = 0;
		for (ModeleVoiture unModele_voiture : lesModeleVoitures) {
			donnees[i][0] = unModele_voiture.getIdmo()+"";
			donnees[i][1] = unModele_voiture.getNom();
			donnees[i][2] = unModele_voiture.getCylindre();

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
