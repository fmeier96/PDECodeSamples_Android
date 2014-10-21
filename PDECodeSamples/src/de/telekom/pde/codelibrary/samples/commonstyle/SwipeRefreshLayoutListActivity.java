package de.telekom.pde.codelibrary.samples.commonstyle;

/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDEArrayAdapter;
import de.telekom.pde.codelibrary.ui.layout.PDESwipeRefreshLayout;


//----------------------------------------------------------------------------------------------------------------------
// CircleRefreshListActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief Sample of a refreshing list, which shows a Telekom-style activity indicator (circle) while processing.
 */
public class SwipeRefreshLayoutListActivity extends PDEActionBarActivity {
    private PDESwipeRefreshLayout mSwipeView;
    private int mListCreateCount = 0;

    // number of list elements
    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 100;
    // the pde list view
    private PDEListView mList;

    // Dataset
    ArrayList<String> mDataset;
    private PDEArrayAdapter<String> mMainAdapter;


    /**
     * @brief Overriden onCreate
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_refresh_list);
        mSwipeView = (PDESwipeRefreshLayout) findViewById(R.id.swipe);

        // get pde list view
        mList = (PDEListView) findViewById(R.id.pde_list);

        // create data set
        mDataset = new ArrayList<String>(NUMBER_OF_LIST_ITEMS_SHOWN);
        createDataSet();

        // set refresh listener
        mSwipeView.setOnRefreshListener(new PDESwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //swipeView.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeView.setRefreshing(false);
                        mListCreateCount++;
                        createDataSet();
                        mMainAdapter.notifyDataSetChanged();

                    }
                }, 3000);
            }
        });


        // Creation of Adapter is moved to the onResume() method, so we can restore the adapter in the current layout
        // size after rotation of the device

        // create callback in order to be informed when a list item was clicked.
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // for now the content of the list element is very simple - just a string
                Object o = mList.getItemAtPosition(position);
                String element = (String) o;
                // show selected item
                Toast.makeText(SwipeRefreshLayoutListActivity.this, "Selected : " + element,
                               Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * @brief Create adapter for current size on resume.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // create new adapter
        setAdapter();
    }


    /**
     * @brief Creates and sets an Adapter.
     */
    private void setAdapter() {
        int layout_row_id;

        // make array with ids of our target views (sub views of the list item layout)
        int mTargetViewID = R.id.PDEList_ItemText;

        layout_row_id = R.layout.pde_list_plain_text_single_line_medium_row;

        // create new adapter
        mMainAdapter = new PDEArrayAdapter<String>(this, layout_row_id, mTargetViewID, mDataset);

        // Set the adapter in our list
        mList.setAdapter(mMainAdapter);
    }


    /**
     * @brief Helper for the creation of the data set.
     */
    protected void createDataSet() {
        // clear data set
        mDataset.clear();
        // create dataSet
        for (int i = 0; i < NUMBER_OF_LIST_ITEMS_SHOWN; i++) {
            int mod;
            String text = "";

            // select blind text
            mod = i % 4;
            if (mod == 0) {
                text = "Lorem ipsum dolor";
            } else if (mod == 1) {
                text = "Conseteur sadipscing";
            } else if (mod == 2) {
                text = "Nonumy eirmod sed diam";
            } else if (mod == 3) {
                text = "Tempor invidunt ut";
            }

            // add count in order to show that the list has refreshed
            if (mListCreateCount != 0) {
                text = text + " (" + mListCreateCount + ")";
            }
            mDataset.add(text);
        }
    }
}


