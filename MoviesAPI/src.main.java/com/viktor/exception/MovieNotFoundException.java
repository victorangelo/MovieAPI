package com.viktor.exception;

/**
 * @author Viktor Angelutsa
 *
 */
public class MovieNotFoundException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public MovieNotFoundException(String title) {
        super("Could not find movie: " + title + ".");
    }
}
