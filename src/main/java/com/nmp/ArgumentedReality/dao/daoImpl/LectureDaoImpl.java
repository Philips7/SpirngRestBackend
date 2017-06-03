package com.nmp.ArgumentedReality.dao.daoImpl;

import com.nmp.ArgumentedReality.dao.LectureDao;
import com.nmp.ArgumentedReality.entity.Lecture;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by Dominik on 2017-06-03.
 */
@Repository
@Transactional
public class LectureDaoImpl implements LectureDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void createLecture(Lecture lecture) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(lecture);
        session.flush();
    }

    @Override
    public void updateLecture(Lecture lecture) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(lecture);
        session.flush();
    }

    @Override
    public void deleteLecture(int id) {
        Session session = sessionFactory.getCurrentSession();
        Lecture lecture = getLectureById(id);
        session.delete(lecture);
        session.flush();
    }

    @Override
    public Lecture getLectureById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Lecture lecture = session.get(Lecture.class, id);
        session.flush();
        return lecture;
    }

    @Override
    public Lecture getLectureByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Lecture where name = :name").setString("name", name);
        return (Lecture) query.uniqueResult();
    }
}
