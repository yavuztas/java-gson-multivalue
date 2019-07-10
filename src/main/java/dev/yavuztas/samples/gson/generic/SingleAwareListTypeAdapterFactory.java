package dev.yavuztas.samples.gson.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * Custom TypeAdapterFactory for GSON to handle {@link SingleAwareList}
 * converstion
 * 
 * @author Yavuz Tas
 *
 */
public class SingleAwareListTypeAdapterFactory implements TypeAdapterFactory {

	@Override
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

		if (!type.getRawType().equals(SingleAwareList.class)) {
			return null;
		}

		try {
			Type elementType = ((ParameterizedType) type.getType()).getActualTypeArguments()[0];
			Class<?> elementClassType = Class.forName(elementType.getTypeName());
			return (TypeAdapter<T>) new SingleAwareListTypeAdapter(gson, elementClassType);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}
