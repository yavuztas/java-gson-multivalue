package dev.yavuztas.samples.gson.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import dev.yavuztas.samples.gson.CommentList;
import dev.yavuztas.samples.gson.CommentListTypeAdapterFactory;

/**
 * Model class for response handling comments as static by {@link CommentList}
 * 
 * @author Yavuz Tas
 *
 */
public class ArticleModelStatic {

	@Expose
	private Long id;

	@Expose
	private String name;

	@JsonAdapter(CommentListTypeAdapterFactory.class)
	@Expose
	private CommentList comments;

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

	public CommentList getComments() {
		return comments;
	}

	public void setComments(CommentList comments) {
		this.comments = comments;
	}

}