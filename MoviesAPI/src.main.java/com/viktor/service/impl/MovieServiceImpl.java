package com.viktor.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import com.viktor.bean.Movie;
import com.viktor.exception.MovieNotFoundException;
import com.viktor.service.MovieService;

import org.json.simple.parser.ParseException;

/**
 * @author Viktor Angelutsa
 *
 */
@Service("movieService")
public class MovieServiceImpl implements MovieService {

	private final String OMDB_API = "http://www.omdbapi.com/";

	private final String THEMOVIEDB_API = "https://api.themoviedb.org/3/search/movie";
	/**
	 * This constant is an omdb requirement for search, it is given by the omdb
	 * website.
	 */
	private final String OMDB_API_KEY = "48627f9f";

	/**
	 * This constant is an themoviedb requirement for search, it is given by the
	 * themoviedb website.
	 */
	private final String THEMOVIEDB_API_KEY = "18101e844f127ac4d55f9a2448f6cf67";

	@Override
	public List<Movie> getMovieByTitle(String title, String api)
			throws ParseException, MalformedURLException, IOException, ParseException {

		String charset = "UTF-8";
		String movieTitle = String.format("%s", URLEncoder.encode(title, charset));
		URLConnection connection = null;
		List<Movie> movies = new ArrayList<Movie>();
		if (api.equals("omdb")) {
			connection = new URL(String.format("%s?t=%s&apiKey=%s", OMDB_API, movieTitle, OMDB_API_KEY))
					.openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response, "UTF-8"));
			String foundTitle = (String) jsonObject.get("Title");
			if(foundTitle != null){
				movies.add(new Movie(foundTitle, (String) jsonObject.get("Director"),
						(String) jsonObject.get("Year")));
			}
			
		} else if (api.equals("themoviedb")) {
			connection = new URL(
					String.format("%s?api_key=%s&query=%s", THEMOVIEDB_API, THEMOVIEDB_API_KEY, movieTitle))
							.openConnection();
			connection.setRequestProperty("Accept-Charset", charset);
			InputStream response = connection.getInputStream();
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new InputStreamReader(response, "UTF-8"));
			movies = transformToMovie((JSONArray) jsonObject.get("results"));
		} else {
			throw new MalformedURLException();
		}

		if(movies == null || movies.isEmpty()) {
			throw new MovieNotFoundException(title);
		}
		return movies;
	}

	/**
	 * Transforms a JSONArray object into a List<Movie> object
	 * @param jsonArray
	 * @return List<Movie>
	 */
	List<Movie> transformToMovie(JSONArray jsonArray) {
		List<Movie> movies = new ArrayList<Movie>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject)jsonArray.get(i);
			movies.add(new Movie((String) jsonObject.get("original_title"), "N/A",
					(String) jsonObject.get("release_date")));
		}
		return movies.isEmpty() ? null : movies;
	}
}
