/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import modelo.exceptions.NonexistentEntityException;
import modelo.exceptions.PreexistingEntityException;
import modelo.exceptions.RollbackFailureException;

/**
 *
 * @author fabricio.diaz
 */
public class TblWMEmpleadosJpaController implements Serializable {

    public TblWMEmpleadosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblWMEmpleados tblWMEmpleados) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TblWMProcesos idproceso = tblWMEmpleados.getIdproceso();
            if (idproceso != null) {
                idproceso = em.getReference(idproceso.getClass(), idproceso.getIdProceso());
                tblWMEmpleados.setIdproceso(idproceso);
            }
            em.persist(tblWMEmpleados);
            if (idproceso != null) {
                idproceso.getTblWMEmpleadosCollection().add(tblWMEmpleados);
                idproceso = em.merge(idproceso);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTblWMEmpleados(tblWMEmpleados.getIdEmpleado()) != null) {
                throw new PreexistingEntityException("TblWMEmpleados " + tblWMEmpleados + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblWMEmpleados tblWMEmpleados) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TblWMEmpleados persistentTblWMEmpleados = em.find(TblWMEmpleados.class, tblWMEmpleados.getIdEmpleado());
            TblWMProcesos idprocesoOld = persistentTblWMEmpleados.getIdproceso();
            TblWMProcesos idprocesoNew = tblWMEmpleados.getIdproceso();
            if (idprocesoNew != null) {
                idprocesoNew = em.getReference(idprocesoNew.getClass(), idprocesoNew.getIdProceso());
                tblWMEmpleados.setIdproceso(idprocesoNew);
            }
            tblWMEmpleados = em.merge(tblWMEmpleados);
            if (idprocesoOld != null && !idprocesoOld.equals(idprocesoNew)) {
                idprocesoOld.getTblWMEmpleadosCollection().remove(tblWMEmpleados);
                idprocesoOld = em.merge(idprocesoOld);
            }
            if (idprocesoNew != null && !idprocesoNew.equals(idprocesoOld)) {
                idprocesoNew.getTblWMEmpleadosCollection().add(tblWMEmpleados);
                idprocesoNew = em.merge(idprocesoNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblWMEmpleados.getIdEmpleado();
                if (findTblWMEmpleados(id) == null) {
                    throw new NonexistentEntityException("The tblWMEmpleados with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TblWMEmpleados tblWMEmpleados;
            try {
                tblWMEmpleados = em.getReference(TblWMEmpleados.class, id);
                tblWMEmpleados.getIdEmpleado();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblWMEmpleados with id " + id + " no longer exists.", enfe);
            }
            TblWMProcesos idproceso = tblWMEmpleados.getIdproceso();
            if (idproceso != null) {
                idproceso.getTblWMEmpleadosCollection().remove(tblWMEmpleados);
                idproceso = em.merge(idproceso);
            }
            em.remove(tblWMEmpleados);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TblWMEmpleados> findTblWMEmpleadosEntities() {
        return findTblWMEmpleadosEntities(true, -1, -1);
    }

    public List<TblWMEmpleados> findTblWMEmpleadosEntities(int maxResults, int firstResult) {
        return findTblWMEmpleadosEntities(false, maxResults, firstResult);
    }

    private List<TblWMEmpleados> findTblWMEmpleadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblWMEmpleados.class));
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

    public TblWMEmpleados findTblWMEmpleados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblWMEmpleados.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblWMEmpleadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblWMEmpleados> rt = cq.from(TblWMEmpleados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
