package com.cognixia.jump.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Cards;
import com.cognixia.jump.model.Store;
import com.cognixia.jump.repository.StoreRepository;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.service.StoreService;

@RestController
@RequestMapping("/api")
public class StoreController {

	@Autowired
	StoreService service;
	
	@Autowired
	StoreRepository repo;
	
	@GetMapping("/store")
	public List<Store> GetAllStore(){
		return service.getAllStore();
	}
	
	@GetMapping("/{storeName}")
	public Store getStoreByStoreName(@PathVariable String storeName) throws ResourceNotFoundException {
		return service.getStoreByStoreName(storeName);
		
	}
	
	@GetMapping("/{storeName}/card")
	public List<Cards> getCardByStoreName(@PathVariable String storeName) throws ResourceNotFoundException {
		return service.getCardByStoreName(storeName);
		
	}
	@GetMapping("/store/card/{cardname}")
	public List<Store> getStoresWithCard(@PathVariable String cardname){
		
		return service.storeWithCard(cardname);
	}
	//Create Store with the name in the body
	@PostMapping("/store")
	public ResponseEntity<?> createStore(@Valid @RequestBody Store col, @CurrentSecurityContext(expression = "authentication") Authentication authentication){
		
		col.setOwnerid(authentication.getName());
		
		if(repo.findByStoreName(col.getStoreName()).isPresent()) {
			return ResponseEntity.status(400).body("Exist already");
		}
		
		Store created =service.createStore(col);

		return ResponseEntity.status(201).body(created);
		
	}
	//add card to store
	@PostMapping("/{store}/card/add")
	public ResponseEntity<?> addCard(@PathVariable String store, @RequestBody Cards card) throws ResourceNotFoundException{
		
		Cards addCard=service.addCard(card,store);
		
		return ResponseEntity.status(201).body(addCard);
		
	}
	//edit a card in a store
	@PostMapping("/{store}/card/edit")
	public ResponseEntity<?> editCard(@PathVariable String store,@RequestBody Cards card) throws ResourceNotFoundException{
		
		Cards updateCard=service.editCard(card,store);
		
		return ResponseEntity.status(202).body(updateCard);
		
	}
	//delete a card from a store
	@PostMapping("/{store}/card/delete")
	public ResponseEntity<?> deleteCard(@PathVariable String store,@RequestBody Cards card) throws ResourceNotFoundException{
		
		Cards deleteCard=service.deleteCard(card,store);
		
		return ResponseEntity.status(202).body(deleteCard);
		
	}
	//place an order from body to store in url  
	@PostMapping("/{store}/order")
	public ResponseEntity<?> addOrder(@PathVariable String store,@RequestBody Order order, @CurrentSecurityContext(expression = "authentication") Authentication authentication) throws ResourceNotFoundException{
		
		order.setCustomerUserName(authentication.getName());
		order.setStoreName(store);
		Order addOrder=service.addOrder(order);
		
		return ResponseEntity.status(201).body(addOrder);
		
	}
	
	
	
}
