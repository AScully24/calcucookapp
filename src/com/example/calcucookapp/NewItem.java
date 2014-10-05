package com.example.calcucookapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class NewItem extends ActionBarActivity {
	public static final String ITEM_NAME = "ITEM_NAME", ITEM_TIME = "ITEM_TIME";
	EditText itemName, itemTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_item);
		itemName = (EditText) findViewById(R.id.etItemName);
		itemTime = (EditText) findViewById(R.id.etItemTime);
	}
	
	public void onAddItemPress(View v){
		Intent returnIntent = new Intent();
		String name = itemName.getText().toString();
		String time = itemTime.getText().toString();
		
//		if (name.equals("") || time.equals("")) {
//			
//		} else {
			returnIntent.putExtra(ITEM_NAME, name);
			returnIntent.putExtra(ITEM_TIME, time);	
		//}
		
		setResult(RESULT_OK, returnIntent);
		finish();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.new_item, menu);
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
