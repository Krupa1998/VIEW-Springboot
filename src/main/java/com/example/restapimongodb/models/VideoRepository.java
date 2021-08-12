package com.example.restapimongodb.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository //this interface maps the document in MongoDB
public interface VideoRepository extends MongoRepository<Video, String> {
    //MongoRepository repository has all the functions to perform CRUD operations.
    //Movie(POJO) --> represents the collection called "movies"
    //String --> datatype of unique identifier in POJO class (datatype of id in Movie class --> String)
}
