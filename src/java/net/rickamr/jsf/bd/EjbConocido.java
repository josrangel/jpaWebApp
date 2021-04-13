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
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/** Se encarga del acceso a la base de datos. La anotación <code>@Stateless</code>
 * indica que los objetos de la clase usan transacciones y no almacenan información de
 * estado, como pueden ser nombre, teléfono, etc.
 * <p>
 * La anotación <code>@Dependent</code> indica que el tiempo de vida de este
 * componente depende de quien lo inyecta. Por ejemplo, la clase
 * <code>CtrlConocidos</code>, con tiempo de vida RequestScoped, inyecta un objeto de
 * esta clase, que por consiguiente dura el tiempo de una solicitud http.
 * </p>
 * <p>
 * Esta clase se crea con los siguientes pasos:
 * </p>
 * <ol>
 * <li>Selecciona el paquete donde quieras colocar la clase.</li>
 * <li>Menú File -> New File...</li>
 * <li>Categories: Enterprise Java Beans, File Types: Session Bean, Next.</li>
 * <li>
 * EJB Name: EjbConocido, Package: net.rickamr.jsf.bd, Session Type: Stateless,
 * Finish.
 * </li>
 * </ol>
 * @author josrangel */
@Stateless @Dependent
public class EjbConocido {
  /** Busca en el proyecto una clase con el nombre <code>EntityManager</code> o una
   * clase con un atributo o método de tipo <code>EntityManager</code> con la
   * anotación <code>@Produces</code>. Los objetos <code>EntityManager</code> se usan
   * para realizar operaciones sobre la base de datos. */
  @Inject
  private EntityManager em;
  public List<Conocido> buscaInstancias() {
    /* Con el resultado de la consulta se llena una lista con objetos de la clase
     * "Conocido". */
    return em.createQuery("SELECT c FROM Conocido c ORDER BY c.nombre",
        Conocido.class).getResultList();
  }
  public Conocido busca(Long id) {
    return em.find(Conocido.class, id);
  }
  public void agrega(Conocido modelo) {
    em.persist(modelo); // Agrega el modelo a la base de datos.
  }
  public void modifica(Conocido modelo) {
    // Guarda los cambios al modelo.
    em.merge(modelo);
  }
  public void elimina(Conocido modelo) {
    // Busca el conocido en base a su id.
    final Conocido anterior = em.find(Conocido.class, modelo.getId());
    // Si el resultado es null, el conocido ya no está registrado.
    if (anterior != null) {
      // Pero si la referencia es diferente de null, hay que eliminar el objeto.
      em.remove(anterior);
    }
  }
}
