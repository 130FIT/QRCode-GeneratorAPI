package io.boatboat.qrcode_generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ServletComponentScan
public class QrcodeGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(QrcodeGeneratorApplication.class, args);
	}

}
