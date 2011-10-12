Lors du demarrage du serveur JBoss
----------------------------------------------

[...]
12:17:30,882 WARN  [SessionSpecContainer] EJBTHREE-2126: container jboss.j2ee:ear=lab_ejb31-server.ear,jar=lab_ejb31-server-ejb-1.0-SNAPSHOT.jar,name=MyStartupBean,service=EJB3 does not verify the businessObjectFactory
12:17:30,882 INFO  [EJBContainer] STARTED EJB: eu.akka.labs.server.MyStartupBean ejbName: MyStartupBean
12:17:30,890 WARN  [TimerServiceContainer] EJBTHREE-2193: using deprecated TimerServiceFactory for restoring timers
12:17:31,005 INFO  [MyStartupBean] @PostConstruct startup : done
[...]


Lors de l'arrêt du serveur JBoss
---------------------------------------------

[...]
12:18:16,448 INFO  [SessionSpecContainer] Stopping jboss.j2ee:ear=lab_ejb31-server.ear,jar=lab_ejb31-server-ejb-1.0-SNAPSHOT.jar,name=MyStartupBean,service=EJB3
12:18:16,463 INFO  [EJBContainer] STOPPED EJB: eu.akka.labs.server.MyStartupBean ejbName: MyStartupBean
12:18:16,470 INFO  [MyStartupBean] @PreDestroy shutdown : done
[...]




