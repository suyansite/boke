package com.databaker.hyzx;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
@EnableScheduling
@ServletComponentScan
public class HyzxApplication   {

	private static Logger logger = LoggerFactory.getLogger(HyzxApplication.class);

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(HyzxApplication.class, args);

	}

}
