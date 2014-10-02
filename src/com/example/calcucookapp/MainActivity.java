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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	ListView foodItems;
	ArrayList<String> listStrings;
	ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		foodItems = (ListView) findViewById(R.id.lvFood);
		listStrings = new ArrayList<String>();
		listStrings.add("Add Item");

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listStrings);
		foodItems.setAdapter(adapter);
		
		additem();
	}
	
	// Separate function for the ListView OnClickListener.
	//Opens a new activity for the user to input the item name and cooking time.
	private void additem() {
		foodItems.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int id,
					long position) {
				if (position >= 0) {
					Intent i = new Intent();
					listStrings.add("Cheese - 20 mins");
					adapter.notifyDataSetChanged();
				}
			}
		});
	}
	
	// Will allow the user to view the order in which items should be cooked.
	public void onSetTimerPress(){
		// Place holder for set timer button. To be completed
	}
	
	// Lets the user exit the application.
	/*
	 Currently
		Closes the application with no prompt
	 Plans
	 	Prompt user to confirm leaving
	 */
	public void onExitPress(){
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
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
