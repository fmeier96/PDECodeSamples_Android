package de.telekom.pde.codelibrary.samples.commonstyle.actionbar;


import android.os.Bundle;
import android.widget.Toast;
import android.support.v7.app.ActionBar.OnNavigationListener;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.samples.adapter.NavigationModeSortAdapter;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;


public class ActivityActionBarSpinner extends PDEActionBarActivity {

    private final class SpinnerListener implements OnNavigationListener {

        private final String[] options;


        private SpinnerListener(final String[] options) {
            this.options = options;
        }


        @Override
        public boolean onNavigationItemSelected(final int itemPosition, final long itemId) {

            final String selectedOption = options[itemPosition];
            Toast.makeText(ActivityActionBarSpinner.this, "You selected: " + selectedOption, Toast.LENGTH_SHORT).show();

            return true;
        }
    }


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_spinner);
        setupSortingAction();
    }


    private void setupSortingAction() {
        getSupportActionBar().setNavigationMode(android.support.v7.app.ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final String[] options = getResources().getStringArray(R.array.sort_options_array);
        final NavigationModeSortAdapter adapter = new NavigationModeSortAdapter(this,
                                                                                R.layout.actionbar_sort_item,
                                                                                options,
                                                                                getString(R.string.spinner_title));
        getSupportActionBar().setListNavigationCallbacks(adapter, new SpinnerListener(options));
    }
}
