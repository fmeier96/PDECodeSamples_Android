package de.telekom.pde.codelibrary.samples.commonstyle.actionbar;


import android.os.Bundle;
import android.view.Menu;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;


public class ActivityActionBarStandard extends PDEActionBarActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_standard);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }
}
