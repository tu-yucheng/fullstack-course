package cn.tuyucheng.taketoday.reactorbus.service.impl;

import cn.tuyucheng.taketoday.reactorbus.domain.NotificationData;
import cn.tuyucheng.taketoday.reactorbus.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class NotificationServiceImpl implements NotificationService {

	@Override
	public void initiateNotification(NotificationData notificationData) throws InterruptedException {
		LOGGER.info("Notification service started for Notification ID: {}", notificationData.getId());
		Thread.sleep(5000);
		LOGGER.info("Notification service ended for Notification ID: {}", notificationData.getId());
	}
}