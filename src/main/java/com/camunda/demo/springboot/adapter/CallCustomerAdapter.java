package com.camunda.demo.springboot.adapter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.camunda.demo.springboot.ProcessConstants;

@Component
@ConfigurationProperties
public class CallCustomerAdapter implements JavaDelegate {

  @Autowired
  private RestTemplate rest;

  private String restProxyHost;
  private String restProxyPort;

  private String restEndpoint() {
    return "http://" + restProxyHost + ":" + restProxyPort + "/customer/phone";
  }
  

  public static class CreateChargeResponse {
    public String status; 
  }

  @Override
  public void execute(DelegateExecution ctx) throws Exception {
	  ResponseEntity<CreateChargeResponse> response = rest.exchange(restEndpoint(),
			    HttpMethod.GET, null, CreateChargeResponse.class);
        
    ctx.setVariable(ProcessConstants.VARIABLE_callStatus, response.getBody().status);
  }

  public String getRestProxyHost() {
    return restProxyHost;
  }

  public void setRestProxyHost(String restProxyHost) {
    this.restProxyHost = restProxyHost;
  }

  public String getRestProxyPort() {
    return restProxyPort;
  }

  public void setRestProxyPort(String restProxyPort) {
    this.restProxyPort = restProxyPort;
  }

}
