package Vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controleur.Main;

	public class VueVoiture extends JFrame implements ActionListener{

			private JPanel panelMenu = new JPanel();
			private JButton btTab[] = new JButton[5];
			private String tab []= {"AjoutVoiture","Lister_voitures","Lister_Modele","Supprimer","Quitter"};		
			private static AjoutVoiture AjoutVoiture = new AjoutVoiture();
			private static Lister_voitures Lister_voitures = new  Lister_voitures();
			private static PanelSupprimer unPanelSupprimer= new PanelSupprimer();
			private static ListerModele Lister_Modele = new ListerModele();

			
			public VueVoiture() {
				this.setTitle("Application  Commandes");
				this.setBounds(100,100,600,450);
				this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				this.setUndecorated(true);
				this.setLayout(null);
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.setResizable(false);
				//this.getContentPane().setBackground(Color.yellow);
				
				//construction du panel Menu
				
				this.panelMenu.setBounds(10, 10, 120, 100);
				this.panelMenu.setLayout(new GridLayout(8,1));
				
				this.panelMenu.add(new JLabel());
				for (int i = 0; i < tab.length ;i++) {
					this.btTab[i] = new JButton (this.tab[i]);
				 
					this.panelMenu.add(this.btTab[i]);
					 
				}
				this.panelMenu.add(new JLabel());
				
				this.add(this.panelMenu);
				
				for (int i = 0; i<this.btTab.length;i++) {
					this.btTab[i].addActionListener(this);
				}
				
				//ajout des panneaux

				this.add(AjoutVoiture);
				this.add(Lister_Modele);
				this.add(unPanelSupprimer);
				this.add(Lister_voitures);
				
				this.setVisible(false);
			}

		

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case "Quitter" : 	this.dispose();
									VueGenerale.rendreVisibleUser(false);
									break;
			case "AjoutVoiture" : 	AjoutVoiture.setVisible(true);
									Lister_voitures.setVisible(false);
									Lister_Modele.setVisible(false);
									unPanelSupprimer.setVisible(false);
									Lister_voitures.setVisible(false);
									break;
			

		   case "Lister_voitures":  AjoutVoiture.setVisible(false);
									Lister_voitures.setVisible(true);
									Lister_Modele.actualiser();
									Lister_Modele.setVisible(false);
									unPanelSupprimer.setVisible(false);
	

									break;
				case "Supprimer": 	AjoutVoiture.setVisible(false);
									Lister_voitures.setVisible(false);
									unPanelSupprimer.actualiser();
									Lister_Modele.setVisible(false);
									unPanelSupprimer.setVisible(true);

									break;
			case "Lister_Modele":   AjoutVoiture.setVisible(false);
									Lister_voitures.setVisible(false);
									unPanelSupprimer.actualiser();
									Lister_Modele.setVisible(true);
									unPanelSupprimer.setVisible(false);
									Lister_voitures.setVisible(false );

									break;
			}
		
	}
	}



