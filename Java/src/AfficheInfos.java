import java.sql.*;


/* Classe qui permet d'afficher différentes tables de la bdd :
 * 1 : Les salles
 * 2 : Les utilisateurs
 * 3 : Les ventes
 * 4 : Les produits
 * 5 : Les catégories
 * 6 : Les caractéristiques
 * 7 : Les offres
 */
public class AfficheInfos {
    private int numero;
    private String type;

    public AfficheInfos(int numero) {
        this.numero = numero;
        
        switch(numero){
            case 1:
                type = "SALLE";
                break;
            case 2:
                type = "UTILISATEUR";
                break;
            case 3:
                type = "VENTE";
                break;
            case 4:
                type = "PRODUIT";
                break;
            case 5:
                type = "CATEGORIE";
                break;
            case 6:
                type = "CARACTERISTIQUE";
                break;
            case 7:
                type = "OFFRE";
                break;
            default :
           	 type = "SALLE";
           	 break;
            }
            SimpleQuery requete = new SimpleQuery("SELECT * FROM " + type);
    }
}
