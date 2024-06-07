package cz.engeto.genesisResources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenesisResourcesApplication {

	public static void main(String[] args) {
		System.out.println("Starting GenesisResourcesApplication...");
		SpringApplication.run(GenesisResourcesApplication.class, args);
		System.out.println("GenesisResourcesApplication started.");
	}
}