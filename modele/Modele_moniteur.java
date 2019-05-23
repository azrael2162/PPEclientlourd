package modele;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;

import controleur.moniteur;



public class Modele_moniteur {
	
	private static String encryptPassword(String password)
	{
	    String sha1 = "";
	    try
	    {
	        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
	        crypt.reset();
	        crypt.update(password.getBytes("UTF-8"));
	        sha1 = byteToHex(crypt.digest());
	    }
	    catch(NoSuchAlgorithmException e)
	    {
	        e.printStackTrace();
	    }
	    catch(UnsupportedEncodingException e)
	    {
	        e.printStackTrace();
	    }
	    return sha1;
	}

	private static String byteToHex(final byte[] hash)
	{
	    Formatter formatter = new Formatter();
	    for (byte b : hash)
	    {
	        formatter.format("%02x", b);
	    }
	    String result = formatter.toString();
	    formatter.close();
	    return result;
	}
	
	
	//REALISATION DES QUATRES FONCTIONS 
	public static ArrayList<moniteur> selectAll() {
		ArrayList<moniteur> lesmoniteurs = new ArrayList<moniteur>();
		Bdd uneBdd = new Bdd("localhost:3306","autoEcole","root","root");
		uneBdd.seConnecter();
		String requete = "select * from  moniteur;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idu = unRes.getInt("idu");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String date_embauche = unRes.getString("date_embauche");
				String datenaissa = unRes.getString("datenaissa");
				String mail = unRes.getString("mail");
				String passwd = unRes.getString("passwd");
				int idgrp = unRes.getInt("idgrp");
		

		moniteur unMoniteur = new moniteur(idu, nom, prenom,date_embauche,datenaissa,mail,passwd,idgrp);
			lesmoniteurs.add(unMoniteur);
			
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesmoniteurs;
	}
	
	public static void insert (moniteur unMoniteur) {
		String mdp = "";
		mdp = Modele_moniteur.encryptPassword(unMoniteur.getPasswd());
		String requete = "insert into Moniteur values (null,'"+unMoniteur.getDate_embauche()+"','"+unMoniteur.getNom()+"','"+unMoniteur.getPrenom()+"','"+unMoniteur.getDatenaissa()+"','"+unMoniteur.getMail()+"','"+mdp+"','"+unMoniteur.getIdgrp()+"');";

		Bdd uneBdd = new Bdd("localhost:3306", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (Exception exp) {
			System.out.println("Erreur de la requete" + requete +"\n"+ exp);
		}
		uneBdd.seDeconnecter();
	}
	
	public static void delete (moniteur unMoniteur) {
		String requete = "delete from Moniteur where";
		String tab[] = new String [7];
		int i = 0;
		
		if (unMoniteur.getIdu() != 0) {
			tab[i++] = " idu ='"+unMoniteur.getIdu();
		}
		if (! unMoniteur.getDate_embauche().equals("")) {
			tab[i++] = " date_embauche ='"+unMoniteur.getDate_embauche()+"'";
		}
		if (! unMoniteur.getNom().equals("")) {
			tab[i++] = " nom ='"+unMoniteur.getNom()+"'";
		}
		if (! unMoniteur.getPrenom().equals("")) {
			tab[i++] = " prenom ='"+unMoniteur.getPrenom()+"'";
		}
		if (! unMoniteur.getDatenaissa().equals("")) {
			tab[i++] = " datenaissa ='"+unMoniteur.getDatenaissa()+"'";
		}
		if (! unMoniteur.getMail().equals("")) {
			tab[i++] = " mail ='"+unMoniteur.getMail()+"'";
		}
		if (unMoniteur.getIdgrp() != 0) {
			tab[i++] = " idgrp ='"+unMoniteur.getIdgrp();
		}
		
		for (int j=0; j < i ; j++) {
			if (j == 0) {
				requete += tab[j];
			} else {
				requete += " and " + tab[j];
			}
		}
		Bdd uneBdd = new Bdd("localhost:3006", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete :"+ requete);
		}
		uneBdd.seDeconnecter();
		}
	
	public static moniteur rechercherID(moniteur unMoniteur) {
		moniteur resultat = null;
		
		String requete = "select * from moniteur where "
		+ "idu = " + unMoniteur.getIdu()+";";
		Bdd uneBdd = new Bdd("localhost:3306", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				int idu = unRes.getInt("idu");
				String date_embauche = unRes.getString("date_embauche");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String datenaissa = unRes.getString("datenaissa");
				String mail = unRes.getString("mail");
				String passwd = unRes.getString("passwd");
				int idgrp = unRes.getInt("idgrp");
				resultat = new moniteur(idu, date_embauche, nom, prenom,datenaissa, mail, passwd, idgrp);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return resultat;		
		}
	public static ArrayList<moniteur> rechercher (String mot) {
		ArrayList<moniteur> lesUsers = new ArrayList<moniteur>();
		String requete = "select * from moniteur where "
		+ "idu like '%"+mot+"%' or "
		+ "date_embauche like '%"+mot+"%' or "
		+ "nom like '%"+mot+"%' or "
		+ "prenom"+mot+"%' or "
		+ "datenaissa like '%"+mot+"%' or "
		+ "mail like '%"+mot+"%' or "
		+ "prenom like '%"+mot+"%' or "
		+ "passwd like '%"+mot+"%' or "
		+ " idgrp like '%"+mot+"%' or "
		+ " idgrp like '%"+mot+"%' ; ";
		Bdd uneBdd = new Bdd("localhost", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idu = unRes.getInt("idu");
				String date_embauche = unRes.getString("date_embauche");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String datenaissa = unRes.getString("datenaissa");
				String mail = unRes.getString("mail");
				String passwd = unRes.getString("passwd");
				int idgrp = unRes.getInt("idgrp");		
				moniteur unUser = new moniteur(idu, date_embauche, nom, prenom,datenaissa, mail, passwd, idgrp);
				lesUsers.add(unUser);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesUsers;
	}
	
	public static String [] verifConnexion (String mail, String mdp) {
		String tab[] = new String [3];
		tab[0]= mail;
		tab[1]= mdp;
		tab[2]= "";
		
		//cryptage du mdp :
	 String email,passwd; 
	 	email= "vincent.pinelli@wanadoo.fr";
	 	passwd = "slipknot";
	 	
		mdp = Modele_moniteur.encryptPassword(passwd);
		
		String requete="Select * from moniteur where mail ='"
				+email+"' AND passwd = '" +mdp+"' ;";
		Bdd uneBdd = new Bdd("localhost:3306","autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				tab[2] = unRes.getString("idu");
			} 
		} catch (SQLException exp) {
			System.out.println("Erreur execution :" + requete);
			exp.printStackTrace();
		}
		uneBdd.seDeconnecter();
		return tab;
	}
//
}



















