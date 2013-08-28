package org.crowdguru.service.domain.impl;

import java.util.ArrayList;
import java.util.List;

import org.crowdguru.datastore.domain.Offer;
import org.crowdguru.datastore.domain.Task;
import org.crowdguru.datastore.domain.User;
import org.crowdguru.service.domain.NotificationService;
import org.crowdguru.service.domain.OfferService;
import org.crowdguru.service.domain.TaskService;
import org.crowdguru.service.domain.UserService;
import org.crowdguru.service.gateway.NotificationRepositoryGateway;
import org.crowdguru.service.gateway.OfferRepositoryGateway;
import org.crowdguru.service.request.OfferRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class OfferServiceImpl implements OfferService {

	private OfferRepositoryGateway offerRepository;
	
	private TaskService taskService;
	
	private UserService userService;
	
	private NotificationService notificationService;
	
	public OfferServiceImpl(){
		log().info("activity=created");
	}
	
	@Autowired
	public void setOfferRepository(OfferRepositoryGateway offerRepositoryGateway){
		this.offerRepository = offerRepositoryGateway;
	}
	
	@Autowired
	public void setTaskService(TaskService taskService){
		this.taskService = taskService;
	}
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	@Autowired
	public void setNotificationService(NotificationService notificationService){
		this.notificationService = notificationService;
	}
	
	@Override
	public Offer findOne(Long id) {
		Offer offer = offerRepository.findOne(id);
		log().info("activity=findOne;id=" + id + ";result=" + (offer != null ? true : false));
		return offer;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Offer> findAll() {
		List<Offer> offers = new ArrayList<Offer>();
		List<Offer> all = offerRepository.findAll();
		
		if(all != null){
			offers = all;
		}
		
		log().info("activity=findAll;count=" + all.size());
		return offers;
	}

	@Override
	@Transactional
	public Offer create(OfferRequest request) {
		Task task = taskService.getTaskById(request.getTaskId());
		User user = userService.getUserByEmail(request.getOwner());
		Offer offer = createOffer(task, user, request.getMessage());
		log().info("activity=create;id=" + offer.getId());
		notificationService.createOfferNotification(offer);
		return offer;
	}

	private Offer createOffer(Task task, User owner, String message) {
		Offer offer = new Offer();
		offer.setMessage(message);
		offer.setTask(task);
		offer.setUser(owner);
		return offerRepository.save(offer);
	}
}
