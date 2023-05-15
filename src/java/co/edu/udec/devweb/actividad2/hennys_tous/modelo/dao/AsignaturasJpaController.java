/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao;

import co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao.exceptions.NonexistentEntityException;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.dao.exceptions.PreexistingEntityException;
import co.edu.udec.devweb.actividad2.hennys_tous.modelo.entidades.Asignatura;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ADMIN
 */
public class AsignaturasJpaController implements Serializable {

    public AsignaturasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignaturas) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(asignaturas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignaturas(asignaturas.getId()) != null) {
                throw new PreexistingEntityException("Asignaturas " + asignaturas + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignaturas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            asignaturas = em.merge(asignaturas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignaturas.getId();
                if (findAsignaturas(id) == null) {
                    throw new NonexistentEntityException("The asignaturas with id " + id + " no longer exists.");
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
            Asignatura asignaturas;
            try {
                asignaturas = em.getReference(Asignatura.class, id);
                asignaturas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignaturas with id " + id + " no longer exists.", enfe);
            }
            em.remove(asignaturas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturasEntities() {
        return findAsignaturasEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturasEntities(int maxResults, int firstResult) {
        return findAsignaturasEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignatura.class));
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

    public Asignatura findAsignaturas(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
