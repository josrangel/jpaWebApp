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

import javax.inject.Named;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/** Controlador que se utiliza en varias vistas. La anotación <code>@ViewScoped</code>
 * indica que los objetos se mantienen almacenados en el archivo de sesión mientras se
 * muestre la vista que está usando este bean. Al cambiar de vista, los datos se
 * pierden. La url debe recibir un parámetro con el id del Conocido. En caso de que no
 * haya id, la página se configura para dar de alta un nuevo conocido.
 * @author josrangel */
@Named(value = "ctrlConocido") @ViewScoped
public class CtrlConocido implements Serializable {
  private static final long serialVersionUID = 1L;
  @Inject
  private Errores errores;
  @Inject
  private EjbConocido ejb;
  private Long id;
  private Conocido modelo;
  @PostConstruct
  void init() {
    try {
      leeLlavePrimaria();
    } catch (NumberFormatException ex) {
      // Si no se puede recuperar la llave primaria, se considera que es una alta.
      id = null;
    }
    try {
      if (isNuevo()) {
        modelo = new Conocido();
      } else {
        this.modelo = ejb.busca(id);
      }
    } catch (Exception ex) {
      errores.procesa(ex);
    }
  }
  private void leeLlavePrimaria() throws NumberFormatException {
    final String parámetroId = FacesContext.getCurrentInstance().
        getExternalContext().getRequestParameterMap().get("id");
    id = new Long(parámetroId);
  }
  public boolean isNuevo() {
    return id == null;
  }
  public Conocido getModelo() {
    return modelo;
  }
  public String guarda() {
    try {
      if (isNuevo()) {
        ejb.agrega(modelo);
      } else {
        ejb.modifica(modelo);
      }
      return regresa();
    } catch (Exception ex) {
      errores.procesa(ex);
      return null;
    }
  }
  private String regresa() {
    /* La url devuelta hace que el navegador elimine de su historia la página actual
     * (en este caso la que manda guardar) y sea sustituida por la página index. Esto
     * hace que al recargar la página se vuelva a guardar la información, que al estar
     * dando de alta un nuevo conocido, ocasionaría registrar dos o más veces el mismo
     * registro. Prueba que sucede cuando el valor devuelto solo es "index" y después
     * de dar de alta un registro, recargas la página varias veces. */
    return "index?faces-redirect=true";
  }
}
