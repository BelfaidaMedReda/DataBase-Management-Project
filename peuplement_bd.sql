-- Insertion des données dans la table vente
INSERT INTO vente VALUES(2, 1, 0, 2, '15-DEC-24', 10, 'jean.dupont@genoble-inp.org', 0, 2);
INSERT INTO vente VALUES(3, 1, 0, 18, '19-JAN-25', 100, 'marie.dupuis@genoble-inp.org', 1, 2);
INSERT INTO vente VALUES(4, 1, 1, 10, '28-FEB-25', 20, 'claire.lemoine@genoble-inp.org', 1, 2);
INSERT INTO vente VALUES(5, 0, 1, 3, '07-MAR-25', 30, 'lucas.durand@genoble-inp.org', 0, 5000);
INSERT INTO vente VALUES(6, 1, 0, 6, '21-DEC-24', 11, 'sophie.bertrand@genoble-inp.org', 0, 2);
INSERT INTO vente VALUES(7, 0, 0, 14, '30-SEP-25', 18, 'julien.ferrier@genoble-inp.org', 1, 5000);
INSERT INTO vente VALUES(8, 1, 1, 9, '12-OCT-25', 27, 'aline.perrin@genoble-inp.org', 1, 2);
INSERT INTO vente VALUES(9, 0, 1, 5, '07-NOV-25', 36, 'olivier.morel@genoble-inp.org', 0, 5000);
INSERT INTO vente VALUES(10, 1, 0, 4, '09-DEC-24', 150, 'isabelle.roux@genoble-inp.org', 0, 2);
INSERT INTO vente VALUES(11, 0, 1, 11, '29-NOV-24', 114, 'thierry.fabre@genoble-inp.org', 0, 5000);
INSERT INTO vente VALUES(12, 0, 1, 12, '18-FEB-25', 68, 'nathalie.boucher@genoble-inp.org', 1, 5000);
INSERT INTO vente VALUES(13, 0, 0, 7, '14-NOV-25', 3, 'antoine.lemoine@genoble-inp.org', 0);

-- Insertion des données dans la table caracteristique
INSERT INTO caracteristique VALUES('Poids', '50', 1);
INSERT INTO caracteristique VALUES('Type', 'hetre', 1);
INSERT INTO caracteristique VALUES('Tissu', 'coton', 2);
INSERT INTO caracteristique VALUES('Sport', 'rugby', 3);
INSERT INTO caracteristique VALUES('Marque', 'renault', 4);
INSERT INTO caracteristique VALUES('Poids', '1500', 4);
INSERT INTO caracteristique VALUES('Poids', '400', 5);
INSERT INTO caracteristique VALUES('Poids', '0.1', 6);
INSERT INTO caracteristique VALUES('Poids', '1', 7);
INSERT INTO caracteristique VALUES('Type', 'Porcelaine', 8);
INSERT INTO caracteristiquev VALUES('Poids', '15', 8);
INSERT INTO caracteristique VALUES('Poids', '20', 9);
INSERT INTO caracteristique VALUES('Composant', 'interrupteur', 10);
INSERT INTO caracteristique VALUES('Composant', 'cable usb', 11);
INSERT INTO caracteristique VALUES('Type', 'Marteau', 12);
INSERT INTO caracteristique VALUES('Poids', '1.5', 12);
INSERT INTO caracteristique VALUES('Type', 'Tournevis', 13);
INSERT INTO caracteristique VALUES('Sport', 'Basketball', 14);
INSERT INTO caracteristique VALUES('Couleur', 'Noir', 14);
INSERT INTO caracteristique VALUES('Marque', 'Opel', 15);
INSERT INTO caracteristique VALUES('Type', 'Plantes carnivores', 18);

