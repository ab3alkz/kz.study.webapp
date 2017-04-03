package util;

/**
 * @author amanzhol-ak;
 * @since on 01.11.2016.
 */
public class Utx {

    /*@Resource
    private UserTransaction utx;

    @PersistenceContext(unitName = DEF_JNDINAME_EM5)
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(Utx.class);


    public void utxBegin() throws Exception {
        // при вызове падает NotSupportedException, возможно он уже открыт
        try {
            utx.begin();
        } catch (NotSupportedException | SystemException e) {
            throw new Exception(e);
        }
    }

    protected void utxRollback() throws Exception {
        try {
            utx.rollback();
            LOGGER.info("rollback successful");
        } catch (SystemException | IllegalStateException e) {
            throw new Exception(e.getMessage());
        }
    }

    protected void utxCommit() throws HeuristicRollbackException, RollbackException, HeuristicMixedException, SystemException {
        utx.commit();
    }

    *//**
     * @param <E> - имя сущности
     * @param <T> - тип параметра
     * @param <X> - параметры
     * @desc - метод динамический возвращает сущность
     * @author beljerin;
     * @since 23.02.2017;
     *//*
    protected <E, T, X> E getSingleResultWithParam(Class<E> eClass, Class<T> tClass, X... param) throws Exception {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> queryFilter = builder.createQuery(eClass);
            Root<E> root = queryFilter.from(eClass);
            queryFilter.select(root);

            List<Predicate> predicateList = new ArrayList<>();

            for (int i = 0; i <= param.length / 2; i += 2) {
                predicateList.add(builder.equal(root.get((String) param[i]).as(tClass), param[i + 1]));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            predicateList.toArray(predicates);
            queryFilter.where(predicates);

            return (E) getSingleResultOrNull(entityManager.createQuery(queryFilter));
        } catch (Exception e) {
            throw new Exception();
        }
    }

    *//**
     * @param <E> - имя сущности
     * @param <T> - тип параметра
     * @param <X> - параметры
     * @return List<E>;
     * @desc - метод динамический возвращает коллекцию сущностей
     * @author beljerin;
     * @since 23.02.2017;
     *//*
    protected <E, T, X> List<E> getResultListWithParam(Class<E> eClass, Class<T> tClass, X... param) throws Exception {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<E> queryFilter = builder.createQuery(eClass);
            Root<E> root = queryFilter.from(eClass);
            queryFilter.select(root);

            List<Predicate> predicateList = new ArrayList<>();

            for (int i = 0; i <= param.length / 2; i += 2) {
                predicateList.add(builder.equal(root.get((String) param[i]).as(tClass), param[i + 1]));
            }

            Predicate[] predicates = new Predicate[predicateList.size()];
            predicateList.toArray(predicates);
            queryFilter.where(predicates);

            return entityManager.createQuery(queryFilter).getResultList();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    *//**
     *
     * @param query;
     * @param max;
     * @return List<E>;
     * @author beljerin;
     * @since 23.02.2017
     *//*
    protected <E> List<E> getResultListWithMaxResult(String query, int max) throws Exception {
        try {
            return (List<E>) entityManager.createNamedQuery(query)
                    .setMaxResults(max)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception();
        }
    }

    *//**
     * @param str;
     * @return <E>
     *//*
    protected <E> List<E> getResultListWithoutParam(String str) throws Exception {
        try {
            return (List<E>) entityManager.createNamedQuery(str)
                    .getResultList();
        } catch (Exception e) {
            throw new Exception();
        }
    }*/
}
