package de.telekom.pde.codelibrary.samples.commonstyle.actionbar;


import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.view.Menu;

import android.view.View;
import android.view.View.OnClickListener;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;


public class ActivityActionBarSplit extends PDEActionBarActivity {

	private int	countActions	= 4;


	@Override
	public void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_action_bar_split);
		setupButtons();
	}


	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		for (int i = 0; i < countActions; i++) {
            MenuItemCompat.setShowAsAction(menu.add("Action").setIcon(R.drawable.action_share),
                    android.support.v4.view.MenuItemCompat.SHOW_AS_ACTION_IF_ROOM
                            | android.support.v4.view.MenuItemCompat.SHOW_AS_ACTION_WITH_TEXT);

		}

		return true;
	}


	private void setupButtons()	{
		findViewById(R.id.activity_action_bar_split_add).setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(final View v)
			{
				countActions++;
				supportInvalidateOptionsMenu();
			}
		});

		findViewById(R.id.activity_action_bar_split_remove).setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(final View v)
			{
				countActions = Math.max(0, countActions - 1);
				supportInvalidateOptionsMenu();
			}
		});

	}

}
