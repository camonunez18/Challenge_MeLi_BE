# Backend del Challenge MeLi

Este repositorio contiene el código fuente del microservicio backend desarrollado en **Java con Spring Boot** para el Challenge de Mercado Libre. Su responsabilidad principal es exponer una API REST que proporciona información de productos, actuando como el cerebro detrás de la aplicación frontend.

## 🚀 Desafío Personal y Aprendizaje en Contenerización

Aunque mi experiencia se centra principalmente en el desarrollo backend, uno de los desafíos clave en este proyecto fue asegurar que la aplicación Spring Boot pudiera ser **contenerizada eficientemente con Docker** y que funcionara sin problemas como parte de una orquestación con Docker Compose.

Los puntos más destacados de este desafío fueron:

* **Creación del Dockerfile:** Recordar y aplicar las mejores prácticas para construir un `Dockerfile` multietapa (multi-stage build) que optimizara el tamaño de la imagen final y el tiempo de construcción. Esto incluyó separar la fase de compilación (con Maven) de la fase de ejecución (con JRE).
* **Selección de Imágenes Base:** Encontrar las imágenes Docker base correctas y optimizadas para Maven y JDK (como `maven:latest` para la construcción y `openjdk:latest` para la ejecución), que ofrecieran un buen equilibrio entre funcionalidad, seguridad y tamaño de imagen reducido.
* **Integración con Docker Compose:** Asegurar que el backend se integrara sin problemas en el `docker-compose.yaml`, permitiendo que el frontend lo descubriera y se comunicara con él dentro de la red Docker.

## 🛠️ Tecnologías Utilizadas

* **Java 17**
* **Spring Boot 3.5.3**
* **Maven**
* **Docker**
* **JSON** (como fuente de datos para el ejemplo)

## 🌐 Diagramas Técnicos

### Diagrama de Componentes

Este diagrama ilustra la arquitectura de alto nivel de la solución, mostrando la interacción entre el cliente (navegador web), el frontend y las capas internas del microservicio backend.

<img src="https://github.com/user-attachments/assets/08458ce2-9760-454e-8000-81a88f753469" alt="Diagrama de Componentes" width="800"/>

1.  **Cliente (Navegador Web) y Frontend Web:**
    * **Propósito:** Es la capa de presentación. Interactúa directamente con el usuario, mostrando la información de los productos y capturando sus interacciones.
    * **Interacción:** Envía solicitudes HTTP al Backend (Microservicio Spring Boot) para obtener los datos necesarios y renderizar la interfaz.

