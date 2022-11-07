package com.cognixia.jump.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.Cards;
import com.cognixia.jump.model.Store;
import com.cognixia.jump.model.Order;
import com.cognixia.jump.repository.StoreRepository;

@Service
public class StoreService {
	
	@Autowired
	StoreRepository repo;

	public List<Store> getAllStore() {
		return repo.findAll();
	}
	public List<Store> storeWithCard(String card){
		
		return repo.findCardByName(card);
		
	}
	public List<Cards> getCardByStoreName(String storeName) throws ResourceNotFoundException {
		Optional<Store> s=repo.findByStoreName(storeName);
		if(s.isPresent())
			return s.get().getCardCollection();
		throw new ResourceNotFoundException("Store Not Found");
	} 
	
	public Store createStore(Store col) {
		col.setId(null);
		List<Cards>cards=new ArrayList<Cards>();
		col.setCardCollection(cards);
		List<Order> orders=new ArrayList<Order>();
		col.setTradesOrder(orders);
		Store created =repo.insert(col);
		
		return created;
	}

	public Store getStoreByStoreName(String store) throws ResourceNotFoundException {
		Optional<Store> Store = repo.findByStoreName(store);
		if(Store.isPresent()) {
			return Store.get();
		}
		throw new ResourceNotFoundException("Store Not Found");
	}

	//add an Order
	public Order addOrder(Order order) throws ResourceNotFoundException {
		
		if(order.getRequestCopys().size()==order.getCard().size()) {
			Store update= getStoreByStoreName(order.getStoreName());
			order.setTotal(findTotal(order));
			update=editCardByOrder(order);
			update.getTradesOrder().add(order);
			update=repo.save(update);
		}
		else {
			throw new ResourceNotFoundException("Card name and request number are not equal");
		}
		return order;
	}
	//add Cards to the Collection
	public Cards addCard(Cards card,String name) throws ResourceNotFoundException {
		Store update= getStoreByStoreName(name);
		update.getCardCollection().add(card);
		update=repo.save(update);
		return card;
	}
	

	//make any edit but the name to the Card
	public Cards editCard(Cards card,String name) throws ResourceNotFoundException {
		Store update= getStoreByStoreName(name);
		Cards updateCard =searchForCard(card.getName(),update);
		update.getCardCollection().set(update.getCardCollection().indexOf(updateCard), card);
		update=repo.save(update);
		return card;	
	} 
	public Cards deleteCard(Cards card,String user) throws ResourceNotFoundException {
		Store update= getStoreByStoreName(user);
		Cards updateCard =searchForCard(card.getName(),update);
		update.getCardCollection().remove(update.getCardCollection().indexOf(updateCard));
		update=repo.save(update);
		return updateCard;	
	} 
	
	
	
	
	//Helper
	
	//find a Card in the Collection
	public Cards searchForCard(String name,Store coll) throws ResourceNotFoundException {
		for(Cards c:coll.getCardCollection()) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		throw new ResourceNotFoundException("Card Does Not Exist");
	}
	
	//edit the Card Inventory
	public Store editCardByOrder(Order order) throws ResourceNotFoundException {
		Store update= getStoreByStoreName(order.getStoreName());
		if(order.getRequestCopys().size()==order.getCard().size()) {
			for(int i=0;i<order.getCard().size();i++) {
				Cards updateCard=searchForCard(order.getCard().get(i),update);
				if(updateCard.getInventory()>order.getRequestCopys().get(i)) {
					updateCard.setInventory(updateCard.getInventory()-order.getRequestCopys().get(i));
					update.getCardCollection().set(update.getCardCollection().indexOf(updateCard), updateCard);
				}
				else {
					throw new ResourceNotFoundException("Request Amout is more than Available");
				}
			}
		}
		return update;	
	}
	//find the Total for a order
	public double findTotal(Order order) throws ResourceNotFoundException {
		double total=0;
		Store update= getStoreByStoreName(order.getStoreName());
		if(order.getRequestCopys().size()==order.getCard().size()) {
			for(int i=0;i<order.getCard().size();i++) {
				total+=((double)searchForCard(order.getCard().get(i),update).getCost())*((double)order.getRequestCopys().get(i));
			}
		}
		return total;
	}
	
	
	
}
