1. Nettoyage des sauts de ligne et renommage des variables en anglais des models.
2. Nettoyage dans le package service : renommage en anglais de certaines méthodes et attributs.
3. Remplacer les méthodes du service appelé par les nouvelles dans AdminController.
4. Retrait de la méthode getHello par getAdminPage dans AdminController et remplacement de"/alldatas" par "/".
5. Nettoyage des fichiers dans le package controller. Renommage des endpoints et des méthodes.
6. Ajout de la méthode addNewUser dans UtilisateurService et retrait des données dans la méthode signUp dans HomeController.
7. Retrait commentaire dans SecurityConfig.
8. Mise en place d'un username unique dans le model Utilisateur.
9. Restriction des chemins d'accès pour admin, user et suspend
10. Ajout d'un envoi dans le template home des infos de l'utilisateur connecter et affichage de l'username dans la phrase de bienvenue.
11. Ajout de tailwindcss et Modification du css menu
12. Ajout d'une condition afin de vérifier que la transaction n'est pas en dessous du solde du receveur avant de le soustraire.
13. Ajout css page d'accueil
14. Ajout css page admin
15. Ajout envoie d'un message afin de montrer a l'utilisateur que la transaction a été effectuée ou pas.
16. Ajout de l'attribut report qui est un booléen dans le model Transaction.
17. Ajout d'un formulaire dans le template transaction afin de donner la possibilité à l'utilisateur de pouvoir signaler une transaction.
18. Ajout de la méthode dans le controller afin de pouvoir enregistrer que la transaction a été signaler dans la base de donner.
19. Affichage du signalement dans la page admin.
20. Ajout du fichier sql afin d'initialiser la base de donnée avec les données présentes dans le fichier dataInitializer. Suppression du fichier dataInitializer et du package.
21. Ajout du css sur toutes les pages.
22. Ajout de la visibilité du formulaire d'inscription si l'utilisateur est null.