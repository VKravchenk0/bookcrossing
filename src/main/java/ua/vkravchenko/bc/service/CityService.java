package ua.vkravchenko.bc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.vkravchenko.bc.entity.City;
import ua.vkravchenko.bc.entity.Country;
import ua.vkravchenko.bc.entity.User;
import ua.vkravchenko.bc.repository.CityRepository;
import ua.vkravchenko.bc.repository.CountryRepository;
import ua.vkravchenko.bc.service.InitDbService.VkApiResponse;
import ua.vkravchenko.bc.util.Util;

@Transactional
@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Transactional
	public void saveCityCountryPair(City city, Country country){
	    cityRepository.save(city);
	    countryRepository.save(country);
	}

	
	public City loadCity(int cityId, String cityName, Country country) {
		if (country != null) {
			String request = "https://api.vk.com/method/database.getCities?country_id=" + country.getId() + "&q=" + cityName + "&count=1000&need_all=1&lang=ru";
			ObjectMapper mapper = new ObjectMapper();
			City city = null;
			List<City> cities = null;
			try {
				String json = Util.getJson(request);
				VkApiResponse<City> response = mapper.readValue(json,
						new TypeReference<VkApiResponse<City>>() {
						});
				cities = response.getResponse();
				for (City cityFromVK : cities) {
					if (cityFromVK.getId() == cityId) {
						city = cityFromVK;
						city.assignCountry(country);
						city.setUsers(new ArrayList<User>());
						saveCityCountryPair(city, country);
						cityRepository.flush();
						break;
					}
				} 
				return city;
				
			} catch (JsonGenerationException e) {
				e.printStackTrace();
				return null;
			} catch (JsonMappingException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			} 
		} else {
			return null;
		}
	}
	
	/*public City loadCity(int cityId, String cityName, Country country) {
		String request = "https://api.vk.com/method/database.getCitiesById?city_ids=" + cityId + "&lang=ru";
		ObjectMapper mapper = new ObjectMapper();
		City city = null;
		try {
			String json = Util.getJson(request);
			VkApiResponse<City> response = mapper.readValue(json,
					new TypeReference<VkApiResponse<City>>() {
					});
			
			city = response.getResponse().get(0);
			if (country != null) {
				city.assignCountry(country);
				city.setUsers(new ArrayList<User>());
				saveCityCountryPair(city, country);
				cityRepository.flush();
			}
			return city;
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return null;
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 
	}*/

	
}
