import java.sql.*;

/* L'appel au constructeur SimpleQuery éxecute la requête (string) mise en argument */

public class SimpleQuery {

    static final String CONN_URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    static final String USER = "elaroudr";
    static final String PASSWD = "elaroudr";

    private	 String PRE_STMT = "";

    public SimpleQuery(String PRE_STMT) {
        try {
	    // Enregistrement du driver Oracle
	    System.out.print("Loading Oracle driver... "); 
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            System.out.println("loaded");

	    // Etablissement de la connection
	    System.out.print("Connecting to the database... ");
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
            System.out.println("connected");
            conn.setAutoCommit(false); 

	    // Creation de la requete
	    	  this.PRE_STMT = PRE_STMT;
            PreparedStatement stmt = conn.prepareStatement(PRE_STMT);
  
	    // Execution de la requete
            ResultSet rset = stmt.executeQuery();
       
	    // Affichage du resultat
            System.out.println("Results:");
         //   dumpResultSet(rset);
            System.out.println("");

	    // Fermeture 
	    rset.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
    }

    private void dumpResultSet(ResultSet rset) throws SQLException {
        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();
        while (rset.next()) {
            for (int j = 1; j <= i; j++) {
                System.out.print(rset.getString(j) + "\t");
	    }
	    System.out.println();
        }
    }
}
