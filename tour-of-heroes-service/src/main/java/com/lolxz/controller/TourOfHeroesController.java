package com.lolxz.controller;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.lolxz.model.Hero;
import com.lolxz.service.HeroService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*", allowedHeaders="*")

public class TourOfHeroesController {

	@Autowired
	HeroService heroService;

	@RequestMapping(method = RequestMethod.GET, value = "/heroes", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getHeroes() {
		return new Gson().toJson(heroService.getHeroes());

	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/heroes/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String getHero(@PathVariable("id") int id) {
		return new Gson().toJson(heroService.getHeroes().stream().filter(i -> i.getId() == id).collect(Collectors.toList()).get(0));

	}

	@RequestMapping(method = RequestMethod.POST, value="/heroes/add")
	public String addHero(@RequestBody String  name) {
		int id = heroService.getHeroes().get(heroService.getHeroes().size() -1).getId() + 1;
		heroService.getHeroes().add(new Hero(id, name));
		return new Gson().toJson(heroService.getHeroes());

	}
	@RequestMapping(method = RequestMethod.PUT, value="/heroes/update")
	public String updateHero(@RequestBody Hero hero) {
		System.out.println(hero.getId() + " " + hero.getName());
		List<Hero> result =  heroService.getHeroes().stream().filter(h -> hero.getId() == h.getId()).collect(Collectors.toList());
		if(result.size() > 0) {
			result.get(0).setName(hero.getName());
		}
		return  new Gson().toJson(result.get(0));

	}

	@RequestMapping(method = RequestMethod.DELETE, value="/heroes/delete/{id}")
	public String deleteHero(@PathVariable("id") int id) {
		Iterator<Hero> iterator = heroService.getHeroes().iterator();
		while(iterator.hasNext()) {
			Hero hero = iterator.next();
			if(hero.getId() == id) {
				iterator.remove();
			}
		}
		return  new Gson().toJson(heroService.getHeroes());
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/heroes/search/{name}")
	public String deleteHero(@PathVariable("name") String name) {
		List<Hero> result =  heroService.getHeroes()
				.stream() 
				.filter(h -> h.getName().toLowerCase().startsWith(name.toLowerCase()))
				.collect(Collectors.toList());
		return new Gson().toJson(result);
	}
	








}
