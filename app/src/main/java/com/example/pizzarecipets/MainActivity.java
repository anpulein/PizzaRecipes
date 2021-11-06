package com.example.pizzarecipets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<PizzaRecipeItem> pizzaRecipeItems;
    private RecyclerView recyclerView; // Связывает с JavaCode
    private RecyclerView.Adapter adapter; // Мост между RecyclerView и данными (управляет неполным заполнением)
    private RecyclerView.LayoutManager layoutManager; // Управляет расположением элементов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaRecipeItems = new ArrayList<>(); // Creating list recipes
        ArrayList<Integer> drawableItems = new ArrayList<>();
        addImageResource(drawableItems);


        try {
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray recipeArr = obj.getJSONArray("recipe");
            for (int i = 0; i < recipeArr.length(); i++) {
                JSONObject recipeDetail = recipeArr.getJSONObject(i);
                pizzaRecipeItems.add(new PizzaRecipeItem(drawableItems.get(i), recipeDetail.getString("title"),
                        recipeDetail.getString("description"), recipeDetail.getString("text")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true); // Для фиксированного места, для быстроты работы приложения
        adapter = new PizzaRecipeAdapter(pizzaRecipeItems, this);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("recipe.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    static void addImageResource(ArrayList<Integer> arr) {
        arr.add(R.drawable.pizza_1);
        arr.add(R.drawable.pizza_2);
        arr.add(R.drawable.pizza_3);
        arr.add(R.drawable.pizza_4);
        arr.add(R.drawable.pizza_5);
        arr.add(R.drawable.pizza_6);
        arr.add(R.drawable.pizza_7);
        arr.add(R.drawable.pizza_8);
        arr.add(R.drawable.pizza_9);
        arr.add(R.drawable.pizza_10);
    }
}