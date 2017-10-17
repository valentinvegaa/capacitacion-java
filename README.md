# capacitacion-java

[GET] webapi/resource/time?value=hh:mm:ss

- recibe una hora en formato hh:mm:ss y devuelve la fecha en formato UTC ISO DATE


[POST] webapi/resource/word

- recibe un Json en el formato {"data": "hola"} de largo 4 y retorna un Json 
en formato {"code":"00","data":"HOLA","description":"OK"} con la data en mayusculas.