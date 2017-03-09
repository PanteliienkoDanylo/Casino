package softgroup.ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softgroup.ua.jpa.User;


/**
 * @author Stanislav Rymar
 */
public interface UserRepository extends JpaRepository<User, String> {



}
