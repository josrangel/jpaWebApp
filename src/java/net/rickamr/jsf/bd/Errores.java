/* Copyright 2021 josrangel
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. */
package net.rickamr.jsf.bd;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import net.rickamr.jsf.biblioteca.Mensajes;

/** Simplifica el manejo de errores. Como es @ApplicationScoped, se usa una sola
 * instancia en toda la aplicación. No se añade al paquete
 * <code>net.rickamr.jsf.biblioteca</code> porque en los ejemplos que siguen se le
 * hacen cambios.
 * @author josrangelR*/
@ApplicationScoped
public class Errores {
  @Inject
  private Mensajes mensajes;
  /** Analiza una excepción para poder mostrar el mensaje de error que lleva.
   * Normalmente cuendo un EJB detecta un error, lanza otras excepciones y guarda la
   * causa original, la cual necesitamos para ver el mensaje que manda el manejador
   * de base de datos para ver si lo podemos usar más adelante para marcar errores.
   * @param ex excepción que describe un error. */
  public void procesa(Throwable ex) {
    Throwable causa = ex.getCause();
    while (causa != null && causa != ex) {
      ex = causa;
      causa = ex.getCause();
    }
    final String mensaje = ex.getLocalizedMessage();
    if (mensaje == null || mensaje.isEmpty()) {
      mensajes.error(null, ex.toString());
    } else {
      mensajes.error(null, ex.getLocalizedMessage());
    }
  }
}
