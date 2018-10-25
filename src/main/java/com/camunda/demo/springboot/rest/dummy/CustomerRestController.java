package com.camunda.demo.springboot.rest.dummy;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerRestController {
  
  @RequestMapping(path="/phone", method=RequestMethod.GET)
  public String createCustomer() {
	  System.out.println("Inside call customer method");
    return "{\"status\": \"Success\"}";
  }
}
