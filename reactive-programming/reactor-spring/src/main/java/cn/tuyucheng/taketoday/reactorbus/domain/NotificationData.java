package cn.tuyucheng.taketoday.reactorbus.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationData {

	private long id;
	private String name;
	private String email;
	private String mobile;
}