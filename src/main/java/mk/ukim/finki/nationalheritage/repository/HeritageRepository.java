package mk.ukim.finki.nationalheritage.repository;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.HeritageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HeritageRepository extends JpaRepository<Heritage,Long> {
    //TODO add aditional filters
    List<Heritage> findByHeritageTypeAndLocation(HeritageType heritageType, String location);

    List<Heritage> findByHeritageType(HeritageType heritageType);

    List<Heritage> findByLocation(String location);
}
