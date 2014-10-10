package com.example.calcucookapp;

import java.util.ArrayList;
import java.util.Collections;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class SortedFoodItems extends ActionBarActivity {
	
	TextView sortedData;
	ArrayList<Food> foodItems;
	String resultText  ="";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sorted_food_items);
		
		sortedData = (TextView) findViewById(R.id.tvSortedItems);
		foodItems = (ArrayList<Food>) getIntent().getSerializableExtra(MainActivity.FOOD_ITEMS);
		
		Collections.sort(foodItems, new FoodTimeComparator());
		
		for (Food f : foodItems) {
			resultText +=  f.getFoodDetails() + "\n";
		}
		
		sortedData.setText(resultText);
		
	}

	
	public void returnToItemList(View v){
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sorted_food_items, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
