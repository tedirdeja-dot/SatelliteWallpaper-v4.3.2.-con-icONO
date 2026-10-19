# 4.3 — instrucciones vigentes (sustituyen las notas de versiones anteriores)

Disparadores: abrir la app (tras confirmar el intervalo) y desbloquear el teléfono.
Guardar una zona desde la app solicita también la imagen como parte de esa configuración.
Siempre se exige más del intervalo 0/15/30/60 minutos desde la última captura correcta.
Cambiar zona ya no elimina la hora de éxito. Si el intervalo no ha terminado, la zona
queda guardada para la siguiente solicitud válida: no hay temporizador de descarga.
Con 0 minutos puedes cambiar de zona y descargarla desde la app sin bloquear.
Volver al escritorio, encender la pantalla bloqueada, onResume y el tiempo por sí solo
no inician descargas. Girar la app no vuelve a solicitar la imagen.

Se conserva el JPG anterior hasta completar la sustitución en la carpeta temporal.
El encuadre se restablece al confirmar una captura de zona diferente, no antes.

Velocidad: el registro aportado de 4.2.2 muestra 10341 y 10350 ms en pantalla virtual.
Antes había 9 s fijos más 0,5 s de asentamiento. Ahora se comprueba desde los 3 s si
el documento, imágenes visibles y un mapa están presentes y hay una pausa en recursos
terminados; se requieren dos comprobaciones y un callback de estado visual.
Es una heurística: no garantiza que todas las teselas WebGL estén listas. En red lenta
puede esperar hasta 20 s para esa comprobación o conservar el JPG anterior con error.
No se ha medido la nueva velocidad en un teléfono ni confirmado ausencia de teselas
incompletas. Se debe comparar la misma zona y red, inspeccionando imagen y fecha.

Se mantienen pruebas JVM y lint en GitHub. XML/ZIP y puntos de llamada revisados
localmente; compilación y ejecución de esta versión pendientes en GitHub/dispositivo.
