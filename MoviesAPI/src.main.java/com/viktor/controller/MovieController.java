package com.viktor.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viktor.bean.Movie;
import com.viktor.exception.MovieNotFoundException;
import com.viktor.service.MovieService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.simple.parser.ParseException;

/**
 * @author Viktor Angelutsa
 *
 */
@RestController
@RequestMapping("movies")
@Api(value = "Movies API")
public class MovieController {

	private static final Logger logger = Logger.getLogger(MovieController.class);

	@Autowired
	private MovieService movieService;

	/**
	 * Gets a Movie by Title and API
	 * 
	 * @param String
	 *            movieTitle, String apiName
	 * @return Movie
	 **/
	@GetMapping("/{movieTitle}")
	@ApiOperation(value = "Finds a Movie", notes = "Returns a movie by Title from the specified API")
	public List<Movie> getMovie(@PathVariable String movieTitle, @RequestParam("api") String apiName) {
		List<Movie>  movies = null;
		try {
			movies = movieService.getMovieByTitle(movieTitle, apiName);
		} catch (MalformedURLException e) {
			logger.error(e);
		} catch (ParseException e) {
			logger.error(e);
		} catch (IOException e) {
			logger.error(e);
		} catch (MovieNotFoundException e) {
			logger.error(e);
		}
		return movies;
	}
}
