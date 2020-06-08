# Mise en place du projet
Cloner ce répertoire: https://github.com/AntoineMorel2/LeBonCoin.git

Récupérer le dumpDatabase.sql présent à la racine et exécutez le sur un serveur local avec comme user root et mot de passe Rootroot1 ou modifiez directement la configuration du fichier hibernate.cfg.xml avec vos propres user et mot de passe.
Vous devriez maintenant disposer de la base de données et d’un jeu de données de test.

# Configuration
- Si vous avez une erreur de configuration sur l'import de Base64, il faut ajouter dans les options de compiler cette ligne : <br>
`` <module name="LeBonCoin" options="--add-exports=java.xml/com.sun.org.apache.xerces.internal.impl.dv.util=ALL-UNNAMED" /> ``

- Si vous avez une erreur sur ``Path.of`` : L'application a été réalisé avec java 11 comme SDK, il faut donc ajouter celui-ci en tant que SDK d'éxécution : 
Sur IntelliJ : File > Project Structure > SDKs > + > JDK
Allez dans le dossier d'installation de IntelliJ et dans ces dossiers ensuite "IntelliJ IDEA 2019.2.4\jbr" vous avez donc un path de ce type : "C:\Program Files\JetBrains\IntelliJ IDEA 2019.2.4\jbr". Et cliquez ensuite sur OK. Vous avez donc maintenant 11 dans votre liste.
Sur la gauche de la fenêtre, cliquez sur Project. Dans la liste déroulante contenue dans le sous-menu Project SDK selectionnez la version 11. Cliquez sur "Apply" et ensuite "Ok". Vous devriez maintenant pouvoir build le projet sans erreur.

- Si vous avez une erreur autre que les deux présentes ci-dessus, veuillez nous envoyer un mail.

# Execution
Vous pouvez maintenant executer l'application.
Une fois arrivé sur la page de connexion, inscrivez-vous et vous pouvez ensuite vous connecter.
L'application contient des donnnées test : Annonces déjà créée, utilisateurs, et des commentaires sur certaines annonces.

# Notation
Vous pouvez choisir une note entre 15 et 20. <br>
Une IllegalArgumentException sera levée si la note est en dehors de ses balises.
