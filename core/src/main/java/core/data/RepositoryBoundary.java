package core.data;

import java.util.List;

public interface RepositoryBoundary<T> {
	
	T find(Integer id);
	List<T> all();
	
}
