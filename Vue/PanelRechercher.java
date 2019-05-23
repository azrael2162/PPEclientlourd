package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import controleur.client;
import modele.Modele_client;;

public class PanelRechercher extends JPanel implements ActionListener{

	
	JComboBox cbxIdEtudiant = new JComboBox();
	private JButton btOk = new JButton("Ok");
	private JTextArea txtResultat = new JTextArea();
	public PanelRechercher() {
		this.setBounds(130,50,370,300);
		this.setLayout(new GridLayout(5,2));
		
		this.cbxIdEtudiant.setBounds(100, 20, 200, 20);
		this.add(this.cbxIdEtudiant);
		this.btOk.setBounds(320,50,50,20);
		this.add(this.btOk);
		this.txtResultat.setBounds(50,100,340,250);
		this.add(this.txtResultat);
		
		this.remplirCBX();
		this.txtResultat.setEditable(false);
		
		this.btOk.addActionListener(this);
		
		this.setVisible(false);
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btOk) {
			String chaine = this.cbxIdEtudiant.getSelectedItem().toString();
			String tab[] = chaine.split(" - ");
			int idu = Integer.parseInt(tab[0]);
			int grp = Integer.parseInt(tab[1]);
			int idum = Integer.parseInt(tab[2]);
			client unClient = new client(idu,"", "", "", "","","","","","",idum,grp);
			unClient = Modele_client.rechercherID(unClient);
			if(unClient != null) {
				chaine = "ID Client : " + unClient.getIdu() + "\n" +
					"adresse : " + unClient.getAdresse() + "\n" +
					"Code_zip : " + unClient.getCode_zip() + "\n" +
					"Datenaissance : " + unClient.getDatenaissa() + "\n" +
					"Telephone : " + unClient.getTel() + "\n" +
					"Nom : " + unClient.getNom() + "\n" +
					"Prenom : " + unClient.getPrenom() + "\n" +
					"Mail: " + unClient.getMail() + "\n" +
					"Password : " + unClient.getPasswd() + "\n" +
					"RandToken : " + unClient.getRandToken() + "\n" +
					"Valider : " + unClient.getValider() + "\n" +
					"Idum : " + unClient.getIdum() + "\n" +
					"grp : " + unClient.getGrp() + "\n";
				this.txtResultat.setText(chaine);
			} else {
				this.txtResultat.setText("Aucun User trouvé");
			}
			
		}
	}
	
	public void remplirCBX() {
		ArrayList<client> lesClients = Modele_client.selectAll();
		this.cbxIdEtudiant.removeAllItems();
		for(client unClient : lesClients){
			String chaine = unClient.getIdu() + " - " + unClient.getCode_zip() + " - " + unClient.getDatenaissa()+ "-" + unClient.getTel()+ "-" + unClient.getNom()+ "-" + unClient.getPrenom()+ "-" + unClient.getMail()+ "-"+ unClient.getPasswd()+ "-"+ unClient.getRandToken()+ "-"+ unClient.getValider()+ "-"+ unClient.getIdum()+ "-" + unClient.getGrp();                               
			this.cbxIdEtudiant.addItem(chaine);
		}
	}
	public void actualiser() {
		this.remplirCBX();
	}
}
