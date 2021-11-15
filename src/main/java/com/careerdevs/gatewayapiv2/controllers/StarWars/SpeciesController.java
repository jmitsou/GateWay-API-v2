package com.careerdevs.gatewayapiv2.controllers.StarWars;

import com.careerdevs.gatewayapiv2.models.Species;
import com.careerdevs.gatewayapiv2.models.SpeciesResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SpeciesController {

    @GetMapping("/ping")
    public String test (){
        return "pong";
    }

    @GetMapping("/sw/species")
    public Species[] species (RestTemplate restTemplate){
        Species[] arr = null;
        String nextURL = "https://swapi.dev/api/species?format=json";
        int elementNum = 0;
        do {
            SpeciesResponse test = restTemplate.getForObject(nextURL, SpeciesResponse.class);
            nextURL = test.getNext();

            if (arr == null) {
                arr = new Species[test.getCount()];
            }

            for (int i = 0; i < test.getResults().length; i++) {
                arr[elementNum] = test.getResults()[i];
                elementNum++;
            }

        }while (nextURL != null);

        return arr;

        //restTemplate.getForObject(nextURL,SpeciesResponse.class);
    }


}
