package de.telekom.pde.codelibrary.samples.actionbar;


import android.os.Bundle;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.adapter.NavigatioModeSortAdapter;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockFragmentActivity;


public class ActivityActionBarSpinner extends PDESherlockFragmentActivity
{

	private final class SpinnerListener implements OnNavigationListener
	{

		private final String[]	options;


		private SpinnerListener(final String[] options)
		{
			this.options = options;
		}


		@Override
		public boolean onNavigationItemSelected(final int itemPosition, final long itemId)
		{

			final String selectedOption = options[itemPosition];
			Toast.makeText(ActivityActionBarSpinner.this, "You selected: " + selectedOption, Toast.LENGTH_SHORT).show();

			return true;
		}
	}


	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar_spinner);
		setupSortationAction();
	}


	private void setupSortationAction()
	{
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		getSupportActionBar().setDisplayShowTitleEnabled(false);

		final String[] options = getResources().getStringArray(R.array.sort_options_array);
		final NavigatioModeSortAdapter adapter = new NavigatioModeSortAdapter(this, R.layout.actionbar_sort_item,
				options, getString(R.string.spinner_title));
		getSupportActionBar().setListNavigationCallbacks(adapter, new SpinnerListener(options));
	}
}
