package com.example.restapimongodb.services;

import com.example.restapimongodb.models.Video;
import com.example.restapimongodb.models.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //annotates that this is a service
public class VideoService {

    @Autowired
    private VideoRepository repository; //has functions for get,post,put(update),delete

    @Autowired
    private MongoTemplate mongoTemplate; //for advanced queries like filtering


    public List<Video> getAllVideos() {

        //business logic
        return repository.findAll(); //findAll method returns a List
    }


    public List<Video> getMovieVideos() throws Exception {

        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(1));

        List<Video> videos = mongoTemplate.find(query, Video.class);

        if (videos.isEmpty()) {
            throw new Exception("Movies not found");
        }

        return videos;
    }


    public List<Video> getTVShowVideos() throws Exception {

        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(2));

        List<Video> videos = mongoTemplate.find(query, Video.class);

        if (videos.isEmpty()) {
            throw new Exception("TV Shows not found");
        }

        return videos;
    }


    public List<Video> getFeaturedMovies(Boolean f) throws Exception {

        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(1)).addCriteria(Criteria.where("isFeatured").is(f));

        List<Video> videos = mongoTemplate.find(query, Video.class);

        if (videos.isEmpty()) {
            throw new Exception("Featured Movies not found");
        }

        return videos;
    }


    public List<Video> getFeaturedTVShows(Boolean f) throws Exception {
        
        Query query = new Query();
        query.addCriteria(Criteria.where("type").is(2)).addCriteria(Criteria.where("isFeatured").is(f));

        List<Video> videos = mongoTemplate.find(query, Video.class);

        if (videos.isEmpty()) {
            throw new Exception("Featured TV Shows not found");
        }

        return videos;
    }


    public List<Video> getVideosWithRating(String rating) throws Exception {

        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("rating").is(rating));

        List<Video> videos = mongoTemplate.find(query, Video.class);

        if (videos.isEmpty()) {
            throw new Exception("Video with rating - " + rating + " is not found");
        }
        return videos;
    }


    public List<Video> getVideoWithTitle(String title) throws Exception {

        //business logic
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is(title));

        List<Video> videos = mongoTemplate.find(query, Video.class);

        if (videos.isEmpty()) {
            throw new Exception("Video with title - " + title + " is not found");
        }
        return videos;
    }


    public Optional<Video> getAVideo(String id) throws Exception { //Optional means it may also return a null

        //business logic
        Optional<Video> video = repository.findById(id); //Video can contain either null or Video so let's validate it

        if (!video.isPresent()) { //null value

            throw new Exception("Video with id " + id + " is not found");
        }

        return video;
    }


    public Optional<Video> deleteAVideo(String id) throws Exception {

        //business logic
        Optional<Video> video = repository.findById(id);

        //validate the id
        if (!video.isPresent()) {

            throw new Exception("Video with id " + id + " is not found");
        }

        repository.deleteById(id);
        return video;
    }


    public Video insertIntoVideos(Video video) throws Exception {

        //business logic
        if (video.getTitle().isEmpty() || video.getRating().isEmpty() || video.getBackgroundimg().isEmpty()
                || video.getBuy() == 0 || video.getRent() == 0 || video.getDirector().isEmpty()
                || video.getGenre().isEmpty() || video.getPoster().isEmpty() || video.getStars().isEmpty()
                || video.getRelease().isEmpty() || video.getStory().isEmpty() || video.getType() == 0) {

            throw new Exception("Video Creation Failed - at least one field is missing");
        }

        repository.insert(video);
        return video;
    }


    public Video editAVideo(String id, Video newVideoData) throws Exception {

        //get resource based on id
        Optional<Video> video = repository.findById(id);

        //validate the id
        if (!video.isPresent()) {

            throw new Exception("Video with id " + id + " is not found");

        } else if (newVideoData.getTitle().isEmpty() || newVideoData.getRating().isEmpty() || newVideoData.getBackgroundimg().isEmpty()
                || newVideoData.getBuy() == 0 || newVideoData.getRent() == 0 || newVideoData.getDirector().isEmpty()
                || newVideoData.getGenre().isEmpty() || newVideoData.getPoster().isEmpty() || newVideoData.getStars().isEmpty()
                || newVideoData.getRelease().isEmpty() || newVideoData.getStory().isEmpty() || newVideoData.getType() == 0) {

            throw new Exception("Update video failed - at least one field is missing");
        }

        //update the resource found with the new data (updating everything since we don't know what new data we're receiving
        video.get().setTitle(newVideoData.getTitle());
        video.get().setPoster(newVideoData.getPoster());
        video.get().setDirector(newVideoData.getDirector());
        video.get().setStars(newVideoData.getStars());
        video.get().setGenre(newVideoData.getGenre());
        video.get().setRelease(newVideoData.getRelease());
        video.get().setRating(newVideoData.getRating());
        video.get().setStory(newVideoData.getStory());
        video.get().setBackgroundimg(newVideoData.getBackgroundimg());
        video.get().setType(newVideoData.getType());
        video.get().setRent(newVideoData.getRent());
        video.get().setBuy(newVideoData.getBuy());
        video.get().setFeatured(newVideoData.isFeatured());

        //commit the update by saving the changes
        Video updatedVideo = repository.save(video.get());  //save the changes in the database
        return updatedVideo;
    }

}
