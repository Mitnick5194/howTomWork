package com.tomcat.ex3;

import java.io.IOException;

import com.tomcat.ex3.connector.http.HttpRequest;
import com.tomcat.ex3.connector.http.HttpResponse;

public class StaticResourceProcessor {

  public void process(HttpRequest request, HttpResponse response) {
    try {
      response.sendStaticResource();
    }
    catch (IOException e) {
      e.printStackTrace();
    }
  }

}
