package kalia.bhaskar.terminalandroid;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	
	//variable declaration
	public static final int MENU_ABOUT=Menu.FIRST;
	public static final int MENU_EXIT=Menu.FIRST+1;
	public static final int MENU_CHANGE_USER_NAME=Menu.FIRST+2;
	public static terminalCommands KEY = new terminalCommands();  // I used the object name KEY here because this object reference is going to be the key of this application ... lol .. 
	public String username="user";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  
		   
		
		
		  final EditText input = (EditText)findViewById(R.id.editText1);
		  Button commands_list = (Button)findViewById(R.id.button2); //command list button
		  Button run = (Button)findViewById(R.id.button1);//run button
		  final TextView screen = (TextView)findViewById(R.id.outputscreen);//output screen textview
		  
		  screen.setMovementMethod(new ScrollingMovementMethod());
		  screen.setText("OUTPUT HERE");
		  
		  final TextView header = (TextView)findViewById(R.id.header);//header textview ..
		  
		  header.setText("AndroidUser@ATerminal/>");
		  
		  
		  
		 
		  
		  Intent i = getIntent();
		  String usernameChanged = i.getStringExtra("value");
		  
		  
		  
		  if(usernameChanged != null)
			 {
			  header.setText(usernameChanged+"@ATerminal/>"); //when username is changed ..
			  username=usernameChanged;
			 }
		  
		  
		  
		  
		  
		  //functions definitions ..
		   commands_list.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i= new Intent(getApplicationContext(),COMMANDS_LIST.class);
				startActivity(i);
				
				
			}
		});
		
			run.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					String[] str = input.getText().toString().split(" ");
					
					if(str[0].equals("google"))
					{
						screen.setText("");
						input.setText("");
						
						Intent google = new Intent(Intent.ACTION_WEB_SEARCH);
						google.putExtra(SearchManager.QUERY,str[1]);
						startActivity(google);
						 
						
						
					}
					
					else
					{	
					KEY.getCommand(input);
					KEY.checkCommand(input, screen,header ,username);
					}
					
					
					
				}
			});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		/* Creates the menu items */
		
		    menu.add(0, MENU_ABOUT, 0, "ABOUT");
		    menu.add(0,MENU_EXIT,0,"QUIT");
		    menu.add(0,MENU_CHANGE_USER_NAME,0,"CHANGE USER ");
		

		/* Handles item selections */
		
		return true;
	}

	/* Handles item selections */
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case MENU_ABOUT:
	        Intent about = new Intent(this,about.class);
	        startActivity(about);
	        
	        return true;
	    case MENU_EXIT:
	        finish();
	        return true;
	    case MENU_CHANGE_USER_NAME:
	        Intent user = new Intent(this,username.class);
	        startActivity(user);
	       // MainActivity.this.finish();
	        return true;

	        
	    }
	    return false;
	}
}

 