-- Insertion des données dans la table salle
INSERT INTO salle VALUES('Bois', 1, 1, 0);
INSERT INTO salle VALUES('Voiture', 1, 0, 0);
INSERT INTO salle VALUES('Plastique', 1, 1, 0);
INSERT INTO salle VALUES('Plastique', 0, 1, 0);
INSERT INTO salle VALUES('Nourriture', 0, 1, 0);
INSERT INTO salle VALUES('Voiture', 0, 0, 0);
INSERT INTO salle VALUES('Ballons', 1, 0, 0);
INSERT INTO salle VALUES('Ballons', 0, 1, 0);
INSERT INTO salle VALUES('Electronique', 1, 1, 0);
INSERT INTO salle VALUES('Vaisselle', 1, 1, 0);
INSERT INTO salle VALUES('Vaisselle', 0, 1, 0);
INSERT INTO salle VALUES('Outils', 0, 0, 1);
INSERT INTO salle VALUES('Outils', 0, 1, 1);
INSERT INTO salle VALUES('Plantes', 1, 0, 0);
INSERT INTO salle VALUES('Plantes', 0, 0, 0);

-- Insertion des données dans la table appartient_a
INSERT INTO appartient_a VALUES('jean.dupont@genoble-inp.org', 1);
INSERT INTO appartient_a VALUES('marie.dupuis@genoble-inp.org', 5);
INSERT INTO appartient_a VALUES('pierre.martin@genoble-inp.org', 6);
INSERT INTO appartient_a VALUES('claire.lemoine@genoble-inp.org', 7);
INSERT INTO appartient_a VALUES('lucas.durand@genoble-inp.org', 8);
INSERT INTO appartient_a VALUES('sophie.bertrand@genoble-inp.org', 9);
INSERT INTO appartient_a VALUES('julien.ferrier@genoble-inp.org', 10);

-- Insertion des données dans la table produit
INSERT INTO produit VALUES(1, 60, 1, 'Bois');
INSERT INTO produit VALUES(5, 100, 12, 'Bois');
INSERT INTO produit VALUES(6, 10, 23, 'Plastique');
INSERT INTO produit VALUES(7, 20, 8, 'Plastique');
INSERT INTO produit VALUES(8, 30, 45, 'Vaisselle');
INSERT INTO produit VALUES(9, 40, 77, 'Vaisselle');
INSERT INTO produit VALUES(10, 60, 34, 'Electronique');
INSERT INTO produit VALUES(11, 6, 9, 'Electronique');
INSERT INTO produit VALUES(12, 1, 2, 'Outils');
INSERT INTO produit VALUES(13, 90, 18, 'Outils');
INSERT INTO produit VALUES(14, 60, 100, 'Ballons');
INSERT INTO produit VALUES(15, 10, 1, 'Voiture');
INSERT INTO produit VALUES(16, 70, 500, 'Vetement');
INSERT INTO produit VALUES(17, 600, 10, 'Plantes');
INSERT INTO produit VALUES(18, 150, 21, 'Plantes');
INSERT INTO produit VALUES(19, 9, 1087, 'Nourriture');
INSERT INTO produit VALUES(20, 37, 492, 'Nourriture');

-- Insertion des données dans la table categorie
INSERT INTO categorie VALUES('Outils', 'Différents outils de petites tailles');
INSERT INTO categorie VALUES('Vaisselle', 'Ensemble complet de vaisselle');
INSERT INTO categorie VALUES('Plantes', 'Toutes sortes de petites plantes');

