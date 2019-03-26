package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json)  {
        Sandwich sandwich = new Sandwich();
        if (json != null && !json.isEmpty()){

            try {
                JSONObject sandwichJSON = new JSONObject(json);
                //parse 'name' Object
                JSONObject name = sandwichJSON.getJSONObject("name");

                sandwich.setMainName(name.getString("mainName"));

                JSONArray jsonArray = name.getJSONArray("alsoKnownAs");
                if (jsonArray != null) {
                    List<String> alsoKnownAsList = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        alsoKnownAsList.add(jsonArray.getString(i));
                    }
                    sandwich.setAlsoKnownAs(alsoKnownAsList);
                }
                //parse placeOfOrigin,description,image and ingredients
                sandwich.setPlaceOfOrigin(sandwichJSON.getString("placeOfOrigin"));
                sandwich.setDescription(sandwichJSON.getString("description"));
                sandwich.setImage(sandwichJSON.getString("image"));
                JSONArray ingredientsJSONArr=sandwichJSON.getJSONArray("ingredients");

                if (ingredientsJSONArr!=null){
                    List<String> ingredientsList=new ArrayList<>();
                    for (int x=0;x<ingredientsJSONArr.length();x++){
                       ingredientsList.add(ingredientsJSONArr.getString(x));
                      }
                      sandwich.setIngredients(ingredientsList);
                }

            }catch (JSONException e){
                e.printStackTrace();
            }


    }else {return null;}
        return sandwich;
    }
}
