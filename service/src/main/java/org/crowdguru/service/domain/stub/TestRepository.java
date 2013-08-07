package org.crowdguru.service.domain.stub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.crowdguru.datastore.domain.HasId;

class TestRepository<T extends HasId> {

	private Map<Long, T> repo;
	
	TestRepository(){
		repo = new HashMap<Long, T>();
	}
	
	T save(T entity) {
		
		if(entity.getId() == null) {
			Long id = new Long(repo.size() + 1);
			entity.setId(id);
		}
		
		repo.put(entity.getId(), entity);
		return entity;
	}
	
	T findOne(Long id) {
		return repo.get(id);
	}
	
	List<T> findAll(){
		return new ArrayList<T>(repo.values());
	}
}
