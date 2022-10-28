
# crud-superheroes Project

    Este es un proyecto inicial para Java 11 con Gradle.
    El proyecto incluye Gradle wrapper, por lo que no es necesario instalar Gradle para ejecutar la aplicación.
## Installation
Eclipse

```bash
    1. Abrir Eclipse y seleccionar Archivo > Importar.
    2. En el asistente de importación, elegir Gradle > Proyecto Gradle existente y luego haga clic en Siguiente.
    3. Seleccionar el directorio java-gradle-starter-project como directorio raíz del proyecto.
    4. Finalizar para completar la importación.
    5. Seleccionar Proyecto > Propiedades. En Java Build Path, en la pestaña Bibliotecas, Modulepath esté configurado en JRE System Library (JavaSE-11). 
        En Java Compiler, asegúrarse de que esté seleccionada la casilla de verificación Usar conformidad del entorno de ejecución 'JavaSE-11' en la 'Ruta de compilación de Java'.
    6. Clic con el botón derecho en el proyecto en el Explorador de proyectos o el Explorador de paquetes y elegir Gradle > Refresh Gradle project.
    7. Abrir Gradle Tasks con Window > Show View > Other... > Gradle > Gradle Tasks.
    8. En Gradle Tasks view, haga doble clic en copyNatives en java-gradle-starter-project > build. Esto descomprimirá las dependencias de la biblioteca nativa en $USER_HOME/.arcgis.
    9. En Gradle Tasks view, haga doble clic en ejecutar en java-gradle-starter-project > aplicación para ejecutar la aplicación.    
```
## Configurar compilación
     Ir a "Run configurations" o "Debug configurations" en "Java application" se habrá creado una configuración seleccionarla. "Debug".
    
## Issues
    Para solventar Breakpoint at "throw new SilentExitException()" in Eclipse + Spring Boot
    Clic con el botón derecho en el proyecto en el Explorador de proyectos y elegir Debug As > Debug Configuratios.

    Clic en Application > Pestaña Arguments.
    En VM arguments introducimos el siguiente comando:

    -Dspring.devtools.restart.enabled=false
    
## Configurar conexión de Spring Boot con H2
    En application.properties
    
    ```bash
        server.port: 8090

        spring.application.name: jpastreamer-example
        spring.datasource.url=jdbc:h2:file:/data/refactorizando/h2
        #spring.datasource.url: jdbc:h2:mem:test;DB_CLOSE_DELAY=-1
        spring.datasource.url: jdbc:h2:mem:testdb
        spring.jpa.defer-datasource-initialization=true
        spring.datasource.driverClassName: org.h2.Driver
        spring.datasource.username: sa
        spring.datasource.password: password
        spring.jpa.database-platform: org.hibernate.dialect.H2Dialect
        spring.h2.console.enabled: true
        spring.h2.console.path=/h2-console

        spring.jpa.show-sql: true
        spring.jpa.properties.hibernate.format_sql: true

        spring:
          jpa:
            database-platform: org.hibernate.dialect.H2Dialect
            properties:
              hibernate:
                show_sql: true
                format_sql: true
                enable_lazy_load_no_trans: true

        logging:
          level:
            root: DEBUG
            ```
            En build.gradle
                runtimeOnly 'com.h2database:h2'
            En \src\main\resources
                En esta ruta tenemos el script data.sql con los inserts necesarios para tener datos en la BBDD. Se ejecutará automáticamente al arrancar el servicio.
