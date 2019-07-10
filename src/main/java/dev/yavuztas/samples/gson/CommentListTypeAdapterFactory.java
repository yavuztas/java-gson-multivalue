package dev.yavuztas.samples.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Custom TypeAdapterFactory for GSON to handle {@link CommentList} converstion
 * 
 * @author Yavuz Tas
 *
 */
public class CommentListTypeAdapterFactory implements TypeAdapterFactory {

	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

		if (!type.getType().equals(CommentList.class)) {
			return null;
		}

		return (TypeAdapter<T>) new CommentListTypeAdapter(gson);
	}

}
