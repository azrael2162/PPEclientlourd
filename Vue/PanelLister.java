package Vue;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import controleur.moniteur;
import controleur.Tableau;
import modele.Modele_moniteur;

public class PanelLister extends JPanel implements ActionListener{

	private JTable tableUser;
	private Tableau unTableau;

	
	public PanelLister() {
		this.setBounds(130, 50, 600, 500);
		this.setLayout(null);
		this.setSize(getMaximumSize());

		
		//construction de la JTable
		String entetes []= {"iduser", "Nom","Prénom","Date de naissance","Date embauche","Email", "password", "grp_id"};
		Object [][]donnees = this.getDonnees();
		unTableau = new Tableau(donnees, entetes);
		this.tableUser = new JTable(unTableau);
		//insersion de la Jtable dans JScroll
		JScrollPane unScroll = new JScrollPane(tableUser);
		unScroll.setBounds(20,20,900,450);
		this.add(unScroll);
		
		
		this.setVisible(false);
	}
	public Object [][] getDonnees() {
		ArrayList<moniteur> lesMoniteurs = Modele_moniteur.selectAll();
	
		Object donnees[][] = new Object [lesMoniteurs.size()][8];
		int i = 0;
		for (moniteur unMoniteur : lesMoniteurs) {
			donnees[i][0] = unMoniteur.getIdu()+"";
			donnees[i][1] = unMoniteur.getNom();
			donnees[i][2] = unMoniteur.getPrenom();
			donnees[i][3] = unMoniteur.getDatenaissa();
			donnees[i][4] = unMoniteur.getDate_embauche();
			donnees[i][5] = unMoniteur.getMail();
			donnees[i][6] = unMoniteur.getPasswd();
			donnees[i][7] = unMoniteur.getIdgrp();

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
