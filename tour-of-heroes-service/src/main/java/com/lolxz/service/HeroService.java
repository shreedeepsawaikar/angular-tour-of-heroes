package com.lolxz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lolxz.model.Hero;

@Service
public class HeroService {
	private static List<Hero> heroes;
	
	{
			heroes = new ArrayList<Hero>();
			heroes.add(new Hero(11, "Mr. Nice"));
			heroes.add(new Hero(12, "Narco"));
			heroes.add(new Hero(13, "Bombasto"));
			heroes.add(new Hero(14, "Celeritas"));
			heroes.add(new Hero(15, "Magneta"));
			heroes.add(new Hero(16, "RubberMan"));
			heroes.add(new Hero(17, "Dynama"));
			heroes.add(new Hero(18, "Dr IQ"));
			heroes.add(new Hero(19, "Magma"));
			heroes.add(new Hero(20, "Tornado"));
		}
	public List<Hero> getHeroes() {
		return heroes;
	}

}
