package mk.ukim.finki.nationalheritage.service;

import mk.ukim.finki.nationalheritage.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {

    public interface UserInterface {

        //TODO exceptions annotation
        /**
         * @return List of all entities in the database
         */
        List<User> listAll();

        /**
         * returns the entity with the given id
         *
         * @param id The id of the entity that we want to obtain
         * @return The ForumUser entity with the given id
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         */
        User findById(Long id);

        /**
         * This method is used to create a new ForumUser entity, and save it in the database.
         *
         * @return The ForumUser entity that is created. The id should be generated when the entity is created.
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         */
        User create(String name, String email, String password,  List<Long> interestId, LocalDate birthday);

        /**
         * This method is used to modify a ForumUser entity, and save it in the database.
         *
         * @param id The id of the entity that is being edited
         * @return The ForumUser entity that is updated.
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidHeritageIdException  when there is no entity with the given id
         */
        User update(Long id, String name, String email, String password, List<Long> interestId, LocalDate birthday);

        /**
         * Method that should delete a ForumUser entity. If the id is invalid, it should throw InvalidForumUserIdException.
         *
         * @param id The id of the ForumUser entity to be deleted
         * @return The ForumUser entity that is deleted.
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         */
        User delete(Long id);

        /**
         * The implementation of this method should use repository implementation for the filtering.
         * All arguments are nullable. When an argument is null, we should not filter by that attribute
         *
         * @return The ForumUser entities that meet the filtering criteria
         */
        List<User> filter(Long interestId, Integer age);
    }
}
