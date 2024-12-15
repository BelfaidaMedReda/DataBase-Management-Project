import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

class Offre {
    public String utilisateur;
    public int prix;
    public int qte;
    public int id_vente;
    public int id_offre;
    public Date date;
    
    public Offre(int id_vente, String utilisateur, int prix, int qte){
	this.id_vente = id_vente;
	this.utilisateur = utilisateur;
	this.prix = prix;
	this.qte = qte;
    }
    public Offre(int id_vente, String utilisateur, int prix, int qte, Date date){
	this.id_vente = id_vente;
	this.utilisateur = utilisateur;
	this.prix = prix;
	this.qte = qte;
	this.date = date;
    }

    public Offre(int id_vente, String utilisateur, int prix, int qte, Date date, int id_offre){
	this.id_vente = id_vente;
	this.utilisateur = utilisateur;
	this.prix = prix;
	this.qte = qte;
	this.date = date;
    this.id_offre = id_offre;
    }
    @Override
    public String toString(){
	return  this.id_vente + " "  + this.qte + " "+ this.utilisateur +" "+ this.prix + " " + this.date;

    }
    
}

class Vente{
    public boolean revoc;
    public boolean limite;
    public boolean mont;
    public Date fin;
    public int qte;

    public Vente(boolean revoc, boolean limite, boolean mont, Date date, int qte){
	this.revoc = revoc;
	this.limite = limite;
	this.mont = mont;
	this.fin = date;
	this.qte = qte;
    }

    @Override
    public String toString(){
	return " revoc = " + this.revoc + " limité = " + this.limite + " montante = " + this.mont + " date fin " + this.fin + " qte dispo " + this.qte;
    }
}

class FaireUneOffre{

    public Offre offre;
    public Vente vente;
    static final String CONN_URL = "jdbc:oracle:thin:@oracle1.ensimag.fr:1521:oracle1";
    static final String USER = "elaroudr";     // A remplacer pour votre compte, sinon genere une exception
    static final String PASSWD = "elaroudr";

    public FaireUneOffre(){
	    this.offre = new Offre(0, "user", 0, 0);
	    majIdvente();
	    majUser();
	    majPrice();
	    majQuantity();
	    this.vente = this.getVente();
	    //System.out.println(offre);

	    if (sellExists() && userExists() && stillActive() && validQuantity(this.offre.qte) && validPrice(this.offre.prix)) {
		    proposeOffre();
	    }
	    else {
		    System.out.println("Offre impossible");
	    }
   }

    public FaireUneOffre(int id_vente, String utilisateur, int prix, int qte){
	    this.offre = new Offre(id_vente, utilisateur, prix, qte);
	    majIdvente();
	    majUser();
	    majPrice();
	    majQuantity();
	    this.vente = this.getVente();
	
	    if (sellExists() && stillActive() && validQuantity(this.offre.qte) && validPrice(this.offre.prix)) {
		    proposeOffre();
	    }
	    else {
		    System.out.println("Offre impossible");
	    }
   }

