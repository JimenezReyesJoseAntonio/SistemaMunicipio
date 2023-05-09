/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.Area;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import modelo.Empleado;
import modelo.Nomina;

/**
 *
 * @author josej
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Empleado empleado) {
        if (empleado.getAreaCollection() == null) {
            empleado.setAreaCollection(new ArrayList<Area>());
        }
        if (empleado.getNominaCollection() == null) {
            empleado.setNominaCollection(new ArrayList<Nomina>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Area> attachedAreaCollection = new ArrayList<Area>();
            for (Area areaCollectionAreaToAttach : empleado.getAreaCollection()) {
                areaCollectionAreaToAttach = em.getReference(areaCollectionAreaToAttach.getClass(), areaCollectionAreaToAttach.getIdarea());
                attachedAreaCollection.add(areaCollectionAreaToAttach);
            }
            empleado.setAreaCollection(attachedAreaCollection);
            Collection<Nomina> attachedNominaCollection = new ArrayList<Nomina>();
            for (Nomina nominaCollectionNominaToAttach : empleado.getNominaCollection()) {
                nominaCollectionNominaToAttach = em.getReference(nominaCollectionNominaToAttach.getClass(), nominaCollectionNominaToAttach.getIdnomina());
                attachedNominaCollection.add(nominaCollectionNominaToAttach);
            }
            empleado.setNominaCollection(attachedNominaCollection);
            em.persist(empleado);
            for (Area areaCollectionArea : empleado.getAreaCollection()) {
                Empleado oldRepresentanteOfAreaCollectionArea = areaCollectionArea.getRepresentante();
                areaCollectionArea.setRepresentante(empleado);
                areaCollectionArea = em.merge(areaCollectionArea);
                if (oldRepresentanteOfAreaCollectionArea != null) {
                    oldRepresentanteOfAreaCollectionArea.getAreaCollection().remove(areaCollectionArea);
                    oldRepresentanteOfAreaCollectionArea = em.merge(oldRepresentanteOfAreaCollectionArea);
                }
            }
            for (Nomina nominaCollectionNomina : empleado.getNominaCollection()) {
                Empleado oldIdempleadoOfNominaCollectionNomina = nominaCollectionNomina.getIdempleado();
                nominaCollectionNomina.setIdempleado(empleado);
                nominaCollectionNomina = em.merge(nominaCollectionNomina);
                if (oldIdempleadoOfNominaCollectionNomina != null) {
                    oldIdempleadoOfNominaCollectionNomina.getNominaCollection().remove(nominaCollectionNomina);
                    oldIdempleadoOfNominaCollectionNomina = em.merge(oldIdempleadoOfNominaCollectionNomina);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getIdempleado());
            Collection<Area> areaCollectionOld = persistentEmpleado.getAreaCollection();
            Collection<Area> areaCollectionNew = empleado.getAreaCollection();
            Collection<Nomina> nominaCollectionOld = persistentEmpleado.getNominaCollection();
            Collection<Nomina> nominaCollectionNew = empleado.getNominaCollection();
            Collection<Area> attachedAreaCollectionNew = new ArrayList<Area>();
            for (Area areaCollectionNewAreaToAttach : areaCollectionNew) {
                areaCollectionNewAreaToAttach = em.getReference(areaCollectionNewAreaToAttach.getClass(), areaCollectionNewAreaToAttach.getIdarea());
                attachedAreaCollectionNew.add(areaCollectionNewAreaToAttach);
            }
            areaCollectionNew = attachedAreaCollectionNew;
            empleado.setAreaCollection(areaCollectionNew);
            Collection<Nomina> attachedNominaCollectionNew = new ArrayList<Nomina>();
            for (Nomina nominaCollectionNewNominaToAttach : nominaCollectionNew) {
                nominaCollectionNewNominaToAttach = em.getReference(nominaCollectionNewNominaToAttach.getClass(), nominaCollectionNewNominaToAttach.getIdnomina());
                attachedNominaCollectionNew.add(nominaCollectionNewNominaToAttach);
            }
            nominaCollectionNew = attachedNominaCollectionNew;
            empleado.setNominaCollection(nominaCollectionNew);
            empleado = em.merge(empleado);
            for (Area areaCollectionOldArea : areaCollectionOld) {
                if (!areaCollectionNew.contains(areaCollectionOldArea)) {
                    areaCollectionOldArea.setRepresentante(null);
                    areaCollectionOldArea = em.merge(areaCollectionOldArea);
                }
            }
            for (Area areaCollectionNewArea : areaCollectionNew) {
                if (!areaCollectionOld.contains(areaCollectionNewArea)) {
                    Empleado oldRepresentanteOfAreaCollectionNewArea = areaCollectionNewArea.getRepresentante();
                    areaCollectionNewArea.setRepresentante(empleado);
                    areaCollectionNewArea = em.merge(areaCollectionNewArea);
                    if (oldRepresentanteOfAreaCollectionNewArea != null && !oldRepresentanteOfAreaCollectionNewArea.equals(empleado)) {
                        oldRepresentanteOfAreaCollectionNewArea.getAreaCollection().remove(areaCollectionNewArea);
                        oldRepresentanteOfAreaCollectionNewArea = em.merge(oldRepresentanteOfAreaCollectionNewArea);
                    }
                }
            }
            for (Nomina nominaCollectionOldNomina : nominaCollectionOld) {
                if (!nominaCollectionNew.contains(nominaCollectionOldNomina)) {
                    nominaCollectionOldNomina.setIdempleado(null);
                    nominaCollectionOldNomina = em.merge(nominaCollectionOldNomina);
                }
            }
            for (Nomina nominaCollectionNewNomina : nominaCollectionNew) {
                if (!nominaCollectionOld.contains(nominaCollectionNewNomina)) {
                    Empleado oldIdempleadoOfNominaCollectionNewNomina = nominaCollectionNewNomina.getIdempleado();
                    nominaCollectionNewNomina.setIdempleado(empleado);
                    nominaCollectionNewNomina = em.merge(nominaCollectionNewNomina);
                    if (oldIdempleadoOfNominaCollectionNewNomina != null && !oldIdempleadoOfNominaCollectionNewNomina.equals(empleado)) {
                        oldIdempleadoOfNominaCollectionNewNomina.getNominaCollection().remove(nominaCollectionNewNomina);
                        oldIdempleadoOfNominaCollectionNewNomina = em.merge(oldIdempleadoOfNominaCollectionNewNomina);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getIdempleado();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getIdempleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            Collection<Area> areaCollection = empleado.getAreaCollection();
            for (Area areaCollectionArea : areaCollection) {
                areaCollectionArea.setRepresentante(null);
                areaCollectionArea = em.merge(areaCollectionArea);
            }
            Collection<Nomina> nominaCollection = empleado.getNominaCollection();
            for (Nomina nominaCollectionNomina : nominaCollection) {
                nominaCollectionNomina.setIdempleado(null);
                nominaCollectionNomina = em.merge(nominaCollectionNomina);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
