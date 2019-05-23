package modele;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Formatter;

import controleur.client;



public class Modele_client {
	
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
	public static ArrayList<client> selectAll() {
		ArrayList<client> lesclients = new ArrayList<client>();
		Bdd uneBdd = new Bdd("localhost:3306","autoEcole","root","root");
		uneBdd.seConnecter();
		String requete = "select * from  client;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			System.out.println(unRes.toString());
			while (unRes.next()) {
				int idu = unRes.getInt("idu");
				String adresse = unRes.getString("adresse");
				String code_zip = unRes.getString("code_zip");
				String datenaissa = unRes.getString("datenaissa");
				String tel = unRes.getString("tel");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String mail = unRes.getString("mail");
				String valider = unRes.getString("valider");
				String RandToken = unRes.getString("RandToken");
				int idum = unRes.getInt("idu_moniteur");
				int grp = unRes.getInt("idgrp");
				

		client unClient = new client(idu,adresse, code_zip, datenaissa, tel,nom,prenom,mail,valider,RandToken,idum,grp);
			lesclients.add(unClient);
		
		}
			
			}
		catch (Exception exp) {
		System.out.println("Erreur de la requete" + requete +"\n"+ exp);	
		}
		return lesclients;
	}
	/*
	public static void insert (client lesclients) {
		String requete = "insert into client values (null,'"+unMoniteur.getIdu()+"','"+unMoniteur.getDate_embauche()+"','"+unMoniteur.getNom()+"','"+unMoniteur.getPrenom()+"','"+unMoniteur+"','"+unUser.getGrp_id()+"');";
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(requete);
		} catch (SQLException exp) {
			System.out.println("Erreur requete : "+ requete);
		}
		uneBdd.seDeconnecter();
	}
	*/
	public static void delete (client unClient) {
		String requete = "delete from client where";
		String tab[] = new String [13];
		int i = 0;
		
		if (unClient.getIdu() != 0) {
			tab[i++] = " idu ="+unClient.getIdu();
		}
		if (! unClient.getAdresse().equals("")) {
			tab[i++] = " adresse ='"+unClient.getAdresse()+"'";
		}
		if (! unClient.getCode_zip().equals("")) {
			tab[i++] = " code_zip ='"+unClient.getCode_zip()+"'";
		}
		if (! unClient.getDatenaissa().equals("")) {
			tab[i++] = " datenaissa ='"+unClient.getDatenaissa()+"'";
		}
		if (! unClient.getTel().equals("")) {
			tab[i++] = " tel ='"+unClient.getTel()+"'";
		}
		if (! unClient.getNom().equals("")) {
			tab[i++] = " nom ='"+unClient.getNom()+"'";
		}
		if (! unClient.getPrenom().equals("")) {
			tab[i++] = " prenom ='"+unClient.getPrenom()+"'";
		}
		if (! unClient.getMail().equals("")) {
			tab[i++] = " mail ='"+unClient.getMail()+"'";
		}
		if (! unClient.getMail().equals("")) {
			tab[i++] = " passwd ='"+unClient.getPasswd()+"'";
		}
		if (! unClient.getRandToken().equals("")) {
			tab[i++] = " RandToken ='"+unClient.getRandToken()+"'";
		}
		if (! unClient.getValider().equals("")) {
			tab[i++] = " valider ='"+unClient.getValider()+"'";
		}
		if (unClient.getGrp() != 0) {
			tab[i++] = " grp ='"+unClient.getGrp();
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
	
	public static client rechercherID(client unClient) {
		client resultat = null;
		
		String requete = "select * from client where "
		+ "idUser = " + unClient.getIdu()+";";
		Bdd uneBdd = new Bdd("localhost:3306", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				int idu = unRes.getInt("idu");
				String adresse = unRes.getString("adresse");
				String code_zip = unRes.getString("code_zip");
				String datenaissa = unRes.getString("datenaissa");
				String tel = unRes.getString("tel");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String mail = unRes.getString("mail");
				String valider = unRes.getString("valider");
				String RandToken = unRes.getString("RandToken");
				int idum = unRes.getInt("idum");
				int grp = unRes.getInt("grp");
				resultat =  new client(idu,adresse, code_zip, datenaissa, tel,nom,prenom,mail,valider,RandToken,idum,grp);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return resultat;		
		}
	public static ArrayList<client> rechercher (String mot) {
		ArrayList<client> lesClients = new ArrayList<client>();
		String requete = "select * from client where "
		+ "idu like '%"+mot+"%' or "
		+ "adresse like '%"+mot+"%' or "
		+ "code_zip like '%"+mot+"%' or "
		+ "datenaissa"+mot+"%' or "
		+ "tel like '%"+mot+"%' or "
		+ "nom like '%"+mot+"%' or "
		+ "prenom like '%"+mot+"%' or "
		+ "mail like '%"+mot+"%' or "
		+ " valider like '%"+mot+"%' or "
		+ " randToken like '%"+mot+"%' or "	
		+ " idum like '%"+mot+"%' or "	
		+ " grp like '%"+mot+"%' ; ";
		Bdd uneBdd = new Bdd("localhost", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idu = unRes.getInt("idu");
				String adresse = unRes.getString("adresse");
				String code_zip = unRes.getString("code_zip");
				String datenaissa = unRes.getString("datenaissa");
				String tel = unRes.getString("tel");
				String nom = unRes.getString("nom");
				String prenom = unRes.getString("prenom");
				String mail = unRes.getString("mail");
				String valider = unRes.getString("valider");
				String RandToken = unRes.getString("RandToken");
				int idum = unRes.getInt("idum");
				int grp = unRes.getInt("grp");			
				client unClient =new client(idu,adresse, code_zip, datenaissa, tel,nom,prenom,mail,valider,RandToken,idum,grp);
				lesClients.add(unClient);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesClients;
	}
	/*
	public static String [] verifConnexion (String mail, String mdp) {
		String tab[] = new String [3];
		tab[0]= mail;
		tab[1]= mdp;
		tab[2]= "";
		
		//cryptage du mdp :
		mdp = Modele.encryptPassword(mdp);
		System.out.println(mdp);
		
		String requete="Select * from utilisateur where login ='"
				+login+"' AND passwordd = '" +mdp+"' ;";
		Bdd uneBdd = new Bdd("localhost:3306", "bdd", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				tab[2] = unRes.getString("grp_id");
			} 
		} catch (SQLException exp) {
			System.out.println("Erreur execution :" + requete);
		}
		uneBdd.seDeconnecter();
		return tab;
	}*/
//
}



















