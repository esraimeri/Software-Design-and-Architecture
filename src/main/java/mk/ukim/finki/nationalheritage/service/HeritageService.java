package mk.ukim.finki.nationalheritage.service;


import mk.ukim.finki.nationalheritage.model.Heritage;

import java.util.List;

public interface HeritageService {
    //TODO
    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return The Heritage entity with the given id
     * @throws mk.ukim.finki.nationalheritage.model.exceptions.InvalidHeritageIdException when there is no entity with the given id
     */

    Heritage findById(Long id);

    /**
     * @return List of all entities in the database
     */
    List<Heritage> listAll();

    /**
     * This method is used to create a new Heritage entity, and save it in the database.
     *
     * @param name The name of the Heritage entity
     * @return The Heritage entity that is created. The id should be generated when the entity is created.
     */
    Heritage create(String name);
}
