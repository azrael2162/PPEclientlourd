package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.moniteur;
import modele.Modele_moniteur;

public class PanelAjoutMoniteur extends JPanel implements ActionListener {

		private JButton btEnregistrer = new JButton("Enregistrer");
		private JButton btAnnuler = new JButton("Annuler");
		private JTextField dateambauche = new JTextField("");
		private JTextField nom = new JTextField("");
		private JTextField prenom = new JTextField("");
		private JTextField datenaissa = new JTextField("");
		private JTextField mail = new JTextField("");
		private JTextField passwd = new JTextField("");
		private JTextField id_grp = new JTextField("");
			
		public PanelAjoutMoniteur() {
			
			this.setBounds(130, 50, 270, 200);
			this.setBackground(Color.yellow);
			this.setLayout(new GridLayout(9,1));
			
			
			this.add(new JLabel("Date d'embauche :"));
			this.add(this.dateambauche);
			this.add(new JLabel("Nom :"));
			this.add(this.nom);
			this.add(new JLabel("Prenom :"));
			this.add(this.prenom);
			this.add(new JLabel("Date Naissance :"));
			this.add(this.datenaissa);
			this.add(new JLabel("Mail:"));
			this.add(this.mail);
			this.add(new JLabel("Password :"));
			this.add(this.passwd);
			this.add(new JLabel("idgrp :"));
			this.add(this.id_grp);
			
			this.add(this.btAnnuler);
			this.add(this.btEnregistrer);
			
			this.btAnnuler.addActionListener(this);
			this.btEnregistrer.addActionListener(this);
			
			this.setVisible(false);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "Annuler" :
				this.vider();
				break;
			case "Enregistrer" :
				
				moniteur unMoniteur = new moniteur(dateambauche.getText(),nom.getText(),prenom.getText(),datenaissa.getText(), mail.getText(),passwd.getText(),Integer.parseInt(id_grp.getText()));
				if (unMoniteur.verifSaisie()) {
					//insertion base de donnée
					Modele_moniteur.insert(unMoniteur);
					JOptionPane.showMessageDialog(this, "l'étudiant a été ajout avec succès","Ajout réussi", JOptionPane.OK_OPTION);
				} else {
					JOptionPane.showMessageDialog(this, "Erreur : Veuillez remplir les champs de saisie.","Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}
			
		
			

		private void vider() {
			this.dateambauche.setText("");
			this.nom.setText("");
			this.prenom.setText("");
			this.datenaissa.setText(""); 
			this.mail.setText("");
			this.passwd.setText("");
			this.id_grp.setText("");

		}

}
