package dev.yavuztas.samples.gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import dev.yavuztas.samples.gson.model.CommentModel;

/**
 * Custom TypeAdapterFactory for GSON to handle list of {@link CommentModel}
 * converstion
 * 
 * @author Yavuz Tas
 *
 */
public class CommentListTypeAdapterFactory implements TypeAdapterFactory {

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        // check the element type of list
        ParameterizedType parameterizedType = ParameterizedType.class.cast(type.getType());
        Type elementType = parameterizedType.getActualTypeArguments()[0];
        if (!elementType.equals(CommentModel.class)) {
            return null;
        }

        return (TypeAdapter<T>) new CommentListTypeAdapter(gson);
    }

}
