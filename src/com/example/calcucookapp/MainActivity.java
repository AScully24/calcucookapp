package com.example.calcucookapp;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	public static final String FOOD_ITEMS = "FOOD_ITEMS";
	ListView lvFoodItem;
	ArrayList<String> listStringsForLV;
	ArrayAdapter<String> lvAdapter;
	ArrayList<Food> foodItemArray; // Data to be sent to the next activity.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lvFoodItem = (ListView) findViewById(R.id.lvFood);
		listStringsForLV = new ArrayList<String>();
		listStringsForLV.add("Add Item");
		
		foodItemArray = new ArrayList<Food>();
		
		testData();
		
		lvAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listStringsForLV);
		lvFoodItem.setAdapter(lvAdapter);
		
		listViewClickListeners();
	}
	
	// Inputs quick test data for debugging.
	private void testData(){
		listStringsForLV.add("a - 2 min(s)");
		listStringsForLV.add("b - 1 min(s)");
		listStringsForLV.add("c - 3 min(s)");
		
		
		foodItemArray.add(new Food("a",2));
		foodItemArray.add(new Food("b",1));
		foodItemArray.add(new Food("c",3));
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {	
				String itemName = data.getStringExtra(NewItem.ITEM_NAME);
				String itemTime = data.getStringExtra(NewItem.ITEM_TIME);
				itemTime.trim();
				int time = Integer.parseInt(itemTime);
				
				foodItemArray.add(new Food(itemName,time));
				
				listStringsForLV.add(itemName + " - " + itemTime + "min(s)");
				lvAdapter.notifyDataSetChanged();
			}
		}
		
	}
	
	// Separate function for the ListView OnClickListener.
	//Opens a new activity for the user to input the item name and cooking time.
	private void listViewClickListeners() {
		lvFoodItem.setOnItemClickListener(new OnItemClickListener() {
			int requestCode = 1;
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int id,
					long position) {
				if (position >= 0) {
					Intent newItem = new Intent(view.getContext(), NewItem.class);
					startActivityForResult(newItem, requestCode);
				}
			}
		});
		
		lvFoodItem.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int id, long position) {
				if (position > 0) {
					foodItemArray.remove((int)position - 1);
					listStringsForLV.remove((int)position);
					lvAdapter.notifyDataSetChanged();
				}
				return true;
			}
		});
	}
	
	// Will allow the user to view the order in which items should be cooked.
	public void onSetTimerPress(View v){
		Bundle bundle = new Bundle();
		bundle.putSerializable(FOOD_ITEMS, foodItemArray);
		
		Intent dataToSort = new Intent(this, SortedFoodItems.class);
		dataToSort.putExtras(bundle);
		
		startActivity(dataToSort);
	}
	
	// Lets the user exit the application.
	/*
	 Currently
		Closes the application with no prompt
	 Plans
	 	Prompt user to confirm leaving
	 */
	public void onExitPress(View v){
//		Intent intent = new Intent(Intent.ACTION_MAIN);
//		intent.addCategory(Intent.CATEGORY_HOME);
//		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		startActivity(intent);
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
