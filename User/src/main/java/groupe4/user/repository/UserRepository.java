package groupe4.user.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import groupe4.user.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {

	public List<User> findByLogin(String login);

}
