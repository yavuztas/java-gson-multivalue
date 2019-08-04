package dev.yavuztas.samples.gson.generic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

import dev.yavuztas.samples.gson.model.CommentModel;

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

        // proceed only if the incoming type is a parameterized type
        if (!ParameterizedType.class.isInstance(type.getType())) {
            return null;
        }

        ParameterizedType parameterizedType = ParameterizedType.class.cast(type.getType());
        Type elementType = parameterizedType.getActualTypeArguments()[0];
        Class rawElementType = Class.class.cast(elementType);

        // and proceed only if the element type is CommentModel
        if (!rawElementType.equals(CommentModel.class)) {
            return null;
        }

        return (TypeAdapter<T>) new SingleAwareListTypeAdapter(gson, rawElementType);
    }

}
