package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
	
	@Query("select n from Notification n "
			+ " JOIN n.employee e "
			+ " JOIN e.userInfo u "
			+ " where u.username=?1")
	List<Notification> getNotificationByEmployeeJpql(String username);

	List<Notification> findByEmployeeId(String username);

}
