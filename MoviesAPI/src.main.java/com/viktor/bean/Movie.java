package com.viktor.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Viktor Angelutsa
 *
 */
@JsonPropertyOrder({ "title", "director", "year" })
@ApiModel("Movie Model")
public class Movie {

	@ApiModelProperty(value = "title", required = true)
	private String title;

	@ApiModelProperty(value = "director", required = true)
	private String director;

	@ApiModelProperty(value = "year", required = true)
	private String year;

	public Movie(){
		
	}
	
	@JsonCreator
	public Movie(@JsonProperty("title") String title, @JsonProperty("director") String director,
			@JsonProperty("year") String year) {
		this.title = title;
		this.director = director;
		this.year = year;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}
	
	@Override
	public String toString(){
		return String.format("Title : %s, Director : %s, Year : %s ", this.title, this.director, this.year);
	}
}
