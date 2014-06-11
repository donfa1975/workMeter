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
public class TblWMResumenUsoJpaController implements Serializable {

    public TblWMResumenUsoJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblWMResumenUso tblWMResumenUso) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tblWMResumenUso);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTblWMResumenUso(tblWMResumenUso.getIdReporte()) != null) {
                throw new PreexistingEntityException("TblWMResumenUso " + tblWMResumenUso + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblWMResumenUso tblWMResumenUso) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tblWMResumenUso = em.merge(tblWMResumenUso);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblWMResumenUso.getIdReporte();
                if (findTblWMResumenUso(id) == null) {
                    throw new NonexistentEntityException("The tblWMResumenUso with id " + id + " no longer exists.");
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
            TblWMResumenUso tblWMResumenUso;
            try {
                tblWMResumenUso = em.getReference(TblWMResumenUso.class, id);
                tblWMResumenUso.getIdReporte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblWMResumenUso with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblWMResumenUso);
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

    public List<TblWMResumenUso> findTblWMResumenUsoEntities() {
        return findTblWMResumenUsoEntities(true, -1, -1);
    }

    public List<TblWMResumenUso> findTblWMResumenUsoEntities(int maxResults, int firstResult) {
        return findTblWMResumenUsoEntities(false, maxResults, firstResult);
    }

    private List<TblWMResumenUso> findTblWMResumenUsoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblWMResumenUso.class));
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

    public TblWMResumenUso findTblWMResumenUso(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblWMResumenUso.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblWMResumenUsoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblWMResumenUso> rt = cq.from(TblWMResumenUso.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
