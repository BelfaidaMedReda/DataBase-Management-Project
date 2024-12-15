import java.sql.*;
import java.util.*;
public class GagnantEnchere{
    static final String CONN_URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    static final String USER = "elaroudr";
    static final String PASSWD = "elaroudr";
    private	 String PRE_STMT = "";
    private Connection conn = null;

    public GagnantEnchere()
    {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            this.conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            this.conn.setAutoCommit(false);
        }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
        System.out.println("Gagnant de quelle enchère ?");
        listeEnchere();
        Scanner userInput = new Scanner(System.in);
        System.out.println("Entrer ID");
        String choix = userInput.nextLine();
        gagnantEnchere(Integer.parseInt(choix));
    }

    private void dumpResultSet(ResultSet rset) throws SQLException {
        boolean limite = false;
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                switch (j) {
                    case 1 -> {
                        System.out.println(" id : " + rset.getString(j));
                        break;
                    }
                    case 2 -> {

                        if (Integer.parseInt(rset.getString(j)) != 0) {
                            System.out.println(" révocable ");
                        } else {
                            System.out.println(" non révocable ");
                        }
                        break;
                    }
                    case 3 -> {
                        if (Integer.parseInt(rset.getString(j)) != 0) {
                            limite = true;
                        }
                        break;
                    }
                    case 4 -> {
                        break;
                    }
                    case 5 -> {
                        if (limite) {
                            System.out.println(" date de fin : " + rset.getString(j));
                        }
                        break;
                    }
                    case 6 -> {
                        System.out.println(" quantité : " + rset.getString(j));
                        break;
                    }
                    case 7 -> {
                        System.out.println(" email vendeur : " + rset.getString(j));
                        break;
                    }
                    case 12 ->
                    {
                        System.out.println(" catégorie : " + rset.getString(j));
                        break;
                    }
                }
                    //System.out.print(rset.getString(j) + "\t");
            }
            System.out.println();
        }
    }

    public void listeEnchere()
    {
        try
        {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VENTE JOIN PRODUIT ON VENTE.IDPRODUIT = PRODUIT.ID_PRODUIT WHERE ( (VENTE.OFFRELIMITÉ <> 0 AND VENTE.DATEFIN < SYSDATE) OR (VENTE.OFFRELIMITÉ = 0 AND (SYSDATE + INTERVAL '10' MINUTE) > (SELECT MAX(DATEOFFRE) FROM OFFRE WHERE OFFRE.IDVENTE = VENTE.ID) ) )");
        ResultSet rset = stmt.executeQuery();
        dumpResultSet(rset);}
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    public void gagnantEnchere(int id)
    {
        try {
            // Étape 1 : Récupérer la quantité totale disponible pour la vente
            String sqlTotal = "SELECT QTÉLOT FROM VENTE WHERE ID = ?";
            PreparedStatement stmt = conn.prepareStatement(sqlTotal);
            stmt.setInt(1, id);
            ResultSet rset = stmt.executeQuery();

            int total = 0;
            if (rset.next()) {
                total = rset.getInt(1); // Utiliser getInt pour les colonnes numériques
            }

            // Fermer les ressources actuelles avant de réutiliser stmt et rset
            rset.close();
            stmt.close();

            // Étape 2 : Récupérer les détails des offres pour cette vente
            String sqlOffres = "SELECT IDOFFRE, PRIXACHAT, QTEACHETEE FROM OFFRE WHERE IDVENTE = ?";
            stmt = conn.prepareStatement(sqlOffres);
            stmt.setInt(1, id);
            rset = stmt.executeQuery();

            // Stocker les offres dans une structure pour trier par prix unitaire
            class Offre {
                int idOffre;
                double prixAchat;
                int quantiteAchetee;
                double prixUnitaire;

                Offre(int idOffre, double prixAchat, int quantiteAchetee) {
                    this.idOffre = idOffre;
                    this.prixAchat = prixAchat;
                    this.quantiteAchetee = quantiteAchetee;
                    this.prixUnitaire = prixAchat / quantiteAchetee;
                }
            }

            ArrayList<Offre> offres = new ArrayList<>();

            while (rset.next()) {
                int idOffre = rset.getInt("IDOFFRE");
                double prixAchat = rset.getInt("PRIXACHAT");
                int quantiteAchetee = rset.getInt("QTEACHETEE");

                offres.add(new Offre(idOffre, prixAchat, quantiteAchetee));
            }

            // Fermer les ressources après lecture
            //rset.close();
            //stmt.close();

            // Étape 3 : Trier les offres par prix unitaire (décroissant)
            offres.sort(Comparator.comparingDouble((Offre o) -> o.prixUnitaire).reversed());

            // Étape 4 : Trouver les gagnants en distribuant les lots
            ArrayList<Integer> gagnants = new ArrayList<>();
            int quantiteRestante = total;

            for (Offre offre : offres) {
                System.out.println(quantiteRestante + " " + offre.quantiteAchetee);
                if (quantiteRestante <= 0)
                {break;}

                if (offre.quantiteAchetee <= quantiteRestante) {
                    // Toute la quantité demandée est satisfaite
                    gagnants.add(offre.idOffre);
                    quantiteRestante -= offre.quantiteAchetee;
                }
            }
            if (gagnants.size() > 0)
            {
                StringBuilder query = new StringBuilder("SELECT EMAIL FROM OFFRE WHERE IDOFFRE IN (");
                for (int i = 0; i < gagnants.size(); i++) {
                    query.append("?");
                    if (i < gagnants.size() - 1)
                    { query.append(", "); }
                }
                query.append(")"); 
                stmt = conn.prepareStatement(query.toString());
                for (int i = 0; i < gagnants.size(); i++)
                { stmt.setInt(i + 1, gagnants.get(i)); }

                rset = stmt.executeQuery();



                ArrayList<String> emails = new ArrayList<>();
                while (rset.next()) { emails.add(rset.getString(1)); }
                
                System.out.println("Email du gagnant: " + emails);
            }
            else
            {
                System.out.println("Aucun gagnant de cette enchère");
            }

        }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    public ArrayList<Integer> listeVenteFinies()
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        try
        {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VENTE JOIN PRODUIT ON VENTE.IDPRODUIT = PRODUIT.ID_PRODUIT WHERE ( (VENTE.OFFRELIMITÉ <> 0 AND VENTE.DATEFIN < SYSDATE) OR (VENTE.OFFRELIMITÉ = 0 AND (SYSDATE + INTERVAL '10' MINUTE) > (SELECT MAX(DATEOFFRE) FROM OFFRE WHERE OFFRE.IDVENTE = VENTE.ID) ) )");
            ResultSet rset = stmt.executeQuery();
            while (rset.next()) {
                res.add(Integer.parseInt(rset.getString(1)));
            }
            for (Integer i : res)
            {
                System.out.println(i);
            }

            }
        catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
        return res;
    }
}
