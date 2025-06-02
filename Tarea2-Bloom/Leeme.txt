/**
 *
 * @author vicho (panzer69) y el joaco que ayudo algo
 */


###### Instrucciones de uso (si esta compilado) ######

1. Iniciar el ejecutable
2. especificar mediante la interfaz el achivo .txt con las preguntas de la prueba
3. Haga clic en "Iniciar prueba" para comenzar.
4. La aplicación mostrará la cantidad de ítems y tiempo estimado.
5. Avance o retroceda entre preguntas. Al final, haga clic en "Enviar respuestas".
6. Se mostrará un resumen con porcentajes por nivel de Bloom y tipo de ítem.
7. Puede revisar las respuestas y volver al resumen desde allí.
8. Para terminar el programa presione "Salir"

# Instrucciones de uso (si no esta compilado)#

1. Revisar si el programa no presenta errores en ninguna de las clases (para evitar problemas usar Apache NetBeans IDE V26 o superior)
2. Abrir el proyecto
3. Ejecutar el proyecto haciendo click en "Run project"
4. Estar atento a la consola "OUTPUT" en caso de codigos de error en la ejecucion
5. Al iniciar, se abrirá un selector de archivo. Seleccione un archivo de preguntas válido (ejemplo: sample_items.txt).
6. La aplicación mostrará la cantidad de ítems y tiempo estimado.
7. Haga clic en "Iniciar prueba" para comenzar.
8. Avance o retroceda entre preguntas. Al final, haga clic en "Enviar respuestas".
9. Se mostrará un resumen con porcentajes por nivel de Bloom y tipo de ítem.
10. Puede revisar las respuestas y volver al resumen desde allí.
11. Para terminar el programa presione "Salir"
12. si todo salio bien y el programa no se congela en la consola debera aparecer "BUILD SUCCESS"

# Formato del archivo de entrada (vale decir el archivo .txt en el que se hacen las preguntas)#

Formato por línea (separado por punto y coma):

pregunta;opción1|opción2|opción3|opción4;respuestaCorrecta;nivelBloom;tipoItem;tiempoEnSegundos

Ejemplo:
¿En que año comenzo la segunda guerra mundial?;1939|1944|1932|1941;1939;RECORDAR;MULTIPLE_CHOICE;30

# Supuestos #

- No se pueden repetir preguntas en el archivo.
- Se valida el formato del archivo.
- Solo se consideran preguntas de opción múltiple y verdadero/falso (peticion del instructivo).
