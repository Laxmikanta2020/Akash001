package com.laxmi.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.laxmi.binding.Pet;

@RestController
public class ExampleRestClient {
	
	@GetMapping("/pet/{petId}")
	public Pet getPet(@PathVariable("petId")Integer petId)  {
		String url="https://vur3ednzag.execute-api.ap-south-1.amazonaws.com/prod/pets/{petId}";
		RestTemplate rt=new RestTemplate();
		ResponseEntity<Pet> forEntity = rt.getForEntity(url, Pet.class,petId);
		Pet body = forEntity.getBody();
		System.out.println(body);
		return body;
		
	}

	@GetMapping("/pets")
	public void getAllPets() {
		
		String apiUrl="https://vur3ednzag.execute-api.ap-south-1.amazonaws.com/prod/pets/";
		
		WebClient  client=WebClient.create();
		
				
		
		
		client.get()
		  .uri(apiUrl)
		  .retrieve()
		  .bodyToMono(Pet.class)
		  .subscribe(ExampleRestClient::ticketRespHandler);

	System.out.println("Logic executing after sending request.....");

		
	}
	
	@PostMapping("/addPet")
	public String  addPet() {
		
		RestTemplate rr=new RestTemplate();
		String url="https://vur3ednzag.execute-api.ap-south-1.amazonaws.com/prod/pets/";
		System.out.println("HI");
		Pet p=new Pet();
		p.setId(4);
		p.setType("cow");
		p.setPrice(2000.33);
		
		
		System.out.println("HI");
		ResponseEntity<String> postForEntity = rr.postForEntity(url, p, String.class);
		String body = postForEntity.getBody();
		System.out.println("HI"+body);
		return body;
		
	}
	


public static void ticketRespHandler(Pet ticket) {
System.out.println("Response Recieved from api.....");
System.out.println(ticket);
}
	
}
