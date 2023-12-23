package mk.ukim.finki.nationalheritage.service;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.model.User;

import java.time.LocalDate;
import java.util.List;

public interface UserService {


        /**
         * @return List of all entities in the database
         */
        List<User> listAll();

        /**
         * returns the entity with the given id
         *
         * @param id The id of the entity that we want to obtain
         * @return The User entity with the given id
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         */
        User findById(Long id);

        /**
         * This method is used to create a new User entity, and save it in the database.
         *
         * @return The User entity that is created. The id should be generated when the entity is created.
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         */
        User create(String name, String email, String password,  List<Long> heritageId, LocalDate birthday);

        /**
         * This method is used to modify a User entity, and save it in the database.
         *
         * @param id The id of the entity that is being edited
         * @return The User entity that is updated.
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidHeritageIdException  when there is no entity with the given id
         */
        User update(Long id, String name, String email, String password, List<Long> heritageId, LocalDate birthday);

        /**
         * Method that should delete a User entity. If the id is invalid, it should throw InvalidUserIdException.
         *
         * @param id The id of the User entity to be deleted
         * @return The User entity that is deleted.
         * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidUserIdException when there is no entity with the given id
         */
        User delete(Long id);

        /**
            * @return The User entities that meet the filtering criteria
         */
        //TODO different filters
        //this is only the general starting filter
        List<User> filter(String city, String description, HeritageType type, String location, LocalDate birthday, HeritageType heritageType);
        List<Heritage> getClosestHeritages(Long userId);

}
