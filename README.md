
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


## Issues

Para solventar Breakpoint at "throw new SilentExitException()" in Eclipse + Spring Boot
Clic con el botón derecho en el proyecto en el Explorador de proyectos y elegir Debug As > Debug Configuratios.

Clic en Application > Pestaña Arguments.
En VM arguments introducimos el siguiente comando:

-Dspring.devtools.restart.enabled=false