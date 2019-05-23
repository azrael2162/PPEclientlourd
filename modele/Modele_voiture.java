package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import controleur.ModeleVoiture;
import controleur.voiture;

public class Modele_voiture {
	
	
	//REALISATION DES QUATRES FONCTIONS 
	public static ArrayList<voiture> selectAll() {
		ArrayList<voiture> lesVoitures = new ArrayList<voiture>();
		Bdd uneBdd = new Bdd("localhost:3306","autoEcole","root","root");
		uneBdd.seConnecter();
		String requete = "select * from  voiture;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idv = unRes.getInt("idv");
				String immatriculation = unRes.getString("immatriculation");
				String date_achat = unRes.getString("date_achat");
				String nbkm = unRes.getString("nb_km");
				int idmo = unRes.getInt("idmo");
		
		voiture uneVoiture = new voiture(idv,immatriculation,date_achat,nbkm,idmo);
			lesVoitures.add(uneVoiture);
			
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesVoitures;
	}
	
	public static ArrayList<ModeleVoiture> selectAllmodele_voiture() {
		ArrayList<ModeleVoiture> lesModeleVoitures = new ArrayList<ModeleVoiture>();
		Bdd uneBdd = new Bdd("localhost:3306","autoEcole","root","root");
		uneBdd.seConnecter();
		String requete = "select * from  modele;";
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idmo = unRes.getInt("idmo");
				String nom = unRes.getString("nom");
				int cylindre = unRes.getInt("cylindre");
		
		ModeleVoiture unModeleVoiture = new ModeleVoiture(idmo,nom,cylindre);
			lesModeleVoitures.add(unModeleVoiture);
			
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesModeleVoitures;
	}
	
	public static void insert (voiture uneVoiture) {
		
		String requete = "insert into voiture values (null,'"+uneVoiture.getIdv()+"','"+uneVoiture.getImmatriculation()+"','"+uneVoiture.getDataAchat()+"','"+uneVoiture.getNbkm()+"','"+uneVoiture.getIdmo()+"');";

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
	
	public static void delete (voiture uneVoiture                                                                                                                                                                                                                                                                                                                                                                                                                                          ) {
		String requete = "delete from voiture where";
		String tab[] = new String [5];
		int i = 0;
		
		if (uneVoiture.getIdv() != 0) {
			tab[i++] = " idu ='"+uneVoiture.getIdv();
		}
		if (! uneVoiture.getImmatriculation().equals("")) {
			tab[i++] = " immatriculation ='"+uneVoiture.getImmatriculation()+"'";
		}
		if (! uneVoiture.getDataAchat().equals("")) {
			tab[i++] = " date_achat ='"+uneVoiture.getDataAchat()+"'";
		}
		if (! uneVoiture.getNbkm().equals("")) {
			tab[i++] = " nb_km ='"+uneVoiture.getNbkm()+"'";
		}
		
		if (uneVoiture.getIdmo() != 0) {
			tab[i++] = " idmo ='"+uneVoiture.getIdmo();
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
	
	public static voiture rechercherID(voiture uneVoiture) {
		voiture resultat = null;
		
		String requete = "select * from voiture where "
		+ "idu = " + uneVoiture.getIdv()+";";
		Bdd uneBdd = new Bdd("localhost:3306", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			if (unRes.next()) {
				int idv = unRes.getInt("idv");
				String immatriculation = unRes.getString("immatriculation");
				String date_achat = unRes.getString("date_achat");
				String nbkm = unRes.getString("nb_km");
				int idmo = unRes.getInt("idmo");
				resultat = new voiture(idv, immatriculation, date_achat, nbkm,idmo);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return resultat;		
		}
	public static ArrayList<voiture> rechercher (String mot) {
		ArrayList<voiture> lesVoitures = new ArrayList<voiture>();
		String requete = "select * from voiture where "
		+ "idv like '%"+mot+"%' or "
		+ "immatriculation like '%"+mot+"%' or "
		+ "date_achat like '%"+mot+"%' or "
		+ "nb_km like"+mot+"%' or "
		+ " idmo like '%"+mot+"%' ; ";
		Bdd uneBdd = new Bdd("localhost", "autoEcole", "root", "root");
		uneBdd.seConnecter();
		try{
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unRes = unStat.executeQuery(requete);
			while (unRes.next()) {
				int idv = unRes.getInt("idv");
				String immatriculation = unRes.getString("immatriculation");
				String date_achat = unRes.getString("date_achat");
				String nbkm = unRes.getString("nb_km");
				int idmo = unRes.getInt("idmo");
				voiture uneVoiture = new voiture(idv, immatriculation, date_achat, nbkm,idmo);		
				lesVoitures.add(uneVoiture);
			}
			unStat.close();
			unRes.close();
		}
		catch (SQLException exp) {
		System.out.println("Erreur de la requete" + requete);	
		}
		return lesVoitures;
	}

}

