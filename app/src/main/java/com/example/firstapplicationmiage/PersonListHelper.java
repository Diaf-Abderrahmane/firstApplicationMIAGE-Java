package com.example.firstapplicationmiage;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersonListHelper {

    private static final String PREF_NAME = "PersonListPref";
    private static final String PERSON_LIST_KEY = "personList";

    // Save list to SharedPreferences
    public static void savePersonList(Context context, List<Person> personList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        Gson gson = new Gson();
        String json = gson.toJson(personList);
        editor.putString(PERSON_LIST_KEY, json);
        editor.apply(); // Save the data asynchronously
    }

    // Load list from SharedPreferences
    public static List<Person> loadPersonList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(PERSON_LIST_KEY, null);

        Type type = new TypeToken<ArrayList<Person>>() {}.getType();
        return json == null ? new ArrayList<>() : gson.fromJson(json, type);
    }

    public static void deletePerson(Context context, Person personToDelete) {
        // Load the existing person list
        List<Person> personList = loadPersonList(context);

        // Remove the person from the list
        personList.removeIf(person -> person.getId().equals(personToDelete.getId())); // Assuming email is unique

        // Save the updated list back to SharedPreferences
        savePersonList(context, personList);
    }
}
