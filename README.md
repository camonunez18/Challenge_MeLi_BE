# Challenge_MeLi_BE
This repository contains the technical challenge for Mercado Libre. This repository contains the back-end source.

## Techinal Diagrams
### Component Diagram

<img width="3840" height="3152" alt="Image" src="https://github.com/user-attachments/assets/08458ce2-9760-454e-8000-81a88f753469" />

1. Cliente (Navegador Web) y Frontend Web: It is the presentation layer.
2. Backend (Microservicio Spring Boot): It has layers:
   
   * **Product Controller (Presentation layer):**   
      *Purpose:* Public REST interface, receive the HTTP request.
   
      *Interaction:* His own responsability is receipt the request and send the response. Delegate directly to the Product Service the manage of the request logic. This not contains the business logic or data layer access.
   
   * **Product Service (Service Layer/Business Logic):**
   
     *Purpose:* Contains the business logic for the applicaiton. Manage the operations, this can apply business validations, combine data from multiple origins, etc.

     *Interaction:* Delegate to the Product Repository to get the data. Receive data from the repository, aplly any necessary business logic and passes them back to the controller.
   
   * **Product Repository (Data Access Layer/Persistence):**
     
     *Purpose:* Encapsulates all the logic related with the data store and data recovery. In this case, the only responsability is to read the JSON file.
     
     *Interaction:* When the application starts, this layer reads the JSON file and then load them in memory. When Product Service ask for the data, this layer provides them from its internal cache.
   
   * **Modelo de Datos (POJO Classes Java):**
   
     *Purpose:* Th POJO classes (Product, Comment, etc.) define the data structure. They are shared and used by all the layers in the backend to represent the information.
   
   * **Persistencia de Datos (Archivo JSON):**
   
      As an example of the JSON file.
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
           "http://googleusercontent.com/image_collection/image_retrieval/12488350602664173885",
           "http://googleusercontent.com/image_collection/image_retrieval/14195687217179486172",
           "http://googleusercontent.com/image_collection/image_retrieval/14195687217179486172",
           "http://googleusercontent.com/image_collection/image_retrieval/14195687217179486172",
           "http://googleusercontent.com/image_collection/image_retrieval/14195687217179486172"
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
           "logoUrl": "https://via.placeholder.com/80?text=Samsung+Logo"
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
### Why Layer Architecture:

**Separation of Concerns:** Each layer has a unique and well-defined responsibility. The controller focuses on HTTP, the service on business logic, and the repository on data access.

**Modularity and Maintainability:** Changes in one layer (e.g., switching from JSON to a database) only affect the adjacent layer (the repository) and not the entire system. This makes the code easier to understand, modify, and debug.

**Testability:** Each layer can be tested independently. For instance, the ProductService can be tested by mocking the ProductRepository without needing a real JSON file.

**Scalability:** In the future, if the business logic becomes complex or if multiple data sources are required, this structure facilitates expansion.

### Deployment Diagram

<img width="3840" height="591" alt="Image" src="https://github.com/user-attachments/assets/343721cd-64a5-4c13-844b-0043f3fa4f2e" />

The justification for this diagram remains the same as before. Docker containers encapsulate applications, providing isolation and portability. The Docker network (app-network) facilitates communication between the frontend and the backend using their service names. The JSON file remains part of the backend's container package.

