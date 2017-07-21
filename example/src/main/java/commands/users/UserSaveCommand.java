package commands.users;

import br.com.rileyframework.commands.service.Command;

public class UserSaveCommand extends Command {
	
	public void saveUser(Integer userId) {
		if (userId > 0) {
			broadcastSuccess();
		} else {
			broadcastFailed();
		}
	}
	
}