2.  **Backend (Microservicio Spring Boot):**
    Este microservicio está compuesto por las siguientes capas lógicas:

    * **Product Controller (Capa de Presentación):**
        * **Propósito:** Es la interfaz REST pública de la aplicación. Se encarga de recibir las peticiones HTTP entrantes.
        * **Interacción:** Su única responsabilidad es recibir la petición y enviar la respuesta. Delega directamente al `Product Service` la gestión de la lógica de la petición. Esta capa no contiene lógica de negocio ni acceso directo a la capa de datos.

    * **Product Service (Capa de Servicio / Lógica de Negocio):**
        * **Propósito:** Contiene la lógica de negocio central de la aplicación. Gestiona las operaciones de los productos, aplica validaciones de negocio y puede combinar datos de múltiples orígenes.
        * **Interacción:** Delega al `Product Repository` para obtener los datos brutos. Recibe los datos del repositorio, aplica cualquier lógica de negocio necesaria y los pasa de vuelta al controlador.

    * **Product Repository (Capa de Acceso a Datos / Persistencia):**
        * **Propósito:** Encapsula toda la lógica relacionada con el almacenamiento y la recuperación de datos. En este caso específico, su única responsabilidad es leer un archivo JSON.
        * **Interacción:** Cuando la aplicación se inicia, esta capa lee el archivo JSON y carga los datos en memoria. Cuando el `Product Service` solicita los datos, esta capa los proporciona desde su caché interna.

    * **Modelo de Datos (Clases POJO Java):**
        * **Propósito:** Las clases POJO (Product, Comment, SellerInfo, etc.) definen la estructura de los datos. Son compartidas y utilizadas por todas las capas en el backend para representar la información de manera consistente.

    * **Persistencia de Datos (Archivo JSON):**
        Como ejemplo de la estructura del archivo JSON que se lee para obtener los datos de los productos:
        ```json
        {
          "id": 1,
          "name": "Samsung Galaxy A55 5G Dual SIM 256 GB azul oscuro 8 GB RAM",
          "description": {
              "shortDescription": "Dispositivo desbloqueado para que elijas tu operador. Memoria RAM de 8 GB y memoria interna de 256 GB.",
              "fullDescription": "Capacidad y eficiencia\nCon su procesador Snapdragon 7 Gen 2 y 8 GB de RAM, su computadora logrará un alto rendimiento con una alta velocidad de transmisión de contenido y ejecutará varias aplicaciones al mismo tiempo, sin demoras.\n\nCapacidad de almacenamiento ilimitado\nOlvídate de borrar. Con su memoria interna de 256 GB podrás descargar todos los archivos y aplicaciones que necesites, guardar fotos y almacenar tus películas, series y videos favoritos para reproducirlos cuando quieras."
          },
          "price": 439.0,
          "originalPrice": 599.0,
          "discountPercentage": 27.0,
          "images": [
            "[https://http2.mlstatic.com/D_NQ_NP_2X_800035-MLA81367078349_122024-F.webp](https://http2.mlstatic.com/D_NQ_NP_2X_800035-MLA81367078349_122024-F.webp)",
            "[https://http2.mlstatic.com/D_NQ_NP_2X_621964-MLA81364948571_122024-F.webp](https://http2.mlstatic.com/D_NQ_NP_2X_621964-MLA81364948571_122024-F.webp)",
            "[https://http2.mlstatic.com/D_NQ_NP_2X_777643-MLA75395342152_042024-F.webp](https://http2.mlstatic.com/D_NQ_NP_2X_777643-MLA75395342152_042024-F.webp)",
            "[https://http2.mlstatic.com/D_NQ_NP_2X_725539-MLA80825742603_112024-F.webp](https://http2.mlstatic.com/D_NQ_NP_2X_725539-MLA80825742603_112024-F.webp)",
            "[https://http2.mlstatic.com/D_NQ_NP_2X_832248-MLA81366749957_122024-F.webp](https://http2.mlstatic.com/D_NQ_NP_2X_832248-MLA81366749957_122024-F.webp)"
          ],
          "colors": [
              {
                  "name": "Azul Oscuro",
                  "code": "azul_oscuro",
                  "hexCode": "#00008B"
              },
              {
                  "name": "Negro",
                  "code": "negro",
                  "hexCode": "#000000"
              },
              {
                  "name": "Violeta",
                  "code": "violeta",
                  "hexCode": "#EE82EE"
              }
          ],
          "comments": [
              {
                  "user": "Alice C.",
                  "text": "Great product, exactly as described! The screen is vibrant.",
                  "rating": 5.0
              },
              {
                  "user": "Bob M.",
                  "text": "Fast shipping, good quality for the price. Battery life is decent.",
                  "rating": 4.0
              },
              {
                  "user": "Charlie D.",
                  "text": "It's okay, but I expected more features. Camera is good though.",
                  "rating": 3.0
              }
          ],
          "totalReviews": 3,
          "sellerInfo": {
              "name": "Samsung Tienda Oficial",
              "contact": "soporte@samsung.com",
              "rating": 4.8,
              "reputation": "Tienda Oficial",
              "logoUrl": "[https://http2.mlstatic.com/D_NQ_NP_789332-MLA74975093701_032024-G.webp](https://http2.mlstatic.com/D_NQ_NP_789332-MLA74975093701_032024-G.webp)"
          },
          "characteristics": [
                  {
                      "name": "Tamaño de la pantalla",
                      "value": "6.6 pulgadas"
                  },
                  {
                      "name": "Memoria RAM",
                      "value": "8 GB"
                  },
                  {
                      "name": "Memoria interna",
                      "value": "256 GB"
                  },
                  {
                      "name": "Cámara principal",
                      "value": "50 MP"
                  },
                  {
                      "name": "Cámara frontal",
                      "value": "32 MP"
                  },
                  {
                      "name": "Batería",
                      "value": "5000 mAh"
                  },
                  {
                      "name": "Desbloqueo",
                      "value": "Huella dactilar y reconocimiento facial"
                  },
                  {
                      "name": "Con NFC",
                      "value": "Sí"
                  }
            ]
          }
        ```

### Diagrama de Despliegue

Este diagrama ilustra cómo los componentes del sistema se distribuyen y se comunican en un entorno contenerizado con Docker.

<img src="https://github.com/user-attachments/assets/343721cd-64a5-4c13-844b-0043f3fa4f2e" alt="Diagrama de Despliegue" width="800"/>

**Justificación del Diagrama de Despliegue:**

* **Contenerización (Docker):** Los contenedores Docker encapsulan cada aplicación (Frontend Nginx y Backend Spring Boot) junto con sus dependencias, asegurando un entorno de ejecución aislado y portátil. Esto garantiza que la aplicación se ejecute de manera consistente en cualquier entorno que soporte Docker.
* **Red de Docker (app-network):** Docker Compose crea una red interna (`app-network`) que facilita la comunicación entre el frontend y el backend utilizando sus nombres de servicio (`frontend` y `backend`) como nombres de host. Esto simplifica la configuración de la comunicación entre servicios.
* **Exposición de Puertos:** Solo los puertos necesarios son expuestos al host (puerto 80 para el frontend, puerto 8080 para el backend), lo que permite que el cliente (navegador web) en el host acceda a la aplicación.
* **Gestión de Datos:** El archivo JSON que sirve como fuente de datos para el backend permanece encapsulado dentro del contenedor del backend, formando parte de su paquete de despliegue.

## 📦 Cómo Ejecutar el Proyecto

Este backend está diseñado para ejecutarse junto con el frontend en un entorno Docker Compose.

1.  **Clonar el repositorio:**
    ```bash
    git clone [URL_DE_TU_REPOSITORIO_BACKEND]
    cd Challenge_MeLi_BE
    ```
2.  **Asegurarse de tener Docker Desktop o Docker Engine instalado y en ejecución.**
3.  **Construir y levantar los contenedores con Docker Compose:**
    Desde el directorio raíz de tu proyecto Docker Compose (donde está el `docker-compose.yaml`), ejecuta:
    ```bash
    docker compose up --build -d
    ```
    Esto construirá la imagen del backend y la levantará.

4.  **Acceder al API desde tu máquina anfitriona (ej. con Postman):**
    Una vez que el contenedor del backend esté levantado y saludable (puedes verificar con `docker compose ps`), puedes acceder a su API en:
    `http://localhost:8080/api/products/1`

---

¡Gracias por revisar el proyecto!
