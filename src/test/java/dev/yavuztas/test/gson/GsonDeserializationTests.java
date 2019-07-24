package dev.yavuztas.test.gson;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dev.yavuztas.samples.gson.model.ArticleModel;
import dev.yavuztas.samples.gson.model.ArticleModelStatic;
import dev.yavuztas.samples.gson.model.CommentModel;
import junit.framework.Assert;

/**
 * Unit tests for deserialization operations with Gson
 * 
 * @author Yavuz Tas
 *
 */
public class GsonDeserializationTests {

	private Gson gson;
	private String mockResponseSingleValue;
	private String mockResponseMultiValue;

	@Before
	public void setup() {

		// initialize gson instance
		this.gson = new Gson();

		// prepare the mock data

		JsonObject response = new JsonObject();
		response.addProperty("id", 1);
		response.addProperty("name", "sample article");

		JsonObject comment = new JsonObject();
		comment.addProperty("id", 1);
		comment.addProperty("text", "some comment text");

		response.add("comments", comment);

		this.mockResponseSingleValue = response.toString();

		response = new JsonObject();
		response.addProperty("id", 1);
		response.addProperty("name", "sample article");

		JsonObject comment1 = new JsonObject();
		comment1.addProperty("id", 1);
		comment1.addProperty("text", "some comment text");

		JsonObject comment2 = new JsonObject();
		comment2.addProperty("id", 2);
		comment2.addProperty("text", "some another comment text");

		JsonArray array = new JsonArray();
		array.add(comment1);
		array.add(comment2);

		response.add("comments", array);

		this.mockResponseMultiValue = response.toString();

	}

	@Test
	public void gsonDeserializeStaticMultipleValueTest() {

		ArticleModelStatic model = gson.fromJson(this.mockResponseMultiValue, ArticleModelStatic.class);

		Assert.assertEquals(ArticleModelStatic.class, model.getClass());
		Assert.assertEquals(model.getComments().get(0).getClass(), CommentModel.class);

		Assert.assertEquals(1, model.getId().longValue());
		Assert.assertEquals("sample article", model.getName());

		Assert.assertEquals(2, model.getComments().size());
		Assert.assertEquals(1, model.getComments().get(0).getId().longValue());
		Assert.assertEquals("some comment text", model.getComments().get(0).getText());
		Assert.assertEquals(2, model.getComments().get(1).getId().longValue());
		Assert.assertEquals("some another comment text", model.getComments().get(1).getText());

	}

	@Test
	public void gsonDeserializeStaticSingleValueTest() {

		ArticleModelStatic model = gson.fromJson(this.mockResponseSingleValue, ArticleModelStatic.class);

		Assert.assertEquals(ArticleModelStatic.class, model.getClass());
		Assert.assertEquals(model.getComments().get(0).getClass(), CommentModel.class);

		Assert.assertEquals(1, model.getId().longValue());
		Assert.assertEquals("sample article", model.getName());

		Assert.assertEquals(1, model.getComments().size());
		Assert.assertEquals(1, model.getComments().get(0).getId().longValue());
		Assert.assertEquals("some comment text", model.getComments().get(0).getText());

	}

	@Test
	public void gsonDeserializeGenericMultipleValueTest() {

		ArticleModel model = gson.fromJson(this.mockResponseMultiValue, ArticleModel.class);

		Assert.assertEquals(ArticleModel.class, model.getClass());
		Assert.assertEquals(model.getComments().get(0).getClass(), CommentModel.class);

		Assert.assertEquals(1, model.getId().longValue());
		Assert.assertEquals("sample article", model.getName());

		Assert.assertEquals(2, model.getComments().size());
		Assert.assertEquals(1, model.getComments().get(0).getId().longValue());
		Assert.assertEquals("some comment text", model.getComments().get(0).getText());
		Assert.assertEquals(2, model.getComments().get(1).getId().longValue());
		Assert.assertEquals("some another comment text", model.getComments().get(1).getText());

	}

	@Test
	public void gsonDeserializeGenericSingleValueTest() {

		ArticleModel model = gson.fromJson(this.mockResponseSingleValue, ArticleModel.class);

		Assert.assertEquals(ArticleModel.class, model.getClass());
		Assert.assertEquals(model.getComments().get(0).getClass(), CommentModel.class);

		Assert.assertEquals(1, model.getId().longValue());
		Assert.assertEquals("sample article", model.getName());

		Assert.assertEquals(1, model.getComments().size());
		Assert.assertEquals(1, model.getComments().get(0).getId().longValue());
		Assert.assertEquals("some comment text", model.getComments().get(0).getText());

	}

}
