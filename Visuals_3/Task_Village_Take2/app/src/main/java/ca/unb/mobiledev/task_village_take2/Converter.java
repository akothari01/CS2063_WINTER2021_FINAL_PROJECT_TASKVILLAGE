package ca.unb.mobiledev.task_village_take2;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Converter {
    @TypeConverter
    public static ArrayList<building> fromString(String value) {
        Type listType = new TypeToken<ArrayList<building>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<building> list) {
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<building>>(){}.getType();
        String json = gson.toJson(list, listType);
        return json;
    }

    @TypeConverter
    public static LocalDate toDate(String dateString) {
        if (dateString == null) {
            return null;
        } else {
            return LocalDate.parse(dateString);
        }
    }

    @TypeConverter
    public static String toDateString(LocalDate date) {
        if (date == null) {
            return null;
        } else {
            return date.toString();
        }
    }

}
