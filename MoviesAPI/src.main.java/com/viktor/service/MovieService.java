package com.viktor.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.viktor.bean.Movie;

import org.json.simple.parser.ParseException;

/**
 * @author Viktor Angelutsa
 *
 */
public interface MovieService {

	/**
	 * Getting the movie upon its title and the API
	 * @return Movie
	 */
	public List<Movie> getMovieByTitle(String title, String api)  throws ParseException, MalformedURLException, IOException, ParseException ;
}
