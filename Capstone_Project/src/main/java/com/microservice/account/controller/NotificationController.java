package com.microservice.account.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Notification;
import com.microservice.account.model.Task;
import com.microservice.account.service.NotificationService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;
	
	@PostMapping("/capstone/notification/employee/{eid}")
	public void sendNotification(@PathVariable("eid") int eid,@RequestBody Notification notification){
		notificationService.sendNotification(eid,notification);
	}
	
	@GetMapping("/capstone/notification/employee")
	public List<Notification> getNotificationByEmployee(Principal principal) {
		String username = principal.getName();
		List<Notification> list = notificationService.getNotificationByEmployee(username);
		return list;
	}
	
	@GetMapping("/capstone/employee/notification/all")
	public List<Notification> getAllNotification(Principal principal){
		String username = principal.getName();
		List<Notification> list = notificationService.getAllNotification(username)
								.stream()
								.filter(t->t.isArchived() == false)
								.collect(Collectors.toList());
		return list;
	}
	
	@GetMapping("/capstone/notification/archive/{nid}")
	public Notification updateNotificationForArchival(@PathVariable("nid") int nid) {
		Notification notification =notificationService.getNotification(nid);
		notification.setArchived(true);
		return notificationService.postNotification(notification);
	}
	
}
