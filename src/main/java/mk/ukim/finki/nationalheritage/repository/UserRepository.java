package mk.ukim.finki.nationalheritage.repository;

import mk.ukim.finki.nationalheritage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    List<User> filterByBirthday();

}
