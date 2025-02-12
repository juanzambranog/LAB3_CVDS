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

Implementamos el metodo TDD, por lo que para cada metodo realizamos las pruebas necesarias antes de completar el metodo en base a la documentacion proporcionada.

### TEST

![alt text](resources/image-22.png)

![alt text](resources/image-23.png)

![alt text](resources/image-24.png)


### COBERTURA

Agregamos la dependencia de Jacoco 

![alt text](resources/image-17.png)

Abrimos el archivo index.html para ver la cobertura inicial del proyecto. 

![alt text](resources/image-18.png)

Ahora limpiamos los datos de las compilaciones anteriores con ``` mvn clean```

![alt text](resources/image-25.png)

Una vez limpiemos el proyecto ejecutamos las pruebas de nuevo con ```mvn test```

![alt text](resources/image-26.png)

![alt text](resources/image-27.png)

Una vez las pruebas hallan corrido generamos el reporte de jacoco con ```mvn jacoco:report```

![alt text](resources/image-28.png)


Ahora buscamos el reporte que se encuentra en ```target\site\jacoco``` y es un archivo ```index.html```


![alt text](resources/image-29.png)

Lo abrimos y miramos el repote de Jacoco actualizado 


![alt text](resources/image-30.png)

Como podemos ver en el reporte tenemos una covertura total de 83%



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

![alt text](resources/image-31.png)

Nuestro token es:

```squ_dc047689f4b24e5fae28bd175e533dce8cf5799d```

Instalamos SonarQube/SonarLint en nuestro IDE en este caso en ```Visual Studio Code```

![alt text](resources/image-19.png)

Añadimos el plugin de Sonar en el archivo pom del proyecto.

![alt text](resources/image-21.png)

Y añadimos las propiedades de SonarQube y Jacoco.

![alt text](resources/image-20.png)

Una vez completemos lo anterior generamos la integración con sonar ```mvn verify sonar:sonar -D sonar.token=squ_dc047689f4b24e5fae28bd175e533dce8cf5799d```

![alt text](resources/image-32.png)

![alt text](resources/image-33.png)

Ahora resta verificar en SonarQube 

![alt text](resources/image-34.png)