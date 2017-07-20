package iteractors;

import data.repository.user.UserRepository;
import models.User;

public class UserIteractor {

	private UserRepository userRepository;
	
	public UserIteractor() {
		this.userRepository = new UserRepository();
	}
	
	public User find(Integer id) {
		return userRepository.find(id);
	}
	
}
