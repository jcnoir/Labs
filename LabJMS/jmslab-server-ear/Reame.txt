
Compilation / Deploiement
-------------------------
mvn clean install   : lance la compilation et l'installation des artifacts dans le repository local Maven
Il faut ensuite : 
 1- copier dans JBoss le fichier EAR ainsi que le fichier de définition de la queue JMS "src/main/config/myservice-destinations-services.xml"
 2- copier le WAR dans Tomcat
 
ou bien si les variables d'environnement suivantes sont positionnés et tomcat lancé:  
   $JBOSS_HOME
   $JBOSS_CONF (initialisée à "default")
   
mvn -P HardDeploy clean install : effectue le déploiement en local dans Tomcat et dans JBoss


Lancement de JBoss 
-------------------------
cd $JBOSS_HOME/bin
./run.sh -Djboss.service.binding.set=ports-01  -Djboss.partition.name=JmsLabs -b 0.0.0.0 -c $JBOSS_CONF


Lancement de Tomcat 
-------------------------
cd $CATALINA_HOME/bin
./startup.sh


URL pour appeler la servlet 
-------------------------
http://localhost:8080/jmslab-client-webapp/jmsLabClient?m=coucou&o=Laurent 