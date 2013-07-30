package de.telekom.pde.codelibrary.samples.actionbar;


import android.os.Bundle;

import com.actionbarsherlock.view.Menu;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;



public class ActivityActionBarStandard extends PDESherlockActivity
{

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_bar_standard);
	}


	@Override
	public boolean onCreateOptionsMenu(final Menu menu)
	{
		getSupportMenuInflater().inflate(R.menu.menu_example, menu);
		return true;
	}
}
