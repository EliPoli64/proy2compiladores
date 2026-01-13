# Documentación del Proyecto: Analizador Léxico

Este proyecto implementa un analizador léxico utilizando **JFlex** y **Java CUP**. El sistema lee un archivo fuente, identifica los tokens definidos en la especificación léxica, y genera un reporte de salida.

## Estructura del Proyecto

*   **`src/`**: Código fuente (`.java`, `.flex`, `.cup`).
*   **`lib/`**: Librerías y herramientas necesarias (`jflex`, `cup`).
*   **`bin/`**: Archivos de clase compilados (`.class`).
*   **`test_input.txt`**: Archivo de prueba con código ejemplo.
*   **`salida.txt`**: Archivo generado con los resultados del análisis.

## Requisitos

*   **JDK**: Debe estar instalado y configurado en el PATH del sistema.

> **Nota**: No es necesario instalar JFlex o CUP en el sistema, ya que el proyecto utiliza las versiones incluidas en la carpeta `lib/`.

## Instrucciones de Compilación

### 1. Generar Código Fuente (Lexer y Parser)

Genera las clases `LexerProyUno.java`, `parser.java` y `sym.java` a partir de las especificaciones:

```powershell
java -jar lib/jflex-full-1.9.1.jar src/jflex.flex

java -jar lib/java-cup-11b.jar -destdir src -parser parser -symbols sym src/cup.cup
```

### 2. Compilar JAR

Compila el código fuente y empaqueta todo en un .jar:

```powershell
mkdir bin

javac -d bin -cp "lib/*" src/*.java
# Si jar está en el path del sistema:
jar cvfm proy1compiladores.jar manifest.txt -C bin .
# Si jar no está en el path del sistema, se puede usar esto en Windows:
"C:\Program Files\Java\jdk-21\bin\jar.exe" cfm proy1compiladores.jar manifest.txt -C bin .
```

## Instrucciones de Ejecución

Para ejecutar el analizador, ejecute este comando:

### Sintaxis
```powershell
java -jar proy1compiladores.jar <nombre del archivo a tokenizar>  
```

### Ejemplo de Uso
Para analizar el archivo de prueba `test_input.txt`:

```powershell
java -jar proy1compiladores.jar test_input.txt
```

## Salida y Resultados

**`salida.txt`** contendrá la secuencia de tokens. El formato es:
    ```text
    Token - Lexema (si aplica) - Linea - Columna   
    ```
    *Ejemplo:*
    ```text
    STRING_LITERAL       Stock insuficiente   29         37        
    ENDL                                      29         39        
    RETURN                                    30         13        
    BOOLEAN_LITERAL      false                30         20 
    ```
