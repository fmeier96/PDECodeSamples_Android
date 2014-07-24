package de.telekom.pde.codelibrary.samples.commonstyle.actionbar;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;


public class ActivityDrawerLayout extends PDEActionBarActivity implements OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    public void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activiy_drawer_layout);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, R.string.drawer_open,
                                                  R.string.drawer_close);
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        findViewById(R.id.leftMenuFolders).setOnClickListener(this);
        findViewById(R.id.leftMenuSettings).setOnClickListener(this);
        findViewById(R.id.leftMenuShares).setOnClickListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(Gravity.LEFT);
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                }
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }


    /**
     * @brief On Click callback for the drawer items.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.leftMenuFolders) {
            Toast.makeText(this, "File", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.leftMenuShares) {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.leftMenuSettings) {
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        }

        mDrawerLayout.closeDrawers();
    }
}
