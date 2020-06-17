#  Servidor-Web-Concurrente
## Manual de usuario

Si se deseea hacer uso del programa lo primero que debe realizarse el clonar el repositorio almacenado en Github a través del siguiente comando:

```
git clone https://github.com/csarssj/ARSW-4-Servidor-Web

```
O si desea puede descargarlo como archivo zip y luego descomprimirlo en la carpeta que desee.

Una vez hecho alguno de los dos pasos anteriores, nos dirigimos a la ruta de instalación y por medio de la consola digitamos el siguiente comando:

```
mvn package

```
Ejecutando el siquiente comando en la consola y accediendo al siguiente link se puede visualizar una prueba.

```
java -cp target\WebServer-1.0-SNAPSHOT.jar edu.escuelaing.arsw.webserver.Client

```
o ingresando a través de este link desplegado en heroku:

[heroku](https://calm-brushlands-54526.herokuapp.com/index.html)

![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/prueba.png)

### Prerequisitos

Este proyecto necesita tener los siguientes progamas instalados en la máquina donde se deseea ejecutar:

```
  java version "1.8.0_251"
  Apache Maven 3.6.3
  git version 2.25.0.windows.1
  jdk1.8.0_251
```

El sistema, mas alla de facilitar el registro de las iniciativas e ideas de proyectos, es una valiosa base de conocimiento donde los diferentes actores pueden revisar si hay iniciativas, ideas o intereses similares y aunar esfuerzos para la materializacion.

## Servidor Concurrente 

Un servidor concurrente atiende a varios clientes al mismo tiempo. Más aún, mientras está atendiendo sigue escuchando El problema es que todo cliente tiene que esperar su turno para ser atendido. 
Si uno de ellos pide un archivo muy grande los demás tienen que esperar La mayor parte de la espera es debido a operaciones de IO, hay capacidad de CPU ociosa! Se trata de crear un nuevo proceso o línea de ejecución cada vez que un cliente "llega" a pedir un servicio.

#### Funcionamiento

Servidores Comcurrentes: hay procesos separados para atender el puerto y para transferir el archivo

![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/server1.png)

Después que el cliente contacta al servidor, éste crea otro proceso para para atender al cliente y se queda escuchando el puerto por otro
Mientras el nuevo proceso está atendiendo al primer cliente, el segundo cliente puede contactar al servidor en el puerto 

![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/server2.png)

Y el servidor crea otro proceso  ahora un tercer cliente contacta al servidor

![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/server3.png)

## Pruebas

Para las pruebas se realizaron dos tipo de prueba; La primera con Jmeter  y las segundas con una clase prueba.

- *JMeter* : nos permite someter a nuestra aplicación a pruebas de estrés, es decir, podemos simular en nuestros entornos de desarrollo la carga de peticiones concurrentes 
			que nuestra aplicación podría tener en determinado momento en el entorno productivo.
	

  - Configuracion

	![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/jmeter.png)
	
  - Arbol de resultados

	![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/jmeter2.png)
	
  - Gráfico

	![image](https://github.com/csarssj/ARSW-4-Servidor-Web/blob/master/img/jmeter3.png)


- *ClaseTest*

Para este tipo de pruebas se diseño una clase cliente/Browser que se encarga de hacer las peticiones concurrentes a nuestro programa.

Iniciamos una clase hilo que va a encargarse de iniciar el hilo correspondiente a los parametros que se le ingresen donde el primero sera la url a la cual se quiere acceder
y el segundo el numero de peticiones que se requiere para la prueba.
Al final se realiza un ciclo que cuenta la cantidad de hilos que se abrieron a lo largo de la prueba.
```
/**
 * Hilo encargado de realizar las peticiones al servidor
 *  
 * @author ceseg
 */
public class clientTest  extends Thread {
    
    private static String url;
    private static int solicitudes;
    
    public clientTest(String temp){ 
      this.url=temp;
    }
    @Override
    public void run() {
        try {
            URL url = new URL(this.url); 
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String line = null; 
            while ((line = reader.readLine()) != null) {
                  //  System.out.println(line);
                }
            reader.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Encargado de iniciar el cliente y de recibir los datos
     * 
     * @param args url y numero de peticiones para ingresar
     * @throws MalformedURLException 
     */
    public static void  main(String [] args) throws MalformedURLException, IOException{
        URL url = new URL(args[0]);
        solicitudes = Integer.parseInt(args[1]);
        clientTest[] threads =  new clientTest[solicitudes];
        for (int i = 0; i < solicitudes; i++) {
            threads[i] = new clientTest(args[0]);
            threads[i].start();
        }
        int contador = 0;
        for (Thread t : threads) {
            contador++;
        }
        System.out.println("Total:" + contador);
        
    }
}
```

## Construido en

* [Maven](https://maven.apache.org/) - Dependency Management


## Control de versiones y pruebas 

[Github](https://github.com/) para el versionamiento.

[Jmeter](https://jmeter.apache.org/) para las pruebas.

## Authors

[César González](https://github.com/csarssj) 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
