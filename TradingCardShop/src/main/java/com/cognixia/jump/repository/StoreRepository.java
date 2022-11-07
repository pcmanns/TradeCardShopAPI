package com.cognixia.jump.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.Store;


@Repository
public interface StoreRepository extends MongoRepository<Store,String>{
	
	
	public Optional<Store> findByStoreName(String storename);
	
	
	@Query("{ 'cardCollection.name': {$regex: '^?0$', $options: 'i' } }")
	public List<Store> findCardByName(String name);
	
}
