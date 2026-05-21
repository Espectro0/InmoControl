package com.inmocontrol.inicializador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.inmocontrol"})
public class InmocontrolBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(InmocontrolBackendApplication.class, args);
  }
}