    public void proposeOffre(){
       try {
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	     Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	     conn.setAutoCommit(false);
	     conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
	     PreparedStatement pstmt = conn.prepareStatement
         ("INSERT INTO Offre(DATEOFFRE,IDVENTE,QTEACHETEE,EMAIL,PRIXACHAT,IDOFFRE) VALUES (SYSDATE, ?, ?, ?, ?, ?)");
        
        int new_id_offre = this.nextIdOffre();
        System.out.println("ID OFFRE NOUVELLE : " + new_id_offre);
        pstmt.setInt(1,this.offre.id_vente);
        pstmt.setInt(2,this.offre.qte);
        pstmt.setString(3,this.offre.utilisateur);
        pstmt.setInt(4,this.offre.prix);
        pstmt.setInt(5,new_id_offre);

        ResultSet rset = pstmt.executeQuery();   
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
		}
	 catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
      }
        System.out.println("\n OFFRE EFFECTUEE!\n");
    }
    
    //Méthodes de mises à jour des attributs de la classe par des user inputs
    private void majIdvente() {
    		System.out.print("Entrer id_vente : ");
    		Scanner userInput = new Scanner(System.in);
    		String choix = userInput.nextLine();
    		this.offre.id_vente = Integer.parseInt(choix);
    }
    
    private void majUser() {
   		System.out.print("Entrer mail utilisateur : ");
    		Scanner userInput = new Scanner(System.in);
    		String choix = userInput.nextLine();
    		this.offre.utilisateur = choix;
    }
    
    private void majPrice() {
   		System.out.print("Entrer prix : ");
    		Scanner userInput = new Scanner(System.in);
    		String choix = userInput.nextLine();
    		this.offre.prix = Integer.parseInt(choix);
    }
    
    private void majQuantity() {
   		System.out.print("Entrer quantité : ");
    		Scanner userInput = new Scanner(System.in);
    		String choix = userInput.nextLine();
    		this.offre.qte = Integer.parseInt(choix);
    } 
    
    /* METHODES DE TEST POUR SAVOIR SI l'OFFRE EST POSSIBLE*/
    
    public boolean sellExists() {
    //Vérifie que la vente existe dans la db
       try {
         boolean existe = true;
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
	    PreparedStatement pstmt = conn.prepareStatement
		("SELECT * FROM Offre WHERE idvente =  " + this.offre.id_vente);
    	    ResultSet rset = pstmt.executeQuery();
    	    
    	    if (!rset.next() ) {
   		 System.out.println("Aucune vente avec l'id renseigné");
   		 existe = false;
	    }
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
	    
	    return existe;
		}
	 catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
      }
	 return false;   
    }

    private boolean validQuantity( int q){
    	return q <= this.vente.qte;
    }

    private boolean validPrice(int p){
	    if (this.vente.mont){
	        return true;
    	}
	    return p > this.lastOffer().prix;
    }
    
    public boolean userExists() {
    //Vérifie que l'utilisateur existe dans la db
      try {
         boolean existe = true;
         DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);
	    
        PreparedStatement pstmt = conn.prepareStatement
		("SELECT * FROM Utilisateur WHERE Email =  ?");

        pstmt.setString(1,this.offre.utilisateur);
    	ResultSet rset = pstmt.executeQuery();
    	    
    	if (!rset.next() ) {
   		 System.out.println("Aucun utilisateur avec l'email renseigné");
   		 existe = false;
	    }
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
	    
	    return existe;
    	}
    	catch (SQLException e) {
            System.err.println("failed");
            e.printStackTrace(System.err);
        }
        return false;
    }
    
    public boolean stillActive() {
        //Vérifie que la vente est toujours en cours dans la db
         ArrayList<Integer> res = new ArrayList<Integer>();
         try
         {
	        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	        Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	        conn.setAutoCommit(false);
	        conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM VENTE JOIN PRODUIT ON VENTE.IDPRODUIT = PRODUIT.ID_PRODUIT WHERE ( (VENTE.OFFRELIMITÉ <> 0 AND VENTE.DATEFIN < SYSDATE) OR (VENTE.Offrelimité = 0 AND (SYSDATE + INTERVAL '10' MINUTE) > (SELECT MAX(DATEOFFRE) FROM OFFRE WHERE OFFRE.IDVENTE = VENTE.ID)))"); 
             ResultSet rset = stmt.executeQuery();
             while (rset.next()) {
                 res.add(Integer.parseInt(rset.getString(1)));
             }
             for (Integer i : res)
             {
                System.out.println(i);             
             }
	         rset.close();
	         conn.commit();
	         stmt.close();
	         conn.close();
         }
         catch (SQLException e) {
             System.err.println("failed");
             e.printStackTrace(System.err);
         }
    	   if (res.contains(this.offre.id_vente)) {
             System.out.println("Vente finie");
    	   	return false;
    	   }
    	   return true;
    }
    

    /* METHODES POUR RECCUEILLIR DES INFOS SUR LES OFFRES */
    public int nextIdOffre(){
    int idoffre= 0;
	try{
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
	    PreparedStatement pstmt = conn.prepareStatement
		("SELECT * FROM Offre ORDER BY idoffre DESC");
	    
	    ResultSet rset = pstmt.executeQuery();
	    rset.next();	    
	    idoffre = rset.getInt("idoffre");
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
	    
	}catch (SQLException e) {
	    System.err.println("failed !");
	    e.printStackTrace();
	}
	return idoffre+1;
    }



    public Offre lastOffer(){
	Offre renvoie = null;
	try{
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
	    PreparedStatement pstmt = conn.prepareStatement
		("SELECT * FROM Offre WHERE idvente =  " + this.offre.id_vente + " ORDER BY dateoffre DESC");
	    
	    ResultSet rset = pstmt.executeQuery();
	    rset.next();	    
	    int idoffre = rset.getInt("idoffre");
	    int qte = rset.getInt("qteachetee");
	    String mail = rset.getString("email");
	    int prix = rset.getInt("prixachat");
	    int vente = rset.getInt("idvente");
	    Date date = rset.getDate("dateoffre");
	    renvoie = new Offre(vente, mail, prix, qte, date, idoffre);
	    //System.err.println(renvoie);
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
	    
	}catch (SQLException e) {
	    System.err.println("failed !");
	    e.printStackTrace();
	}
	return renvoie;
    }

    public Vente getVente(){
	Vente renvoie = null;
	try{
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
	    PreparedStatement pstmt = conn.prepareStatement
		("SELECT * FROM Vente WHERE id =  " + this.offre.id_vente);
	    ResultSet rset = pstmt.executeQuery();
	    rset.next();	    
	    int estmontante = rset.getInt("estmontante");
	    int qte = rset.getInt("qtélot");
	    int revoc = rset.getInt("revocabilité");
	    int limite = rset.getInt("offrelimité");
	    Date date = rset.getDate("datefin");
	    renvoie = new Vente(revoc == 1, limite == 1, estmontante == 1, date, qte);
	    //System.err.println(renvoie);
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
	    
	}catch (SQLException e) {
	    System.err.println("failed !");
	    e.printStackTrace();
	}
	return renvoie;
    }

    public boolean noOffer(){
	boolean result = false;
	try{
	    DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
	    conn.setAutoCommit(false);
	    conn.setTransactionIsolation(conn.TRANSACTION_READ_COMMITTED);
	    PreparedStatement pstmt = conn.prepareStatement
		("SELECT * FROM Offre WHERE idvente =  " + this.offre.id_vente + " and email ='" + this.offre.utilisateur + "'");
	    ResultSet rset = pstmt.executeQuery();
	    result= !rset.next();
	    rset.close();
	    conn.commit();
	    pstmt.close();
	    conn.close();
	      

	}catch (SQLException e) {
	    System.err.println("failed !");
	    e.printStackTrace();
	}
	return result;
    }
    
    private void dumpResult(ResultSet r) throws SQLException {
	while (r.next()) {
	    int idoffre = r.getInt("idoffre");
	    int qte = r.getInt("qteachetee");
	    String mail = r.getString("email");
	    int prix = r.getInt("prixachat");
	    int vente = r.getInt("idvente");
	    Date date = r.getDate("dateoffre");
	    System.err.println(idoffre + " " + qte + " "+ mail +" "+ prix + " "+ vente + " "+date);
	}
    }
}
