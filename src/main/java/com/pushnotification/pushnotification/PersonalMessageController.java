package com.pushnotification.pushnotification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class PersonalMessageController {

  private final PersonalMessageSender pushSender;
  private Logger logger = LoggerFactory.getLogger(PersonalMessageController.class);

  public PersonalMessageController(PersonalMessageSender pushSender) {
    this.pushSender = pushSender;
  }
  
  @GetMapping("/ping")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity pingServer() {
//	  System.out.println("Server is alive ");
	  return ResponseEntity.ok("server is alive");
	  
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void register(@RequestParam("token") String token) {
    logger.error("before token is: "+ token);
    System.out.println("register: " + token);
    this.pushSender.addToken(token);
    logger.error("after token is: "+ token);
  }

  @PostMapping("/unregister")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void unregister(@RequestParam("token") String token) {
    logger.error("before token is: "+ token);
    System.out.println("unregister: " + token);
    this.pushSender.removeToken(token);
    logger.error("after token is: "+ token);
  }

}
