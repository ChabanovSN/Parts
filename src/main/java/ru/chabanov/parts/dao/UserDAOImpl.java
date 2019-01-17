package ru.chabanov.parts.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.chabanov.parts.model.Parts;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private final static int MinAmountComponentsForFullPC = 5; // мин. количество деталей для сборки при условии что детали совместимы
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addParts(Parts parts) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(parts);
    }

    public void updateParts(Parts parts) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.update(parts);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void deleteParts(int id) {
        Session session = sessionFactory.getCurrentSession();
        Parts parts = (Parts) session.load(Parts.class, new Integer(id));
        if (parts != null) {
            session.delete(parts);
        }
    }

    public Parts getPartsById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Parts parts = (Parts) session.get(Parts.class, new Integer(id));
        return parts;
    }

    @SuppressWarnings("unchecked")
    public List<Parts> sortComps(int rowsPerPage, int page, int isNecessary) {
        List<Parts> list = null;

        if(isNecessary == 2)
            list = listComps(rowsPerPage,page,"");
        else{
            try {
                Session session = this.sessionFactory.getCurrentSession();
                Query query = session.createQuery("from Parts where isNecessary = "+isNecessary);
                //query.setInteger("isNecessary", isNecessary);
                if (rowsPerPage > 0) {
                    query.setFirstResult((page - 1) * rowsPerPage);
                    query.setMaxResults(rowsPerPage);
                }
                list = (List<Parts>) query.list();

//                for(Comp c: listUsers) System.out.println(c.getName()+" isNecessary "+ c.getIsNecessary());
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Ошибка здесь sortComps");
            }
        }

        return list;
    }
    @SuppressWarnings("unchecked")
    public List<Parts> listComps(int rowsPerPage, int page, String criterion) {
        List<Parts> list = null;
        try {
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Parts where name like :criterion");
            query.setString("criterion", "%" + criterion + "%");


            if (rowsPerPage > 0) {
                query.setFirstResult((page - 1) * rowsPerPage);
                query.setMaxResults(rowsPerPage);
            }
            list = (List<Parts>) query.list();
//             System.out.println(criterion);
//             for(Comp c: listUsers) System.out.println(c.getName());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ошибка здесь listComps DAO");
        }
        return list;
    }

    public long getCompsCount() {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(id) from Parts");
        return (Long) query.uniqueResult();
    }

    public long getCompsCount(int isNecessary) {
        if(isNecessary == 2)
            return getCompsCount();
        else {
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("select count(id) from Parts where isNecessary = "+isNecessary);
            return (Long) query.uniqueResult();
        }
    }
    @SuppressWarnings("unchecked")
    public int getAmountOfFullComplect() {
        /// то все при условии что все компоненты совместимы
        List<Parts> list = null;
        int amountOfFullPC=0;
        try{
            Session session = this.sessionFactory.getCurrentSession();
            Query query = session.createQuery("from Parts where isNecessary = 1");

            list = (List<Parts>) query.list();
            if(list.size()>=MinAmountComponentsForFullPC) {
                Collections.sort(list, new Comparator<Parts>() {
                    public int compare(Parts o1, Parts o2) {
                        return o1.getAmount() - o2.getAmount();
                    }
                });
                amountOfFullPC = list.get(0).getAmount();
            }
//            for(Comp c: listUsers) System.out.println(c.getName() + " amount "+c.getAmount());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("getAmountOfFullComplect()");
        }

        return amountOfFullPC;
    }
}