-- Insertion des données dans la table utilisateur
INSERT INTO utilisateur VALUES('jean.dupont@genoble-inp.org', 'Jean', 'Dupont', '10 Rue de la République 75001 Paris');
INSERT INTO utilisateur VALUES('marie.dupuis@genoble-inp.org', 'Marie', 'Dupuis', '15 Boulevard Montparnasse, 75006 Paris');
INSERT INTO utilisateur VALUES('pierre.martin@genoble-inp.org', 'Pierre', 'Martin', '23 Avenue des Champs-Élysées, 75008 Paris');
INSERT INTO utilisateur VALUES('claire.lemoine@genoble-inp.org', 'Claire', 'Lemoine', '5 Place de la Concorde, 75008 Paris');
INSERT INTO utilisateur VALUES('lucas.durand@genoble-inp.org', 'Lucas', 'Durand', '12 Rue de Rennes, 75006 Paris');
INSERT INTO utilisateur VALUES('sophie.bertrand@genoble-inp.org', 'Sophie', 'Bertrand', '8 Rue de la Sorbonne, 75005 Paris');
INSERT INTO utilisateur VALUES('julien.ferrier@genoble-inp.org', 'Julien', 'Ferrier', '24 Quai de la Tournelle, 75005 Paris');
INSERT INTO utilisateur VALUES('aline.perrin@genoble-inp.org', 'Aline', 'Perrin', '30 Rue Saint-Antoine, 75003 Paris');
INSERT INTO utilisateur VALUES('olivier.morel@genoble-inp.org', 'Olivier', 'Morel', '7 Rue des Archives, 75003 Paris');
INSERT INTO utilisateur VALUES('isabelle.roux@genoble-inp.org', 'Isabelle', 'Roux', '19 Rue de la Liberté, 75013 Paris');
INSERT INTO utilisateur VALUES('thierry.fabre@genoble-inp.org', 'Thierry', 'Fabre', '11 Boulevard de l Indépendance, 75020 Paris');
INSERT INTO utilisateur VALUES('nathalie.boucher@genoble-inp.org', 'Nathalie', 'Boucher', '16 Rue du Faubourg Saint-Antoine, 75012 Paris');
INSERT INTO utilisateur VALUES('antoine.lemoine@genoble-inp.org', 'Antoine', 'Lemoine', '22 Rue de la Paix, 75002 Paris');
INSERT INTO utilisateur VALUES('valerie.charles@genoble-inp.org', 'Valérie', 'Charles', '3 Avenue de la Gare, 75011 Paris');
INSERT INTO utilisateur VALUES('francois.petit@genoble-inp.org', 'François', 'Petit', '14 Boulevard des Italiens, 75009 Paris');
INSERT INTO utilisateur VALUES('laure.marchand@genoble-inp.org', 'Laure', 'Marchand', '25 Rue des Martyrs, 75009 Paris');
INSERT INTO utilisateur VALUES('bernard.robin@genoble-inp.org', 'Bernard', 'Robin', '18 Rue de la Villette, 75019 Paris');
INSERT INTO utilisateur VALUES('marion.brunet@genoble-inp.org', 'Marion', 'Brunet', '2 Rue de la Montagne Sainte-Geneviève, 75005 Paris');
INSERT INTO utilisateur VALUES('gregoire.dubois@genoble-inp.org', 'Grégoire', 'Dubois', '13 Rue du Pont Neuf, 75001 Paris');
INSERT INTO utilisateur VALUES('audrey.vincent@genoble-inp.org', 'Audrey', 'Vincent', '4 Rue des Petits Champs, 75002 Paris');

-- Insertion des données dans la table offre
INSERT INTO offre VALUES('10-DEC-24', 2, 5, 'jean.dupont@genoble-inp.org', 10, 2);
INSERT INTO offre VALUES('13-DEC-24', 3, 7, 'marie.dupuis@genoble-inp.org', 12, 2);
INSERT INTO offre VALUES('05-JAN-25', 4, 48, 'pierre.martin@genoble-inp.org', 5, 3);
INSERT INTO offre VALUES('06-JAN-25', 5, 55, 'claire.lemoine@genoble-inp.org', 6, 3);
INSERT INTO offre VALUES('08-JAN-25', 6, 57, 'pierre.martin@genoble-inp.org', 8, 3);
INSERT INTO offre VALUES('19-JAN-25', 7, 20, 'lucas.durand@genoble-inp.org', 50, 4);
INSERT INTO offre VALUES('05-MAR-25', 8, 25, 'sophie.bertrand@genoble-inp.org', 35, 5);
INSERT INTO offre VALUES('15-DEC-24', 9, 5, 'julien.ferrier@genoble-inp.org', 15, 6);
INSERT INTO offre VALUES('18-DEC-24', 10, 6, 'aline.perrin@genoble-inp.org', 20, 6);
INSERT INTO offre VALUES('30-AUG-25', 11, 10, 'isabelle.roux@genoble-inp.org', 30, 7);
