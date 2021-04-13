/* Copyright 2021 josrangel
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use
 * this file except in compliance with the License. You may obtain a copy of the
 * License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
 * CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License. */
package net.rickamr.jsf.biblioteca;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/** Simplifica el manejo de mensajes.
 * @author josrangel */
@ApplicationScoped
public class Mensajes {
  /** Muestra un mensaje informativo.
   * @param idDeComponente id del componente de la página al que se asocia el mensaje,
   *                       o <code>null</code> si el mensaje es para toda la página.
   * @param texto          contenido del mensaje. */
  public void información(String idDeComponente, final String texto) {
    mensaje(idDeComponente, FacesMessage.SEVERITY_INFO, texto, null);
  }
  /** Muestra un mensaje de error.
   * @param idDeComponente id del componente de la página al que se asocia el mensaje,
   *                       o <code>null</code> si el mensaje es para toda la página.
   * @param texto          contenido del mensaje. */
  public void error(String idDeComponente, final String texto) {
    mensaje(idDeComponente, FacesMessage.SEVERITY_ERROR, texto, null);
  }
  /** Muestra un mensaje con el tipo, resumen y detalles indicados.
   * @param idDeComponente id del componente de la página al que se asocia el mensaje,
   *                       o <code>null</code> si el mensaje es para toda la página.
   * @param tipo           tipo del mensaje. Puede ser FacesMessage.SEVERITY_ERROR,
   *                       FacesMessage.SEVERITY_FATAL, FacesMessage.SEVERITY_INFO ó
   *                       FacesMessage.SEVERITY_WARN.
   * @param resumen        versión corta del mensaje.
   * @param detalle        versión larga del mensaje. */
  public void mensaje(String idDeComponente, FacesMessage.Severity tipo,
          String resumen, String detalle) {
    FacesContext.getCurrentInstance().
            addMessage(idDeComponente, new FacesMessage(tipo, resumen, detalle));
  }
}
