package dev.yavuztas.samples.gson.model;

import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.JsonAdapter;

import dev.yavuztas.samples.gson.generic.SingleAwareListTypeAdapterFactory;

/**
 * Model class for response handling comments with generic {@link TypeAdapter}
 * by {@link SingleAwareListTypeAdapterFactory}
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