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
public class TblWMResumenOtrosWebJpaController implements Serializable {

    public TblWMResumenOtrosWebJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TblWMResumenOtrosWeb tblWMResumenOtrosWeb) throws PreexistingEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(tblWMResumenOtrosWeb);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findTblWMResumenOtrosWeb(tblWMResumenOtrosWeb.getIdReporteWebOtros()) != null) {
                throw new PreexistingEntityException("TblWMResumenOtrosWeb " + tblWMResumenOtrosWeb + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TblWMResumenOtrosWeb tblWMResumenOtrosWeb) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            tblWMResumenOtrosWeb = em.merge(tblWMResumenOtrosWeb);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tblWMResumenOtrosWeb.getIdReporteWebOtros();
                if (findTblWMResumenOtrosWeb(id) == null) {
                    throw new NonexistentEntityException("The tblWMResumenOtrosWeb with id " + id + " no longer exists.");
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
            TblWMResumenOtrosWeb tblWMResumenOtrosWeb;
            try {
                tblWMResumenOtrosWeb = em.getReference(TblWMResumenOtrosWeb.class, id);
                tblWMResumenOtrosWeb.getIdReporteWebOtros();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tblWMResumenOtrosWeb with id " + id + " no longer exists.", enfe);
            }
            em.remove(tblWMResumenOtrosWeb);
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

    public List<TblWMResumenOtrosWeb> findTblWMResumenOtrosWebEntities() {
        return findTblWMResumenOtrosWebEntities(true, -1, -1);
    }

    public List<TblWMResumenOtrosWeb> findTblWMResumenOtrosWebEntities(int maxResults, int firstResult) {
        return findTblWMResumenOtrosWebEntities(false, maxResults, firstResult);
    }

    private List<TblWMResumenOtrosWeb> findTblWMResumenOtrosWebEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TblWMResumenOtrosWeb.class));
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

    public TblWMResumenOtrosWeb findTblWMResumenOtrosWeb(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TblWMResumenOtrosWeb.class, id);
        } finally {
            em.close();
        }
    }

    public int getTblWMResumenOtrosWebCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TblWMResumenOtrosWeb> rt = cq.from(TblWMResumenOtrosWeb.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
