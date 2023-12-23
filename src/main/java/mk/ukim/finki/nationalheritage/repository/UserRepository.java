package mk.ukim.finki.nationalheritage.repository;

import mk.ukim.finki.nationalheritage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
