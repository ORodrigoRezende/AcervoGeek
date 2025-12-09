##Como compilar

#Compilar e rodar
javac modelo/*.java persistencia/BancodeDados.java persistencia/IDNaoExistenteExeception.java persistencia/Persistente.java visao/*.java Programa.java

java Programa

#Compilar e rodar com JUnit
javac -cp ".:lib/junit-platform-console-standalone-1.13.0-M3.jar" */*.java

java -jar lib/junit-platform-console-standalone-1.13.0-M3.jar --class-path . --scan-class-path