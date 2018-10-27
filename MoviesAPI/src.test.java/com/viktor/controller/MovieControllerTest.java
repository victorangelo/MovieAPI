package com.viktor.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.mockito.BDDMockito;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import com.viktor.bean.Movie;
import com.viktor.service.MovieService;

/**
 * @author Viktor Angelutsa
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(MovieController.class)
public class MovieControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MovieService service;

	@Test
	public void givenMovies_whenGetMovies_OMDB_API_thenReturnJsonArray() throws Exception {

		Movie movie = new Movie("Viktor", "Viktor Angelutsa", "2018");

		List<Movie> allMovies = Arrays.asList(movie);

		BDDMockito.given(service.getMovieByTitle("Viktor", "omdb")).willReturn(allMovies);

		mockMvc.perform(get("/movies/Viktor?api=omdb").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].title", is(movie.getTitle())));
	}

	@Test
	public void givenMovies_whenGetMovies_THEMOVIEDB_API_thenReturnJsonArray() throws Exception {

		Movie movie1 = new Movie("Viktor 1", "N/A", "2018-01-01");
		Movie movie2 = new Movie("Viktor 2", "N/A", "2015-02-02");

		List<Movie> allMovies = Arrays.asList(movie1, movie2);

		BDDMockito.given(service.getMovieByTitle("Viktor", "themoviedb")).willReturn(allMovies);

		mockMvc.perform(get("/movies/Viktor?api=themoviedb").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].title", is(movie1.getTitle())))
				.andExpect(jsonPath("$[1].title", is(movie2.getTitle())));
	}

	@Test
	public void getThrowsExceptionIfMovieNotFound() throws Exception {
		mockMvc.perform(get("/movies/John?api=omdb")).andExpect(status().isOk()).andExpect(forwardedUrl(null));
		mockMvc.perform(get("/movies/John?api=themoviedb")).andExpect(status().isOk()).andExpect(forwardedUrl(null));
	}
}
