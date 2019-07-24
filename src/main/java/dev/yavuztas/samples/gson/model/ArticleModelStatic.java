package dev.yavuztas.samples.gson.model;

import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import dev.yavuztas.samples.gson.CommentListTypeAdapterFactory;

/**
 * Model class for response handling comments with static {@link TypeAdapter} by
 * {@link CommentListTypeAdapterFactory}
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
	private List<CommentModel> comments;

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

	public List<CommentModel> getComments() {
		return comments;
	}

	public void setComments(List<CommentModel> comments) {
		this.comments = comments;
	}

}