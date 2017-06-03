package com.nmp.ArgumentedReality.dao.daoImpl;

import com.nmp.ArgumentedReality.dao.VideoDao;
import com.nmp.ArgumentedReality.entity.Video;
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
public class VideoDaoImpl implements VideoDao {

    @Autowired
    SessionFactory sessionFactory;


    @Override
    public void createVideo(Video video) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(video);
        session.flush();
    }

    @Override
    public void updateVideo(Video video) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(video);
        session.flush();
    }

    @Override
    public void deleteVideo(int id) {
        Session session = sessionFactory.getCurrentSession();
        Video video = getVideoById(id);
        session.delete(video);
        session.flush();
    }

    @Override
    public Video getVideoById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Video video = session.get(Video.class, id);
        session.flush();
        return video;
    }

    @Override
    public Video getVideoByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Video where name = :name").setString("name", name);
        return (Video) query.uniqueResult();
    }
}
