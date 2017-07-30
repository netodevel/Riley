package core.interactors.users;

import java.util.List;

import br.com.rileyframework.commands.service.Command;
import core.data.RepositoryBoundary;
import core.data.users.UserRepository;
import core.models.User;

/**
 * your use case here!!
 * @author netodevel
 */
public class UserListInteractor extends Command {

	private UserRepository userRepository;
	
	public UserListInteractor(RepositoryBoundary<User> repositoryBoundary) {
		this.userRepository = (UserRepository) repositoryBoundary;
	}

	public List<User> all() {
		return this.userRepository.all();
	}
	
}
