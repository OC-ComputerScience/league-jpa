package soccerLeaguePD;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.FlushModeType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaDelete;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.metamodel.Metamodel;

/**
 * Dummy {@link EntityManager} used in unit testing. Implements every method by
 * doing nothing and returning base values. Intended to be subclassed to
 * override/implement some subset of methods for purposes of
 * testing/verification.
 * 
 * @author nic
 */
public class DummyEntityManager implements EntityManager {
	@Override
	public void clear() {

	}

	@Override
	public void close() {

	}

	@Override
	public boolean contains(Object arg0) {
		return false;
	}

	@Override
	public <T> EntityGraph<T> createEntityGraph(Class<T> arg0) {
		return null;
	}

	@Override
	public EntityGraph<?> createEntityGraph(String arg0) {
		return null;
	}

	@Override
	public Query createNamedQuery(String arg0) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createNamedQuery(String arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createNamedStoredProcedureQuery(String arg0) {
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0) {
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, @SuppressWarnings("rawtypes") Class arg1) {
		return null;
	}

	@Override
	public Query createNativeQuery(String arg0, String arg1) {
		return null;
	}

	@Override
	public Query createQuery(String arg0) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(CriteriaQuery<T> arg0) {
		return null;
	}

	@Override
	public Query createQuery(@SuppressWarnings("rawtypes") CriteriaUpdate arg0) {
		return null;
	}

	@Override
	public Query createQuery(@SuppressWarnings("rawtypes") CriteriaDelete arg0) {
		return null;
	}

	@Override
	public <T> TypedQuery<T> createQuery(String arg0, Class<T> arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, @SuppressWarnings("rawtypes") Class... arg1) {
		return null;
	}

	@Override
	public StoredProcedureQuery createStoredProcedureQuery(String arg0, String... arg1) {
		return null;
	}

	@Override
	public void detach(Object arg0) {

	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1) {
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, Map<String, Object> arg2) {
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2) {
		return null;
	}

	@Override
	public <T> T find(Class<T> arg0, Object arg1, LockModeType arg2, Map<String, Object> arg3) {
		return null;
	}

	@Override
	public void flush() {

	}

	@Override
	public CriteriaBuilder getCriteriaBuilder() {
		return null;
	}

	@Override
	public Object getDelegate() {
		return null;
	}

	@Override
	public EntityGraph<?> getEntityGraph(String arg0) {
		return null;
	}

	@Override
	public <T> List<EntityGraph<? super T>> getEntityGraphs(Class<T> arg0) {
		return null;
	}

	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return null;
	}

	@Override
	public FlushModeType getFlushMode() {
		return null;
	}

	@Override
	public LockModeType getLockMode(Object arg0) {
		return null;
	}

	@Override
	public Metamodel getMetamodel() {
		return null;
	}

	@Override
	public Map<String, Object> getProperties() {
		return null;
	}

	@Override
	public <T> T getReference(Class<T> arg0, Object arg1) {
		return null;
	}

	@Override
	public EntityTransaction getTransaction() {
		return null;
	}

	@Override
	public boolean isJoinedToTransaction() {
		return false;
	}

	@Override
	public boolean isOpen() {
		return false;
	}

	@Override
	public void joinTransaction() {

	}

	@Override
	public void lock(Object arg0, LockModeType arg1) {

	}

	@Override
	public void lock(Object arg0, LockModeType arg1, Map<String, Object> arg2) {

	}

	@Override
	public <T> T merge(T arg0) {
		return null;
	}

	@Override
	public void persist(Object arg0) {

	}

	@Override
	public void refresh(Object arg0) {

	}

	@Override
	public void refresh(Object arg0, Map<String, Object> arg1) {

	}

	@Override
	public void refresh(Object arg0, LockModeType arg1) {

	}

	@Override
	public void refresh(Object arg0, LockModeType arg1, Map<String, Object> arg2) {

	}

	@Override
	public void remove(Object arg0) {

	}

	@Override
	public void setFlushMode(FlushModeType arg0) {

	}

	@Override
	public void setProperty(String arg0, Object arg1) {

	}

	@Override
	public <T> T unwrap(Class<T> arg0) {
		return null;
	}
}
