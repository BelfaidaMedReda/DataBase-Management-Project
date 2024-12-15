import java.sql.*;
import java.util.Scanner; 

/* Il s'agit du programme principal lancé pour chacune des 3 fonctionnalités demandées dans l'énoncé */
public class Demonstrateur {

	static final String CONN_URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
     static final String USER = "elaroudr";
     static final String PASSWD = "elaroudr";
	private Scanner userInput = new Scanner(System.in);
	private String choix = "";
 	public Demonstrateur() {
 		PageIntro();
 	}
 		
 	private void PageIntro() {
 			System.out.println("----------------------------------------------------\n\nDémonstrateur de la base de donnée Baie-Electronique \n\n----------------------------------------------------\n");
 			LoopMain();
 		}
 		
 	private void LoopMain() {
 		
		System.out.println("1 : Créer salle de vente");
		System.out.println("2 : Faire une enchère/offre");
		System.out.println("3 : Connaître le gagnant d'une enchère");
		System.out.println("4 : Informations sur une table");
		System.out.println("5 : Quitter le programme");
		
		String choix = userInput.nextLine();  
		
		switch(choix) {
			case "1":
				CreerSalle();
				break;
			case "2":
				FaireEnchere();
				break;
			case "3":
				Encheres();
				break;
			case "4":
				InfoTables();
				break;
			case "5":
				Quitter();
				break;	
			default:
				System.out.println("Mauvaise entrée, recommencer :");
				LoopMain();
				break;
		}
		
 	}
 	
 	private void InfoTables() {
 		System.out.println("Choisir parmi les tables une à afficher :");
 		System.out.println("1 : SALLE\n 2 : UTILISATEUR\n 3 : VENTE\n 4 : PRODUIT\n 5 : CATEGORIE\n 6 : CARACTERISTIQUE\n 7 : OFFRE");
 		choix = userInput.nextLine();
 		new AfficheInfos(Integer.parseInt(choix));
 		System.out.println("------------------------------------");
 		LoopMain();
 	}
 	
 	private void Quitter() {
 		System.out.println("Fermeture du programme");
 	}
 	
 	private void Encheres() {
        new GagnantEnchere();
        LoopMain();
 	}
 	
 	private void CreerSalle() {
        System.out.println("Vous allez devoir entrer les informations concernant la création d'un salle de vente");
        new CreeSalle();
        LoopMain();
 	}
 	
 	private void FaireEnchere() {
        System.out.println("Vous allez devoir entrer les informations concernant l'offre");
 	    new FaireUneOffre();
        LoopMain();
 	}
 	
 	public static void main(String args[]) {
     	new Demonstrateur();

	}
}


