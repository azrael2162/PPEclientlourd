package controleur;

public class moniteur {
	private int idu,idgrp;
	private String date_embauche,nom,prenom,datenaissa,mail,passwd;
	
	public moniteur() {
		this.idu=0;
		this.date_embauche="";
		this.nom="";
		this.prenom="";
		this.datenaissa="";
		this.mail="";
		this.passwd="";
		this.idgrp=0;
	}
	
	public moniteur(int id,String date_embauche,String nom,String prenom,String datenaissa,String mail,String passwd,int idgrp) {
		this.idu=id;
		this.date_embauche=date_embauche;
		this.nom=nom;
		this.prenom=prenom;
		this.datenaissa=datenaissa;
		this.mail=mail;
		this.passwd=passwd;
		this.idgrp=idgrp;
	}
	public moniteur(String date_embauche,String nom,String prenom,String datenaissa,String mail,String passwd,int idgrp) {
		this.date_embauche=date_embauche;
		this.nom=nom;
		this.prenom=prenom;
		this.datenaissa=datenaissa;
		this.mail=mail;
		this.passwd=passwd;
		this.idgrp=idgrp;
	}

	public boolean verifSaisie() {
		if(this.nom.equals("") || this.prenom.equals("") || this.mail.equals("")){
			return false;
		}
		return true;
	}

	public int getIdu() {
		return idu;
	}

	public void setIdu(int idu) {
		this.idu = idu;
	}

	public int getIdgrp() {
		return idgrp;
	}

	public void setIdgrp(int idgrp) {
		this.idgrp = idgrp;
	}

	public String getDate_embauche() {
		return date_embauche;
	}

	public void setDate_embauche(String date_embauche) {
		this.date_embauche = date_embauche;
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

	public String getDatenaissa() {
		return datenaissa;
	}

	public void setDatenaissa(String datenaissa) {
		this.datenaissa = datenaissa;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	

}
