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

import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/** @author josrangel */
@Named(value = "ctrlConocidos") @RequestScoped
public class CtrlConocidos {
  @Inject
  private Errores errores;
  @Inject
  private EjbConocido ejb;
  private List<Conocido> instancias;
  @PostConstruct
  /* @PostConstruct indica que el método se ejecuta después de crear el objeto y
   * realizar todos los inject. Funciona casi como un constructor. */
  void init() {
    try {
      instancias = ejb.buscaInstancias();
    } catch (Exception ex) {
      errores.procesa(ex);
    }
  }
  public List<Conocido> getInstancias() {
    return instancias;
  }
  /** Elimina el modelo recibido de la base de datos y devuelve el nombre de la página
   * a mostrar.
   * @param modelo el conocido que se elimina de la base de datos.
   * @return nombre de la página a mostrar. */
  public String elimina(final Conocido modelo) {
    try {
      if (modelo != null) {
        ejb.elimina(modelo);
        /* La url devuelta hace que el navegador elimine de su historia la página
         * actual (en este caso la que hace el borrado) y sea sustituida por la página
         * index. */
        return "index?faces-redirect=true";
      }
    } catch (Exception ex) {
      errores.procesa(ex);
    }
    return null;
  }
}
