package de.telekom.pde.codelibrary.samples.actionbar;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.View.OnClickListener;
import com.actionbarsherlock.view.MenuItem;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockFragmentActivity;
import de.telekom.pde.codelibrary.ui.utils.PDEMenuItemUtils;


public class ActivityDrawerLayout extends PDESherlockFragmentActivity implements OnClickListener
{

	private DrawerLayout			drawerLayout;
	private ActionBarDrawerToggle	drawerToggle;


	@Override
	public void onCreate(Bundle arg0)
	{
		super.onCreate(arg0);
		setContentView(R.layout.activiy_drawer_layout);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close);
		drawerLayout.setDrawerListener(drawerToggle);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);

		findViewById(R.id.leftMenuFolders).setOnClickListener(this);
		findViewById(R.id.leftMenuSettings).setOnClickListener(this);
		findViewById(R.id.leftMenuShares).setOnClickListener(this);

	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (drawerToggle.onOptionsItemSelected(PDEMenuItemUtils.getMenuItem(item)))
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}


	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}


	@Override
	public void onClick(View v)
	{
		drawerLayout.closeDrawers();
	}
}
