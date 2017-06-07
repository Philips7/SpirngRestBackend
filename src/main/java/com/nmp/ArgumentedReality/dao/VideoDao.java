package com.nmp.ArgumentedReality.dao;

import com.nmp.ArgumentedReality.entity.Video;

import java.util.ArrayList;

/**
 * Created by Dominik on 2017-06-03.
 */
public interface VideoDao {
    void createVideo(Video video);

    void updateVideo(Video video);

    void deleteVideo(int id);

    Video getVideoById(int id);

    Video getVideoByName(String name);

    ArrayList<Video> getVideos();

}
