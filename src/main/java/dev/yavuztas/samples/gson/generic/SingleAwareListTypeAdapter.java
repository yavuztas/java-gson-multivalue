package dev.yavuztas.samples.gson.generic;

import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Custom TypeAdapter for GSON to handle {@link SingleAwareList} converstion
 * 
 * @author Yavuz Tas
 *
 */
public class SingleAwareListTypeAdapter extends TypeAdapter<SingleAwareList> {

	private Gson gson;
	private TypeAdapter<?> objectTypeAdapter;
	private TypeAdapter<?> listTypeAdapter;
	private TypeAdapter<List> listTypeAdapterForWrite;

	public SingleAwareListTypeAdapter(Gson gson, Class clazz) {

		this.gson = gson;
		this.objectTypeAdapter = gson.getAdapter(clazz);

		// we need to get parameterized type here to protect type info in runtime so
		// that Gson can convert to the correct type rather than LinkedTreeSet
		TypeToken<?> parameterized = TypeToken.getParameterized(SingleAwareList.class, clazz);
		this.listTypeAdapter = gson.getAdapter(parameterized);
		// list adapter for only serializing do not need element type here since all the
		// output will be String
		this.listTypeAdapterForWrite = gson.getAdapter(new TypeToken<List>() {
		});
	}

	@Override
	public void write(JsonWriter out, SingleAwareList list) throws IOException {

		/*
		 * Since we do not serialize CommentList with gson we can omit this part but
		 * anyway we can simply implement by reusing listTypeAdapter
		 */
		listTypeAdapterForWrite.write(out, list);

	}

	@Override
	public SingleAwareList<?> read(JsonReader in) throws IOException {

		SingleAwareList deserializedObject = new SingleAwareList();

		// type of next token
		JsonToken peek = in.peek();

		// if the json field is single object just add this object to list as an
		// element
		if (JsonToken.BEGIN_OBJECT.equals(peek)) {
			Object object = objectTypeAdapter.read(in);
			deserializedObject.add(object);
		}

		// if the json field is array then implement normal array deserialization
		if (JsonToken.BEGIN_ARRAY.equals(peek)) {
			List list = (List) listTypeAdapter.read(in);
			deserializedObject.addAll(list);
		}

		return deserializedObject;
	}
}