package kalia.bhaskar.terminalandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class COMMANDS_LIST extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commands_list);
		ListView list=(ListView)findViewById(R.id.listview);
		String[] listString=new String[]{"ls","cd","find","pwd","clear","google","rm"};
		ArrayAdapter<String> newAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,listString);
		list.setAdapter(newAdapter);
		
		
	}
	
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}


