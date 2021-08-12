package com.example.restapimongodb.controllers;

import com.example.restapimongodb.CustomizedResponse;
import com.example.restapimongodb.models.Video;
import com.example.restapimongodb.services.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController //annotates that this is a controller
public class VideoController {

    @Autowired//this will instantiate the following reference variable and create an object automatically
    //so we can use the given variable directly without doing new MovieService()
    private VideoService service;


    //ResponseEntity accepts any object and returns status

    @GetMapping("/videos")
    public ResponseEntity getVideos() {

        var customizedResponse = new CustomizedResponse("A list of All Videos", service.getAllVideos());

        return new ResponseEntity(customizedResponse, HttpStatus.OK); //Customized response with list and message
    }


    @GetMapping("/videos/movies")
    public ResponseEntity getVideosMovies() {

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("A list of All Movies", service.getMovieVideos());

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @GetMapping("/videos/tvshows")
    public ResponseEntity getVideosShows() {
        CustomizedResponse customizedResponse = null;

        try {
            customizedResponse = new CustomizedResponse("A list of All TV Shows", service.getTVShowVideos());

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    // /videos/movies/featured?isFeatured=true
    @GetMapping("/videos/movies/featured")
    public ResponseEntity getFeaturedMovies(@RequestParam(value = "isFeatured") Boolean f) {
        CustomizedResponse customizedResponse = null;

        try {
            customizedResponse = new CustomizedResponse("A list of Featured Movies", service.getFeaturedMovies(f));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    // /videos/tvshows/featured?isFeatured=true
    @GetMapping("/videos/tvshows/featured")
    public ResponseEntity getFeaturedShows(@RequestParam(value = "isFeatured") Boolean f) {
        CustomizedResponse customizedResponse = null;

        try {
            customizedResponse = new CustomizedResponse("A list of Featured TV Shows", service.getFeaturedTVShows(f));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    // /videos/rating?rating=PG-13 <-- following URL example
    @GetMapping("/videos/rating") //filtering get request result using query string
    public ResponseEntity getVideosByRating(@RequestParam(value = "rating") String rate) {
        //RequestParam to define query string parameter (rating -->parameter name) (value datatype is String)

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("A list of All Movies with Rating", service.getVideosWithRating(rate));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK); //Customized response with list and message
    }


    // /movies/title?title=frozen <-- following URL example
    @GetMapping("/videos/title")
    public ResponseEntity getVideoByTitle(@RequestParam(value = "title") String title) {

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("Movie with title " + title, service.getVideoWithTitle(title));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK); //Customized response with list and message
    }


    @GetMapping("/videos/{id}")
    public ResponseEntity getVideoById(@PathVariable("id") String id) { //to remember the path variable

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("A Movie with ID " + id,
                    Collections.singletonList(service.getAVideo(id)));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND); //shows error if id not found
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @PostMapping(value = "/videos", consumes = {

            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity addVideo(@RequestBody Video movie) {

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("Video Added Successfully",
                    Collections.singletonList(service.insertIntoVideos(movie)));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @DeleteMapping("/videos/{id}")
    public ResponseEntity deleteVideo(@PathVariable("id") String id) { //to remember the path variable

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("Video Deleted Successfully",
                    Collections.singletonList(service.deleteAVideo(id)));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }


    @PutMapping(value = "/videos/{id}", consumes = {

            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity editVideo(@PathVariable("id") String id, @RequestBody Video movieData) {

        CustomizedResponse customizedResponse = null;

        try {

            customizedResponse = new CustomizedResponse("Video With ID - " + id + " Updated Successfully",
                    Collections.singletonList(service.editAVideo(id, movieData)));

        } catch (Exception e) {

            customizedResponse = new CustomizedResponse(e.getMessage(), null);
            return new ResponseEntity(customizedResponse, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(customizedResponse, HttpStatus.OK);
    }

}
