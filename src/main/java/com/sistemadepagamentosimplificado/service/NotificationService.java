package com.sistemadepagamentosimplificado.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sistemadepagamentosimplificado.dto.NotificationDTO;

@Service
public class NotificationService {
  @Autowired
  private RestTemplate restTemplate;

  public void sendNotification(String email, String message) throws Exception {
    NotificationDTO notification = new NotificationDTO(email, message);

    ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notification, String.class);

    if(notificationResponse.getStatusCode() != HttpStatus.OK) {
      throw new Exception("Notification service unavailable");
    }
  }
}
