package data.repository.user;

import models.User;

public class UserRepository implements UserRepositoryBoundary {

	@Override
	public User find(Integer id) {
		User user = new User();
		user.setId(id);
		user.setEmail("fake@gmail.com");
		user.setName("NetoDevel");
		return user;
	}

}
