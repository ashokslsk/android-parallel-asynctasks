package dev.ash.catalog;


import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView output;
	ProgressBar Ashupb;
	List<ashTask> task; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		Initialize the TextView for vertical scrolling
		output = (TextView) findViewById(R.id.textView);
		output.setMovementMethod(new ScrollingMovementMethod());
		Ashupb = (ProgressBar) findViewById(R.id.progressBar1);
		Ashupb.setVisibility(View.INVISIBLE);
		
		task= new ArrayList<>();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.action_do_task) {
		ashTask	task = new ashTask();
		task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "Ashu 1","Ashu 2 ","Ashu 3");
		}
		return false;
	}

	protected void updateDisplay(String message) {
		output.append(message + "\n");
	}
	
	private class ashTask extends AsyncTask<String, String, String>{

		@Override
		protected void onPreExecute() {
		updateDisplay("Ash task Starting");
		
		if (task.size()==0) {
			Ashupb.setVisibility(View.VISIBLE);
		}
		task.add(this);
		
		}
		
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			for (int i = 0; i < params.length; i++) {
				publishProgress("Working with Ashu "+params[i] );
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "Ash Task Completed  ";
		}
		
		@Override
		protected void onPostExecute(String result) {
			updateDisplay(result);
			
			task.remove(this);
			if (task.size()==0) {
				Ashupb.setVisibility(View.INVISIBLE);
			}
		}
		
		@Override
		protected void onProgressUpdate(String... values) {
		 updateDisplay(values[0]);
		}
		
	}

}