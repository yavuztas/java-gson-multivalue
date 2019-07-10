package dev.yavuztas.samples.gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import dev.yavuztas.samples.gson.model.CommentModel;

/**
 * Custom TypeAdapter for GSON to handle {@link CommentList} converstion
 * 
 * @author Yavuz Tas
 *
 */
public class CommentListTypeAdapter extends TypeAdapter<CommentList> {

	private Gson gson;
	private TypeAdapter<CommentModel> objectTypeAdapter;
	private TypeAdapter<List<CommentModel>> listTypeAdapter;

	public CommentListTypeAdapter(Gson gson) {

		this.gson = gson;
		this.objectTypeAdapter = gson.getAdapter(new TypeToken<CommentModel>() {
		});
		this.listTypeAdapter = gson.getAdapter(new TypeToken<List<CommentModel>>() {
		});
	}

	@Override
	public void write(JsonWriter out, CommentList list) throws IOException {

		/*
		 * Since we do not serialize CommentList with gson we can omit this part but
		 * anyway we can simply implement by reusing listTypeAdapter
		 */
		listTypeAdapter.write(out, new ArrayList<CommentModel>(list));

	}

	@Override
	public CommentList read(JsonReader in) throws IOException {

		CommentList deserializedObject = new CommentList();

		// type of next token
		JsonToken peek = in.peek();

		// if the json field is single object just add this object to list as an
		// element
		if (JsonToken.BEGIN_OBJECT.equals(peek)) {
			CommentModel object = objectTypeAdapter.read(in);
			deserializedObject.add(object);
		}

		// if the json field is array then implement normal array deserialization
		if (JsonToken.BEGIN_ARRAY.equals(peek)) {
			List<CommentModel> list = listTypeAdapter.read(in);
			deserializedObject.addAll(list);
		}

		return deserializedObject;
	}
}