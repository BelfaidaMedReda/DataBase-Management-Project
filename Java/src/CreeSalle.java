import java.sql.*;
import java.util.Scanner;

/*Classe permettant de créer une salle, qui contient en attribut les attributs de l'entité salle*/
public class CreeSalle {

    String categorie;
    int type_enchere_1;
    int type_enchere_2;
    int type_enchere_3;


    static final String CONN_URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    static final String USER = "elaroudr";
    static final String PASSWD = "elaroudr";
    
    public void dumpResult(ResultSet r) throws SQLException {
 
    }

    public CreeSalle(){
        try {
            // Enregistrement du driver Oracle
            System.out.println("Loading Oracle thin driver...");
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded.");
            
            // Etablissement de la connexion
            System.out.println("Connecting to the database...");
            Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            System.out.println("connected.");
    
            // Demarrage de la transaction (implicite dans notre cas)
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);

            // Affichage des catégories disponibles
            PreparedStatement pstmt = conn.prepareStatement
            ("SELECT nom from Categorie");

            System.out.println("Voici le nom des catégories disponibles : ");
            ResultSet rset = pstmt.executeQuery();
            while (rset.next()) {
                System.out.println(rset.getString("nom"));
                }
            rset.close();

            //Demande de la catégorie et du type de vente
            //Vérification existence salle
            pstmt = conn.prepareStatement
                ("SELECT * FROM SALLE WHERE Categorie = ? AND EstMontante = ? AND revocabilite = ? AND OFFRE_LIMITEE = ?");
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("De quelle catégorie est le produit que vous souhaitez vendre ? ");
            this.categorie = myObj.nextLine();

            myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Type d'enchère ? Entrez 0 ou 1 : 0 : le nombre d'offre est limité, 1: le nombre d'offre est illimité ");
            this.type_enchere_1 = myObj.nextInt();

            myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Type d'enchère ? Entrez 0 ou 1 : 0 : ascendante, 1: descendante ");
            this.type_enchere_2 = myObj.nextInt();
            
            myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Type d'enchère ? Entrez 0 ou 1 : 0 : la vente est révoquable, 1: la vente est irrévoquable ");
            this.type_enchere_3 = myObj.nextInt();

            pstmt.setString(1, categorie);
            pstmt.setInt(2, type_enchere_1);
            pstmt.setInt(3, type_enchere_2);
            pstmt.setInt(4, type_enchere_3);
            rset = pstmt.executeQuery();

            //Création de la salle si nécéssaire
            if(!rset.next()){
                pstmt = conn.prepareStatement
                    ("INSERT INTO Salle VALUES ('"+categorie+"',"+type_enchere_1+", " + type_enchere_2 + " , "+type_enchere_3+")");
                rset = pstmt.executeQuery();
                rset.close();
                System.out.println("La salle a été créée !");
            }
            //La salle existe déjà
            else{
                System.out.println("Cette salle existe déjà !");
                rset.close();
            }
            
            //Affichage des ventes devant être dans cette salle
            pstmt = conn.prepareStatement
            ("SELECT id FROM Vente JOIN Produit ON (Vente.idproduit = Produit.id_produit) WHERE Produit.nom_categorie ='" + categorie +"' AND REVOCABILITÉ = "+ type_enchere_1 +" AND estMontante = "+ type_enchere_2+" AND OFFRELIMITÉ = "+ type_enchere_3);
            rset = pstmt.executeQuery();
            if(!rset.next()){
                System.out.println("Il n'y a pas d'article en vente dans cette salle");
            }
            else{
                System.out.println("Voici les identifiants des ventes dans cette salle : ");
            while (rset.next()) {
                System.out.println(rset.getInt(1));
                }
            }
            rset.close();

            // Terminaison de la transaction
            conn.commit();
            
            // Fermetures
            pstmt.close();
            conn.close();
        }
        catch (SQLException e) {
            System.err.println("failed !");
            e.printStackTrace();
        }
    }
}
