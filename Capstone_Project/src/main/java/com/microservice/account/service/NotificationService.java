package com.microservice.account.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Employee;
import com.microservice.account.model.Notification;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.NotificationRepository;
@Service
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void sendNotification(int eid, Notification notification) {
		Employee employee = employeeRepository.findById(eid).get();
		notification.setEmployee(employee);
		notificationRepository.save(notification);
	}

	public List<Notification> getNotificationByEmployee(String username) {
		
		return notificationRepository.getNotificationByEmployeeJpql(username);
	}

	public void updateNotificationForArchival(int nid) {
		Notification notification = notificationRepository.findById(nid).get();
		notification.setArchived(true);
		notificationRepository.save(notification);
		
	}

	public List<Notification> getAllNotification(String username) {
		return notificationRepository.getNotificationByEmployeeJpql(username);
	}

	public Notification getNotification(int nid) {
		
		return notificationRepository.findById(nid).get();
	}

	public Notification postNotification(Notification notification) {
		
		return notificationRepository.save(notification);
	}

}
