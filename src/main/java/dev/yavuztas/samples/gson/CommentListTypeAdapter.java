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
 * Custom TypeAdapter for GSON to handle list of {@link CommentModel}
 * converstion
 * 
 * @author Yavuz Tas
 *
 */
public class CommentListTypeAdapter extends TypeAdapter<List> {

    private Gson gson;
    private TypeAdapter<CommentModel> objectTypeAdapter;
    private TypeAdapter<List<CommentModel>> listTypeAdapter;

    public CommentListTypeAdapter(Gson gson) {

        this.gson = gson;
        this.objectTypeAdapter = gson.getAdapter(CommentModel.class);
        this.listTypeAdapter = gson.getAdapter(new TypeToken<List<CommentModel>>() {
        });
    }

    @Override
    public void write(JsonWriter out, List list) throws IOException {

        /*
         * Since we do not serialize our comment list with gson we can omit this part
         * but anyway we can simply implement by reusing listTypeAdapter
         */
        listTypeAdapter.write(out, list);
    }

    @Override
    public List read(JsonReader in) throws IOException {

        List<CommentModel> deserializedObject = new ArrayList<>();

        // type of next token
        JsonToken peek = in.peek();

        // if the json field is single object just add this object to list as an
        // element
        if (JsonToken.BEGIN_OBJECT.equals(peek)) {
            deserializedObject.add(deserializeObject(in));
        }

        // if the json field is array then implement normal array deserialization
        if (JsonToken.BEGIN_ARRAY.equals(peek)) {
            deserializedObject.addAll(deserializeList(in));
        }

        return deserializedObject;
    }

    private CommentModel deserializeObject(JsonReader in) throws IOException {
        // just use gson object type adapter
        return objectTypeAdapter.read(in);
    }

    private List<CommentModel> deserializeList(JsonReader in) throws IOException {
        // just use gson list type adapter
        return listTypeAdapter.read(in);
    }

}