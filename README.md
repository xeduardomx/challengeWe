# challengeWe

CRUD reactivo con Spring Boot y MongoDB + uso de hilos.

Arquitectura del proyecto.

Modelo: Contiene la información de las entidades del negocio.
Repositorio: Implementación del patrón Repository (abstracción de los datos a entidades del negocio).
Servicio: Capa de servicios, donde está la lógica principal.
Controlador: Capa que actúa como entrada, contiene los controladores.




Para importar la coleccion de test de POSTMAN ir a:

File --> Import --> File --> upload files

Seleccionar el archivo:

-Servicios Wenance Challenge.postman_collection.json
El cual se encuentra en el directorio raiz.

Instrucciones de ejecución en entorno local:
Clonar el repositorio.
Importar proyecto en eclipse (o IDE de su preferencia), como MAVEN EXISTING PROJECT.
ir al archivo que contiene el main main (ChallengeWenanceApplication)
click boton derecho 'Run as Java Aplication'

SOLUCION:

-El servicio inicia un Thread el cual queda como un demonio en ejecución cada 10 segundos.

-Dicho hilo se encarga de invocar al endpoint (https://cex.io/api/last_price/BTC/USD), para ello usa RestTemplate.

-Para el CRUD sobre MongoDB se uso el stack reactivo (WebFlux) de Spring Boot para Mongo.

-Se requiere una base de datos Mongo instalada, en caso de no poseerla puede ejecutar el siguiente comando:
 docker run -d -p 27017-27019:27017-27019 --name mongodb mongo



