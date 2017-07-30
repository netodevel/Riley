package core.data.users;

import java.util.ArrayList;
import java.util.List;

import core.data.RepositoryBoundary;
import core.models.User;

/**
 * the implementation of your repository with your orm favorite
 * @author netodevel
 */
public class UserRepository implements RepositoryBoundary<User> {

	public User find(Integer id) {
		User user = new User(id, "netodevel", "josevieira.dev@gmail.com");
		return user;
	}

	public List<User> all() {
		List<User> allUsers = new ArrayList<User>();
		allUsers.add(new User(1, "user1", "email1"));
		allUsers.add(new User(2, "user2", "email2"));
		return allUsers;
	}
	
}
