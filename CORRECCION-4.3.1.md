# 4.3.1 — cero minutos y eventos concurrentes

0 minutos permite actualizar en cada evento, incluso si coincide con el milisegundo
de la última actualización. 15/30/60 conservan el límite estrictamente mayor.
Solo UnlockService recibe USER_PRESENT y SCREEN_OFF; el fondo animado inicia dicho
servicio y recibe CACHE_UPDATED. Se elimina el receptor duplicado del fondo y el
estado armed que podía impedir nuevos desbloqueos si se perdía SCREEN_OFF.
Una solicitud nueva recibida mientras termina/cancela la captura se conserva y se
atiende al liberarse, comprobando de nuevo pantalla desbloqueada e intervalo.
Al apagar la pantalla se elimina la solicitud pendiente: no descarga bloqueado.
Solicitudes concurrentes se agrupan en la última; no hay descargas simultáneas.
Si no hay red o el mapa falla se conserva la imagen anterior; no hay reintento periódico.

Se mantienen apertura/configuración de la app y desbloqueo como entradas.
El servicio debe estar activo: si Android lo detiene no se pueden recibir los eventos.

La captura usa WebView: página, scripts, teselas, renderizado, captura y JPEG. No es
una petición HTTP a un JPG. 4.3 conserva un mínimo de comprobación de 3 s, pausa de
recursos de 1,5 s, dos muestras separadas por 0,5 s y 0,5 s tras limpiar la interfaz.
Esto supone aproximadamente 4 s después de cargar la página en el caso favorable,
más composición/captura. Si no está lista puede tardar más. Cambiar el intervalo
a 0 no acelera la carga: solo permite solicitarla en cada desbloqueo.
El registro visible añade tiempo hasta página cargada y mapa preparado, más el total.
No se han añadido nuevas esperas ni cambiado la resolución en esta corrección.

Validación local: revisión de entradas, XML y ZIP. Test JVM de cero actualizado,
pendiente de ejecutar en GitHub junto con lint y compilación. Sin prueba en teléfono.
Probar: 0 min, desbloquear dos veces tras completar cada captura; bloquear durante
una captura y desbloquear inmediatamente; mantenerlo desbloqueado y comprobar que
no hay actualizaciones espontáneas. Revisar después 15/30/60 minutos.
