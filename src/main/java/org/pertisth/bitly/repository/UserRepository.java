package org.pertisth.bitly.repository;

import org.bson.types.ObjectId;
import org.pertisth.bitly.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User getUsersByUsername(String username);
}
