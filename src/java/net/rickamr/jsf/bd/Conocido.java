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

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** Clase conectada a la tabla Conocido. La tabla se crea automáticamente a partir de
 * los atributos definidos en esta clase, usando los mismos nombres. Esta clase se
 * crea con los siguientes pasos:
 * <ol>
 * <li>Selecciona el paquete donde quieras colocar la clase .</li>
 * <li>Menú File > New File... </li>
 * <li>
 * Categories: Persistence, File Types: Entity Class, Next>.
 * </li>
 * <li>
 * Class Name: Conocido, Package: net.rickamr.jsf.bd Primary Key Type: Long. Si no
 * has creado el archivo persistence.xml, hay una casilla de verificación que dice
 * "Create persistence unit" y la debes conservar seleccionada.
 * </li>
 * <li>
 * Si seleccionaste crear la unidad de persistencia, aparece una forma donde debes
 * seleccionar el data source. Introduce: java:comp/DefaultDataSource. Clic en Finish.
 * </li>
 * </ol>
 * <p>
 * <code>Serializable</code> indica que el objeto se puede almacenar y recuperar.
 * </p>
 * @author josrangel*/
@Entity
@Table(uniqueConstraints = {
  /* Esta restricción aborta cualquier operación que genere un
   * nombre duplicado. Algunos manejadores de base de datos utilizan el valor de
   * "name" en sus mensajes cuando detectan un nombre duplicado. Prueba insertando
   * datos con el nombre duplicado. */
  @UniqueConstraint(name = "CON_D_NOMBRE", columnNames = {"nombre"})})
public class Conocido implements Serializable {
  /** Constante que se utiliza cuando la clase es serializable. */
  private static final long serialVersionUID = 1L;
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  // Las siguientes anotaciones se explican en el la página sobre Bean Validation.
  @NotNull @Size(min = 1, max = 255)
  private String nombre;
  @NotNull @Size(min = 1, max = 255)
  // Se recomienda no usar acentos en los nombres de los campos de la base de datos.
  private String curso;
  @NotNull @Pattern(regexp = "\\d{8,14}") // 8 a 14 dígitos.
  private String telefono;
  private String salon;
  @Temporal(TemporalType.DATE)
  private Date fecha;
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public String getCurso() {
    return curso;
  }
  public void setCurso(String curso) {
    this.curso = curso;
  }
  public String getSalon() {
    return salon;
  }
  public void setSalon(String salon) {
    this.salon = salon;
  }
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
  public void setFecha(Date fecha){
    this.fecha = fecha;
  }
  public Date getFecha(){
    return fecha;
  }  
  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }
  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Conocido)) {
      return false;
    }
    Conocido other = (Conocido) object;
    if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(
            other.id))) {
      return false;
    }
    return true;
  }
  @Override
  public String toString() {
    return "net.rickamr.jsf.bd.Conocido[ id=" + id + " ]";
  }
}
