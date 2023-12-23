package mk.ukim.finki.nationalheritage.service;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import mk.ukim.finki.nationalheritage.model.exceptions.InvalidHeritageIdException;

import java.util.List;


public interface HeritageService {
    /**
     * returns the entity with the given id
     *
     * @param id The id of the entity that we want to obtain
     * @return The Heritage entity with the given id
     * @throws InvalidHeritageIdException when there is no entity with the given id
     */
    Heritage findById(Long id);

    /**
     * @return List of all entities in the database
     */
    List<Heritage> listAll();

    /**
     * This method is used to create a new Heritage entity, and save it in the database.
     *
     * @return The Heritage entity that is created. The id should be generated when the entity is created.
     * @throws InvalidHeritageIdException when there is no entity with the given id
     */
    Heritage create(Long id,String name, HeritageType heritageType, String englishName, String description, String phoneNumber, String website, String location);

    /**
     * This method is used to modify a Heritage entity, and save it in the database.
     *
     * @param id The id of the entity that is being edited
     * @return The Heritage entity that is updated.
     * @throws InvalidHeritageIdException when there is no entity with the given id
     */
    Heritage update(Long id, String name, HeritageType heritageType, String englishName, String description, String phoneNumber, String website, String location);

    /**
     * Method that should delete a Heritage entity. If the id is invalid, it should throw InvalidHeritageIdException.
     *
     * @param id The id of the Heritage entity to be deleted
     * @return The Heritage entity that is deleted.
     * @throws InvalidHeritageIdException when there is no entity with the given id
     */
    Heritage delete(Long id);

    /**
     * The implementation of this method should use repository implementation for the filtering.
     * All arguments are nullable. When an argument is null, we should not filter by that attribute
     *
     * @return The Heritage entities that meet the filtering criteria
     */
    List<Heritage> filter(HeritageType heritageType, String location);

}
