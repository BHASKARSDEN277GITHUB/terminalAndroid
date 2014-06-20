// writing class terminalCommands  for execution of various terminal commands ..


package kalia.bhaskar.terminalandroid;
import java.io.File;
import android.widget.EditText;
import android.widget.TextView;
 
public class terminalCommands {

	private static String command ; //to store the command string from editText
	private static String current_directory_path="/";
	private static String previous_directory_path="";
	private static String prefix="ATerminal";
	public static String CHECK_COMMAND_RESULT ;
	//making terminal name consisting of prefix and suffix . prefix remains same . suffix changes depending upon current working directory ...
	public static String terminalName=prefix+"-"+current_directory_path+">";
	public static String[] splitCommand = null;		
	public static String argument = "";
	//now writing the constructor here .. with no arguments .. empty ..
	public terminalCommands() 
	{
	//empty
	}
	
	//main task begins here ..
	
	//Reading the command
	void getCommand(EditText input)
	{
		this.command=input.getText().toString();
	}
	
	//checking the command
	
	void checkCommand(EditText input , TextView output,TextView header , String headerString  )
	{
		splitCommand = this.command.split(" ");   //split command on the white space ..
		
		//combining strs other than command 
		for(int i=0;i<splitCommand.length;i++)
		{
			argument=argument+splitCommand[i]+" ";
		}
		
		
		
		if(this.command.equals(""))  //means nothing entered and RUN button pressed ..
		{
			input.setText("");
			output.setText("NO COMMAND ENTERED");
		}
		
		if(splitCommand.length<=2)
		{	
			
		 if(command.equals("ls"))
		{
			listCommand(input,output);  //list the files in current directory ..
		}
		
		else if(command.equals("cd"))
		{
			changeToBaseDirectoryCommand(input ,output ,header,headerString); //going to the base directory ..
		}
		else if(splitCommand[0].equals("cd"))
		{
			changeDirectoryCommand(input,output ,header,headerString);
		}
		
		else if(command.equals("cd.."))
		{
			
			previousDirectorycommand(input ,output ,header,headerString);
		}
		
		else if(command.equals("clear"))
		{
			input.setText("");
			output.setText("");
		}
		else if(splitCommand[0].equals("find"))
	    {
			findCommand(input ,output ,header,headerString);
		}
		else if(command.equals("pwd"))
		{
			pwdCommand(input ,output);
		}
		
		else if(splitCommand[0].equals("rm"))
		{
			removeCommand(input ,output ,header,headerString);
		}
		
		else if(command.equals("fuck"))
	    {
			input.setText("");
		    output.setText(" \t\t\t\t\t\t BETICHOD FUCK URSELF");
		}
		
		else
		{
			input.setText("");
			output.setText(" Command Not Found");
			
		}
	
		}
		
		else
		{
			input.setText("");
			output.setText(" Invalid  Argument");
		}
	}
	
	void listCommand(EditText input ,TextView output)//ls command
	{
		output.setText("");
		
		File[] fileList ; //creating file array to store list of files ..
		File directory = new File(current_directory_path); // creating reference variable to directory with the given path ..
		fileList = directory.listFiles(); //getting list of files in current directory ..
		StringBuilder str = new StringBuilder("");
		for(int i=0;i<fileList.length;i++)
		{
			str = str.append(fileList[i].getName()+"\n");
		}
		output.setText(str);
		input.setText("");
		
		//done
	}
	
	void changeToBaseDirectoryCommand(EditText input ,TextView output , TextView header ,String abc) //cd command
	{
		
		output.setText("");
		input.setText("");
		header.setText(abc+"@ATerminal/>");
		previous_directory_path=current_directory_path;
		current_directory_path="/";
		
		//done
		
	}
	
	void changeDirectoryCommand(EditText input ,TextView output ,TextView header , String abc)
	{
		
		
		
		output.setText("");
		input.setText("");
		
		File[] fileList ; //creating file array to store list of files ..
		File directory = new File(current_directory_path); // creating reference variable to directory with the given path ..
		fileList = directory.listFiles(); //getting list of files in current directory ..
		boolean check =false;
		File name =null;
		//String filename ="xxx";
		
		
		
		
		for(int j=0;j<fileList.length; j++)
		{
			//output.setText(fileList[j].getName());
			if(fileList[j].getName().equals(splitCommand[1]))
			{
				check =true;
				name=fileList[j];
				//filename=fileList[j].getName();
				break ;
			}
			
		}
		//output.setText(filename);
		
	if(check)
	  {
		  if(name.isDirectory())
		  {
			  // do nothing ..
		  }
		  else
		  { 
			  check=false;
			  output.setText("Error : No Directory Named "+ splitCommand[1] +"exists. ");
			  
		  }
	  }
	else
	{
		output.setText("Error : No Directory Named "+ splitCommand[1] +"exists. ");
	}
		
	  
	  if(check)
	  {
		  previous_directory_path=current_directory_path;
		current_directory_path=current_directory_path+splitCommand[1]+"/";
		header.setText(abc+"@ATerminal"+current_directory_path+">");
	  }
		
	  
	
	}
	
	
	void previousDirectorycommand(EditText input ,TextView output ,TextView header , String abc)
	{
		input.setText("");
		output.setText("");
		header.setText(abc+"@ATerminal"+previous_directory_path+">");
		current_directory_path=previous_directory_path;
	}
	

	
	void findCommand(EditText input ,TextView output ,TextView header , String abc)
	{
		
		if(splitCommand.length==1)
		{
			input.setText("");
			output.setText("Invalid Argument");
		}	
		else
		{	
		input.setText("");
		output.setText("");
		
		File[] fileList ; //creating file array to store list of files ..
		File directory = new File(current_directory_path); // creating reference variable to directory with the given path ..
		fileList = directory.listFiles(); //getting list of files in current directory ..
		
		StringBuilder str = new StringBuilder("");
		int count=0;
		for(int j=0;j<fileList.length; j++)
		{
			
			if(fileList[j].getName().startsWith(splitCommand[1]) || fileList[j].getName().endsWith(splitCommand[1]))
			{
				str.append(fileList[j].getName().toString()+"\n");
				count++;
			}
			
			
		}
		
		if(count==0)
		{
			output.setText("No file found that starts or ends with given character sequence .");
		}
	    
		else
		{
			output.setText(count+" files found"+"\n \n \n"+str);
		}
		}
		
		//done
	}
	
	void pwdCommand(EditText input , TextView output)
	{
		input.setText("");
		output.setText(current_directory_path);
	}
	void removeCommand(EditText input ,TextView output ,TextView header , String abc)
	{
		if(splitCommand.length==1)
		{
			input.setText("");
			output.setText("Invalid Argument");
		}	
		else
		{	
		input.setText("");
		output.setText("");
		
		File[] fileList ; //creating file array to store list of files ..
		File directory = new File(current_directory_path); // creating reference variable to directory with the given path ..
		fileList = directory.listFiles(); //getting list of files in current directory ..
		
		boolean count=false ;
		for(int j=0;j<fileList.length; j++)
		{
			
			if(fileList[j].getName().equals(splitCommand[1]))
			{
				count=fileList[j].delete();
				break ;
			}
			
			
		}
		
		if(count)
		{
			output.setText("File not found");
		}
	    
		else
		{
			output.setText("File deleted ");
		}
		}
	}
	
	
	
	
	
}
