-- Table UTILISATEUR
CREATE TABLE UTILISATEUR (
    EMAIL VARCHAR2(50) PRIMARY KEY,
    PRENOM VARCHAR2(20) NOT NULL,
    NOM VARCHAR2(50) NOT NULL,
    ADRESSEPOSTALE VARCHAR2(50) NOT NULL
);

-- Table PRODUIT
CREATE TABLE PRODUIT (
    ID_PRODUIT NUMBER PRIMARY KEY,
    PRIX_DE_REVIENT FLOAT(126) NOT NULL,
    STOCK NUMBER NOT NULL,
    NOM_CATEGORIE VARCHAR2(20),
    CONSTRAINT PRODUIT_FK1 FOREIGN KEY (NOM_CATEGORIE) REFERENCES CATEGORIE(NOM) ON DELETE CASCADE
);

-- Table VENTE
CREATE TABLE VENTE (
    ID NUMBER PRIMARY KEY,
    IDPRODUIT NUMBER,
    DATEFIN DATE,
    QTÉLOT NUMBER,
    EMAIL VARCHAR2(50),
    ESTMONTANTE NUMBER,
    OFFRELIMITÉ NUMBER,
    REVOCABILITÉ NUMBER,
    PRIX_DEPART NUMBER NOT NULL,
    CONSTRAINT VENTE_FK1 FOREIGN KEY (EMAIL) REFERENCES UTILISATEUR(EMAIL) ON DELETE CASCADE,
    CONSTRAINT VENTE_FK2 FOREIGN KEY (IDPRODUIT) REFERENCES PRODUIT(ID_PRODUIT) ON DELETE CASCADE
);

-- Table CATEGORIE
CREATE TABLE CATEGORIE (
    NOM VARCHAR2(20) PRIMARY KEY,
    DESCRIPTION VARCHAR2(100) NOT NULL
);

-- Table SALLE
CREATE TABLE SALLE (
    CATEGORIE VARCHAR2(20) NOT NULL,
    REVOCABILITE NUMBER NOT NULL,
    ESTMONTANTE NUMBER NOT NULL,
    OFFRE_LIMITEE NUMBER NOT NULL,
    PRIMARY KEY (CATEGORIE, REVOCABILITE, ESTMONTANTE, OFFRE_LIMITEE),
    CONSTRAINT SALLE_FK1 FOREIGN KEY (CATEGORIE) REFERENCES CATEGORIE(NOM) ON DELETE CASCADE
);

-- Table OFFRE
CREATE TABLE OFFRE (
    IDOFFRE NUMBER PRIMARY KEY,
    DATEOFFRE DATE,
    QTEACHETEE NUMBER,
    EMAIL VARCHAR2(255) NOT NULL,
    PRIXACHAT NUMBER,
    IDVENTE NUMBER,
    CONSTRAINT OFFRE_FK1 FOREIGN KEY (EMAIL) REFERENCES UTILISATEUR(EMAIL) ON DELETE CASCADE
);

-- Table CARACTERISTIQUE
CREATE TABLE CARACTERISTIQUE (
    NOM VARCHAR2(20) NOT NULL,
    VALEUR VARCHAR2(20),
    ID_PRODUIT NUMBER NOT NULL,
    PRIMARY KEY (NOM, ID_PRODUIT),
    CONSTRAINT CARACTERISTIQUES_FK1 FOREIGN KEY (ID_PRODUIT) REFERENCES PRODUIT(ID_PRODUIT) ON DELETE CASCADE
);

-- Table APPARTIENT_A
CREATE TABLE APPARTIENT_A (
    EMAIL VARCHAR2(50),
    ID_PRODUIT NUMBER NOT NULL,
    PRIMARY KEY (ID_PRODUIT),
    CONSTRAINT APPARTIENT_A_FK1 FOREIGN KEY (EMAIL) REFERENCES UTILISATEUR(EMAIL) ON DELETE CASCADE,
    CONSTRAINT APPARTIENT_A_FK2 FOREIGN KEY (ID_PRODUIT) REFERENCES PRODUIT(ID_PRODUIT) ON DELETE CASCADE
);