package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controleur.ModeleVoiture;
import controleur.client;
import controleur.moniteur;
import controleur.voiture;
import modele.Modele_client;
import modele.Modele_moniteur;
import modele.Modele_voiture;

public class AjoutVoiture extends JPanel implements ActionListener  {
	

		
		private JButton btEnregistrer = new JButton("Enregistrer");
		private JButton btAnnuler = new JButton("Annuler");
		private JTextField immatriculation = new JTextField("");
		private JTextField date_achat = new JTextField("");
		private JTextField nb_km = new JTextField("");
		private JTextField idmo = new JTextField("");
		JComboBox cbxIdEtudiant = new JComboBox();

		public void remplirCBX() {
			ArrayList<ModeleVoiture> lesModeles = Modele_voiture.selectAllmodele_voiture();
			this.cbxIdEtudiant.addItem("exemple");
			for(ModeleVoiture unModele : lesModeles){
				int chaine = unModele.getIdmo();                               
				this.cbxIdEtudiant.addItem(chaine);
			}
		}
		public void actualiser() {
			this.remplirCBX();
		}
	
			
		public AjoutVoiture() {
			
			this.setBounds(130, 50, 270, 200);
			this.setBackground(Color.yellow);
			this.setLayout(new GridLayout(9,1));
			
			this.actualiser();
			
			this.add(cbxIdEtudiant);
			this.add(new JLabel("immatriculation :"));
			this.add(this.immatriculation);
			this.add(new JLabel("date_achat :"));
			this.add(this.date_achat);
			this.add(new JLabel("nb_km :"));
			this.add(this.nb_km);
			this.add(new JLabel("idmo :"));
			this.add(this.idmo);
			
			
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
				
				voiture uneVoiture = new voiture(immatriculation.getText(),date_achat.getText(),nb_km.getText(),Integer.parseInt(idmo.getText()));
				if (uneVoiture.verifSaisie()) {
					//insertion base de donnée
					Modele_voiture.insert(uneVoiture);
					JOptionPane.showMessageDialog(this, "la voiture a été ajout avec succès","Ajout réussi", JOptionPane.OK_OPTION);
				} else {
					JOptionPane.showMessageDialog(this, "Erreur : Veuillez remplir les champs de saisie.","Erreur", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}
		}
			
		
		private void vider() {
			this.immatriculation.setText("");
			this.date_achat.setText("");
			this.nb_km.setText("");
			this.idmo.setText(""); 

		}
	
	}


