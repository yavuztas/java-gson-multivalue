package dev.yavuztas.samples.gson.model;

import com.google.gson.annotations.Expose;

/**
 * Model class for comment response structure
 * 
 * @author Yavuz Tas
 *
 */
public class CommentModel {

	@Expose
	private Long id;

	@Expose
	private String text;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
