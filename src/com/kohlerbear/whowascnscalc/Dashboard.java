package com.kohlerbear.whowascnscalc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.Toast;

public class Dashboard extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dashboard);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						PlaceholderFragment.newInstance(position + 1)).commit();
		ConfigTool ct = new ConfigTool(this);
	switch (position)
	{
		case 0://keep an eye on this..
		//don't touch me.. for loading a fragment?
		break;
		case 1://Create new projection
			Intent newProjectionIntent = new Intent(Dashboard.this, MainActivity.class);
			newProjectionIntent.putExtra("origin", "dashboard");
			if (!ct.dbEmpty())
				newProjectionIntent.putExtra("liftPattern", ct.populateArrayBasedOnDatabase());
			else
			{
				String[] defaultPattern = {"Bench", "Squat", "Rest", "OHP", "Deadlift", "Rest"  };			
				newProjectionIntent.putExtra("liftPattern", defaultPattern);
			}
				startActivity(newProjectionIntent);
			break;
		case 2://View existing projection
			if (!ct.dbEmpty())
			{
			Intent viewExistingProjectionIntent = new Intent(Dashboard.this, ThirdScreenActivity.class);
			viewExistingProjectionIntent.putExtra("origin", "dashboard");
			viewExistingProjectionIntent.putExtra("liftPattern", ct.populateArrayBasedOnDatabase());
			startActivity(viewExistingProjectionIntent);
			}
			else
				Toast.makeText(Dashboard.this, "No previous projection exists!", Toast.LENGTH_SHORT).show();

/*		case 3:
			Intent wintent = new Intent(Dashboard.this, TestNavDrawerActivity.class);
			startActivity(wintent);
			break;
			*/
			
	}
	
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);//keep an eye on this..
            break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		case 4:
			mTitle = getString(R.string.title_section4);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

/*	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.dashboard, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}*/

/*	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

/*		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_dashboard,
					container, false);
			return rootView;
		}
*/
		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((Dashboard) activity).onSectionAttached(getArguments().getInt(
					ARG_SECTION_NUMBER));
		}
	}

}