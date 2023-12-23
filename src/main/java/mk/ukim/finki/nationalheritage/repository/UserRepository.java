package mk.ukim.finki.nationalheritage.repository;

import mk.ukim.finki.nationalheritage.model.Heritage;
import mk.ukim.finki.nationalheritage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> findByBirthday(LocalDate birthday);


    List<User> findByVisited(List<Heritage> tmep);
}
