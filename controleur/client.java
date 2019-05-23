package controleur;

public class client {
	private String adresse, code_zip,datenaissa,passwd,tel,nom,prenom,mail,valider,randToken;
	private int idu ,grp,idum;
	
	public client() {
		this.idu=0;
		this.adresse="";
		this.code_zip="";
		this.datenaissa="";
		this.tel="";
		this.nom="";
		this.prenom="";
		this.mail="";
		this.valider="";
		this.passwd="";
		this.randToken="";
		this.idum=0;
		this.grp=0;
	}
	
	
	public client(int Null,String adresse,String code_zip,String datenaissa,String tel,String nom, String prenom,String mail,String valider,String randToken,int idum,int grp) {
		this.idu=idu;
		this.adresse=adresse;
		this.code_zip=code_zip;
		this.datenaissa=datenaissa;
		this.tel=tel;
		this.nom=nom;
		this.prenom=prenom;
		this.mail=mail;
		this.valider=valider;
		this.randToken=randToken;
		this.idum=idum;
		this.grp=grp;
	}

	public boolean verifSaisie() {
		if(this.nom.equals("") || this.prenom.equals("") || this.mail.equals("")){
			return false;
		}
		return true;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCode_zip() {
		return code_zip;
	}

	public void setCode_zip(String code_zip) {
		this.code_zip = code_zip;
	}

	public String getDatenaissa() {
		return datenaissa;
	}

	public void setDatenaissa(String datenaissa) {
		this.datenaissa = datenaissa;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getValider() {
		return valider;
	}

	public void setValider(String valider) {
		this.valider = valider;
	}

	public String getRandToken() {
		return randToken;
	}

	public void setRandToken(String randToken) {
		this.randToken = randToken;
	}

	public int getIdu() {
		return idu;
	}

	public void setIdu(int idu) {
		this.idu = idu;
	}

	public int getGrp() {
		return grp;
	}

	public void setGrp(int grp) {
		this.grp = grp;
	}


	public int getIdum() {
		return idum;
	}


	public void setIdum(int idum) {
		this.idum = idum;
	}


	public String getPasswd() {
		return passwd;
	}


	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	
	
	
	
}
