package com.blogspot.droidista.locationupdate;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		
		SeekBar mSeekbar;
		TextView mProgressDisplay;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			wireUIElements(rootView);
			return rootView;
		}

		private void wireUIElements(View rootView) {
			mSeekbar = (SeekBar) rootView.findViewById(R.id.seekbar_frequency);
			mSeekbar.setOnSeekBarChangeListener(mSeekbarListener);
			
			mProgressDisplay = (TextView) rootView.findViewById(R.id.textview_seekbar_value);
			mProgressDisplay.setText("Seekbar is at: " + getProgressToDisplay(mSeekbar.getProgress()));
		}
		
		SeekBar.OnSeekBarChangeListener mSeekbarListener = new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) { /* Do nothing*/ }
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) { /* Do nothing*/ }
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				mProgressDisplay.setText("Seekbar is at: " + getProgressToDisplay(progress));
			}
		};
		
		private String getProgressToDisplay(int progress) {
			float actualProgress = (progress + 1) - (progress * 0.5f);
			// 1 = 1
			// 2 = 1.5
			// 18 = (19 - 9) = 10
			return String.valueOf(actualProgress);
		}
	}

}
