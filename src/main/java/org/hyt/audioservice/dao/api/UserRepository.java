package org.hyt.audioservice.dao.api;

import org.hyt.audioservice.model.actual.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends MongoRepository<User, Long> {

}
