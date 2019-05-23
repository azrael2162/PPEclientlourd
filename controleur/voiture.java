package controleur;

public class voiture {
	private String immatriculation, nbkm,dataAchat;
	private int idv,idmo;
	
	public voiture(int idv,String immatriculation, String nbkm, String dataAchat , int idmo) {

		this.immatriculation = immatriculation;
		this.nbkm = nbkm;
		this.dataAchat = dataAchat;
		this.idv = idv;
		this.idmo = idmo;
	}

	

	public voiture(String immatriculation, String nbkm, String dataAchat, int idmo) {
		this.immatriculation = immatriculation;
		this.nbkm = nbkm;
		this.dataAchat = dataAchat;
		this.idmo = idmo;
	}


	public String getImmatriculation() {
		return immatriculation;
	}

	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	public String getNbkm() {
		return nbkm;
	}

	public void setNbkm(String nbkm) {
		this.nbkm = nbkm;
	}

	public String getDataAchat() {
		return dataAchat;
	}

	public void setDataAchat(String dataAchat) {
		this.dataAchat = dataAchat;
	}

	public int getIdv() {
		return idv;
	}

	public void setIdv(int idv) {
		this.idv = idv;
	}

	public int getIdmo() {
		return idmo;
	}

	public void setIdmo(int idmo) {
		this.idmo = idmo;
	}
	
	public boolean verifSaisie() {
		if(this.immatriculation.equals("") || this.dataAchat.equals("") || this.nbkm.equals("")){
			return false;
		}
		return true;
	
	}

}
