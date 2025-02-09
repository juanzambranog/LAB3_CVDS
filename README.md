# LAB3_CVDS

## INTEGRANTES 
### - Juan David Zambrano Gonzalez
### - Allan Contreras 


## CREAR PROYECTO CON MAVEN

Creamos el proyecto con Maven usando los parametros solicitados. 

![alt text](resources/image-4.png)

Con el siguiente comando realizamos la creacion del proyecto con los respectivos parametros. 

```mvn archetype:generate -DgroupId=edu.eci.cvds- -DartifactId=Library -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.5 -DinteractiveMode=false -Dpackage=edu.eci.cvds.tdd```

![alt text](resources/image.png)


![alt text](resources/image-1.png)

Una vez creado el proyecto verificamos que la estructura sea la esperada usando ```tree```

![alt text](resources/image-2.png)

Compilamos el proyecto para validar que todo este bien.

![alt text](resources/image-15.png)

## ESQUELETO DEL PROYECTO

Ahora agregamos el esqueleto del proyecto segun nos lo indica el documento.

![alt text](resources/image-16.png)

Y usando ```tree``` verificamos que halla quedado bien.

![alt text](resources/image-5.png)


## AGREGAR CLASES

Creamos las clases solicitadas en los paquetes respectivos.  

![alt text](resources/image-6.png)

Y comprobamos que la estructura esta bien usando ```mvn clean package```

![alt text](resources/image-7.png)

![alt text](resources/image-8.png)

## PRUEBAS UNITARIAS Y TDD


### COBERTURA

Agregamos la dependencia de Jacoco 

![alt text](resources/image-17.png)

Abrimos el archivo index.html para ver la cobertura inicial del proyecto. 

![alt text](resources/image-18.png)

## SONARQUBE

descargamos la imagen del docker con el siguiente comando ```docker pull sonarqube```
![alt text](resources/image-9.png)

Ahora arrancamos el servicio de SonarQube con el siguiente comando ```docker run -d --name sonarqube -e SONAR_ES_BOOTSTRAP_CHECKS_DISABLE=true -p 9000:9000 sonarqube:latest```

![alt text](resources/image-10.png)

Y validamos el funcionamiento con el comando ```docker ps -a```

![alt text](resources/image-11.png)

Iniciamos sesión en sonar localhost:9000 y cambiamos la clave por defecto.

![alt text](resources/image-12.png)

Entramos a las opciones de la cuenta.
Account -> settings -> generate token.
Y generamos un token.

![alt text](resources/image-13.png)

![alt text](resources/image-14.png)

Nuestro token es:

```sqa_d8ca3e33f8faa5f0735ab0edd254f93435105bb9```

Instalamos SonarQube/SonarLint en nuestro IDE en este caso en ```Visual Studio Code```

![alt text](resources/image-19.png)

Añadimos el plugin de Sonar en el archivo pom del proyecto.

![alt text](resources/image-21.png)

Y añadimos las propiedades de SonarQube y Jacoco.

![alt text](resources/image-20.png)

