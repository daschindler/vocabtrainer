package at.davidomi.vocabtrainer;

import android.arch.persistence.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Converters {
    public static Gson gson;

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        if (gson == null) {
            InitializeGson();
        }
        return gson.fromJson(value, listType);
    }

    private static void InitializeGson() {
        gson = new Gson();
    }

    @TypeConverter
    public static String fromArrayList(List<String> list) {
        if (gson == null) {
            InitializeGson();
        }
        String json = gson.toJson(list);
        return json;
    }
}
