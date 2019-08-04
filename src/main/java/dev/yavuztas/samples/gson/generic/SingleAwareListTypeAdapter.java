package dev.yavuztas.samples.gson.generic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Custom TypeAdapter for GSON to handle single aware list converstion TODO
 * perhaps we consider to get rid of SingleAwareList by changing it to
 * ArrayList!
 * 
 * @author Yavuz Tas
 *
 */
public class SingleAwareListTypeAdapter extends TypeAdapter<List> {

    private Gson gson;
    private TypeAdapter<?> objectTypeAdapter;
    private TypeAdapter<List> listTypeAdapter;

    public SingleAwareListTypeAdapter(Gson gson, Class elementClassType) {

        this.gson = gson;
        // we need to carry element's type by passing the element class type to object
        // type adapter otherwise gson deserialize our objects as LinkedTreeSet which we
        // do not expect that.
        this.objectTypeAdapter = gson.getAdapter(elementClassType);
        // list adapter for only serializing do not need the type of element inside list
        // here since all the output will be String at the end.
        this.listTypeAdapter = gson.getAdapter(List.class);
    }

    @Override
    public void write(JsonWriter out, List list) throws IOException {

        /*
         * Since we do not serialize our comment list with gson we can omit this part
         * but anyway we can simply implement by reusing gson list type adapter
         */
        listTypeAdapter.write(out, list);
    }

    @Override
    public List read(JsonReader in) throws IOException {

        List deserializedObject = new ArrayList<>();

        // type of next token
        JsonToken peek = in.peek();

        // if the json field is single object just add this object to list as an
        // element
        if (JsonToken.BEGIN_OBJECT.equals(peek)) {
            deserializedObject.add(deserializeObject(in));
        }

        // if the json field is array then deserialize the objects inside
        if (JsonToken.BEGIN_ARRAY.equals(peek)) {
            in.beginArray();
            while (in.hasNext()) {
                if (JsonToken.BEGIN_OBJECT.equals(in.peek())) {
                    deserializedObject.add(deserializeObject(in));
                }
            }
            in.endArray();
        }

        return deserializedObject;
    }

    private Object deserializeObject(JsonReader in) throws IOException {
        // just use gson object type adapter
        return objectTypeAdapter.read(in);
    }
}