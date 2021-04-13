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

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Indica como crear una instancia de Entity Manager. Se usa porque en la clase
 * <code>EjbConocido</code> se tiene la anotación <br/>
 * <code>@Inject<br />
 * private EntityManager em;<br />
 * </code> donde solicita un <code>EntityManager</code> que se enlaza con la variable
 * <var>em</var>. Como CDI encuentra la anotación <code>@Produces</code>, decide usar
 * la declaración de esta clase.
 * <p>
 * La anotación <code>@Dependent</code> permite que esta clase sea analizada por CDI.
 * </p>
 * @author josrangel*/
@Dependent
public class Conexion {
  /* @Produces indica que se puede usar en @Inject. @PersistenceContext indica que usa
   * transacciones y el archivo persistence.xml */
  @Produces @PersistenceContext
  EntityManager em;
}
