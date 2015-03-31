/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
 

/*
 *
 * @author Fructokinase
  */   
public class TaskController extends AbstractController {
    
        static String toViewResponse; 
        
        @Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
		HttpServletResponse response) throws Exception {
 
		ModelAndView modelandview = new ModelAndView("HelloPage");
                
                String addr;
                addr = "http://localhost:8080/jbpm-console/rest/task/query";
                getRequest(addr);
		modelandview.addObject("TaskList",toViewResponse);
		
		return modelandview;
	}
        
        public static void getRequest(String RequestUrl) throws Exception {
	   
	    try {
	      HttpClient client = HttpClientBuilder.create().build();
	      HttpGet get = new HttpGet(RequestUrl);

	      String authData = "alex" + ":" + "password";
	      String encoded = new sun.misc.BASE64Encoder().encode(authData.getBytes());
	      get.setHeader("Content-Type", "application/json");
	      get.setHeader("Authorization", "Basic " + encoded);
	      get.setHeader("ACCEPT", "application/JSON");

	      HttpResponse cgResponse = client.execute(get);
	      String content = EntityUtils.toString(cgResponse.getEntity());
              toViewResponse = content; 
              
	    } catch (Exception e) {
	      throw new Exception("Error consuming service.", e);
	    }
	  }
        
}
