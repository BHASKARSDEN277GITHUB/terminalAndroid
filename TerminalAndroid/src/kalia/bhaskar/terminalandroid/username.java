package kalia.bhaskar.terminalandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class username extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.username);
	    final EditText username = (EditText)findViewById(R.id.username);
	    Button done = (Button)findViewById(R.id.done);
	    
	    
	    done.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String usernameString = username.getText().toString();
				Intent mainActivity = new Intent(getApplicationContext(),MainActivity.class);
				mainActivity.putExtra("value",usernameString);
				startActivity(mainActivity);//sending data to main Activity ...
				 username.this.finish();
				
			}
		});
		
	}
	
	 

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
