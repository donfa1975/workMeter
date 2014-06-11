/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.exceptions.NonexistentEntityException;
import modelo.exceptions.PreexistingEntityException;
import modelo.exceptions.RollbackFailureException;

/**
 *
 * @author fabricio.diaz
 */
public class TblWMProcesosJpaController implements Serializable {

    public TblWMProcesosJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblWMProcesos tblWMProcesos) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (tblWMProcesos.getTblWMEmpleadosCollection() == null) {
            tblWMProcesos.setTblWMEmpleadosCollection(new ArrayList<TblWMEmpleados>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<TblWMEmpleados> attachedTblWMEmpleadosCollection = new ArrayList<TblWMEmpleados>();
            for (TblWMEmpleados tblWMEmpleadosCollectionTblWMEmpleadosToAttach : tblWMProcesos.getTblWMEmpleadosCollection()) {
                tblWMEmpleadosCollectionTblWMEmpleadosToAttach = em.getReference(tblWMEmpleadosCollectionTblWMEmpleadosToAttach.getClass(), tblWMEmpleadosCollectionTblWMEmpleadosToAttach.getIdEmpleado());
                attachedTblWMEmpleadosCollection.add(tblWMEmpleadosCollectionTblWMEmpleadosToAttach);
            }
            tblWMProcesos.setTblWMEmpleadosCollection(attachedTblWMEmpleadosCollection);
            em.persist(tblWMProcesos);
            for (TblWMEmpleados tblWMEmpleadosCollectionTblWMEmpleados : tblWMProcesos.getTblWMEmpleadosCollection()) {
                TblWMProcesos oldIdprocesoOfTblWMEmpleadosCollectionTblWMEmpleados = tblWMEmpleadosCollectionTblWMEmpleados.getIdproceso();
                tblWMEmpleadosCollectionTblWMEmpleados.setIdproceso(tblWMProcesos);
                tblWMEmpleadosCollectionTblWMEmpleados = em.merge(tblWMEmpleadosCollectionTblWMEmpleados);
                if (oldIdprocesoOfTblWMEmpleadosCollectionTblWMEmpleados != null) {
                    oldIdprocesoOfTblWMEmpleadosCollectionTblWMEmpleados.getTblWMEmpleadosCollection().remove(tblWMEmpleadosCollectionTblWMEmpleados);
                    oldIdprocesoOfTblWMEmpleadosCollectionTblWMEmpleados = em.merge(oldIdprocesoOfTblWMEmpleadosCollectionTblWMEmpleados);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTblWMProcesos(tblWMProcesos.getIdProceso()) != null) {
                throw new PreexistingEntityException("TblWMProcesos " + tblWMProcesos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblWMProcesos tblWMProcesos) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TblWMProcesos persistentTblWMProcesos = em.find(TblWMProcesos.class, tblWMProcesos.getIdProceso());
            Collection<TblWMEmpleados> tblWMEmpleadosCollectionOld = persistentTblWMProcesos.getTblWMEmpleadosCollection();
            Collection<TblWMEmpleados> tblWMEmpleadosCollectionNew = tblWMProcesos.getTblWMEmpleadosCollection();
            Collection<TblWMEmpleados> attachedTblWMEmpleadosCollectionNew = new ArrayList<TblWMEmpleados>();
            for (TblWMEmpleados tblWMEmpleadosCollectionNewTblWMEmpleadosToAttach : tblWMEmpleadosCollectionNew) {
                tblWMEmpleadosCollectionNewTblWMEmpleadosToAttach = em.getReference(tblWMEmpleadosCollectionNewTblWMEmpleadosToAttach.getClass(), tblWMEmpleadosCollectionNewTblWMEmpleadosToAttach.getIdEmpleado());
                attachedTblWMEmpleadosCollectionNew.add(tblWMEmpleadosCollectionNewTblWMEmpleadosToAttach);
            }
            tblWMEmpleadosCollectionNew = attachedTblWMEmpleadosCollectionNew;
            tblWMProcesos.setTblWMEmpleadosCollection(tblWMEmpleadosCollectionNew);
            tblWMProcesos = em.merge(tblWMProcesos);
            for (TblWMEmpleados tblWMEmpleadosCollectionOldTblWMEmpleados : tblWMEmpleadosCollectionOld) {
                if (!tblWMEmpleadosCollectionNew.contains(tblWMEmpleadosCollectionOldTblWMEmpleados)) {
                    tblWMEmpleadosCollectionOldTblWMEmpleados.setIdproceso(null);
                    tblWMEmpleadosCollectionOldTblWMEmpleados = em.merge(tblWMEmpleadosCollectionOldTblWMEmpleados);
                }
            }
            for (TblWMEmpleados tblWMEmpleadosCollectionNewTblWMEmpleados : tblWMEmpleadosCollectionNew) {
                if (!tblWMEmpleadosCollectionOld.contains(tblWMEmpleadosCollectionNewTblWMEmpleados)) {
                    TblWMProcesos oldIdprocesoOfTblWMEmpleadosCollectionNewTblWMEmpleados = tblWMEmpleadosCollectionNewTblWMEmpleados.getIdproceso();
                    tblWMEmpleadosCollectionNewTblWMEmpleados.setIdproceso(tblWMProcesos);
                    tblWMEmpleadosCollectionNewTblWMEmpleados = em.merge(tblWMEmpleadosCollectionNewTblWMEmpleados);
                    if (oldIdprocesoOfTblWMEmpleadosCollectionNewTblWMEmpleados != null && !oldIdprocesoOfTblWMEmpleadosCollectionNewTblWMEmpleados.equals(tblWMProcesos)) {
                        oldIdprocesoOfTblWMEmpleadosCollectionNewTblWMEmpleados.getTblWMEmpleadosCollection().remove(tblWMEmpleadosCollectionNewTblWMEmpleados);
                        oldIdprocesoOfTblWMEmpleadosCollectionNewTblWMEmpleados = em.merge(oldIdprocesoOfTblWMEmpleadosCollectionNewTblWMEmpleados);
                    }
                }
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
                Integer id = tblWMProcesos.getIdProceso();
                if (findTblWMProcesos(id) == null) {
                    throw new NonexistentEntityException("The tblWMProcesos with id " + id + " no longer exists.");
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
            TblWMProcesos tblWMProcesos;
            try {
                tblWMProcesos = em.getReference(TblWMProcesos.class, id);
                tblWMProcesos.getIdProceso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblWMProcesos with id " + id + " no longer exists.", enfe);
            }
            Collection<TblWMEmpleados> tblWMEmpleadosCollection = tblWMProcesos.getTblWMEmpleadosCollection();
            for (TblWMEmpleados tblWMEmpleadosCollectionTblWMEmpleados : tblWMEmpleadosCollection) {
                tblWMEmpleadosCollectionTblWMEmpleados.setIdproceso(null);
                tblWMEmpleadosCollectionTblWMEmpleados = em.merge(tblWMEmpleadosCollectionTblWMEmpleados);
            }
            em.remove(tblWMProcesos);
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

    public List<TblWMProcesos> findTblWMProcesosEntities() {
        return findTblWMProcesosEntities(true, -1, -1);
    }

    public List<TblWMProcesos> findTblWMProcesosEntities(int maxResults, int firstResult) {
        return findTblWMProcesosEntities(false, maxResults, firstResult);
    }

    private List<TblWMProcesos> findTblWMProcesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblWMProcesos.class));
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

    public TblWMProcesos findTblWMProcesos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblWMProcesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblWMProcesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblWMProcesos> rt = cq.from(TblWMProcesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
