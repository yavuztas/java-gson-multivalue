package dev.yavuztas.samples.gson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import dev.yavuztas.samples.gson.generic.SingleAwareList;
import dev.yavuztas.samples.gson.generic.SingleAwareListTypeAdapterFactory;

/**
 * Model class for response handling comments as generic by
 * {@link SingleAwareList}
 * 
 * @author Yavuz Tas
 *
 */
public class ArticleModel {

	@Expose
	private Long id;

	@Expose
	private String name;

	@JsonAdapter(SingleAwareListTypeAdapterFactory.class)
	@Expose
	private SingleAwareList<CommentModel> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SingleAwareList<CommentModel> getComments() {
		return comments;
	}

	public void setComments(SingleAwareList<CommentModel> comments) {
		this.comments = comments;
	}

}