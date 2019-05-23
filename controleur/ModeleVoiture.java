package controleur;

public class ModeleVoiture {
	
	private int idmo,cylindre;
	private String nom;
	
	
	public ModeleVoiture(int idmo, String nom,int cylindre) {
		this.idmo = idmo;
		this.cylindre = cylindre;
		this.nom = nom;
	}


	public int getIdmo() {
		return idmo;
	}


	public void setIdmo(int idmo) {
		this.idmo = idmo;
	}


	public int getCylindre() {
		return cylindre;
	}


	public void setCylindre(int cylindre) {
		this.cylindre = cylindre;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}
