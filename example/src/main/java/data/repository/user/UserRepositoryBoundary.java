package data.repository.user;

import models.User;

public interface UserRepositoryBoundary {

	public User find(Integer id);
	
}
