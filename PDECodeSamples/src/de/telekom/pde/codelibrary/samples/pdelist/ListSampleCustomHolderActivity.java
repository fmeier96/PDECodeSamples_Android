/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.pdelist;

//----------------------------------------------------------------------------------------------------------------------
//  ListSampleCustomHolderActivity
//----------------------------------------------------------------------------------------------------------------------

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDESherlockActivity;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

/**
 * @brief This small PDEList example just shows, that it's also possible to use your very own holder class for the
 * list adapter.
 */
public class ListSampleCustomHolderActivity extends PDESherlockActivity {

    // number of list elements
    private final static int NUMBER_OF_LIST_ITEMS_SHOWN = 1000;

    /**
     * @brief onCreate
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        // set content view
        setContentView(R.layout.pde_list_activity);

        // get pde list view
        final PDEListView list = (PDEListView) findViewById(R.id.pde_list);

        // create a list adapter
        // For styleguide behaviour of the list items it is strongly recommended to use an adapter which is derived
        // from PDEListBaseAdapter
        ListSampleCustomHolderAdapter adapter = new ListSampleCustomHolderAdapter(this, R.layout.list_sample_row_layout,null);
        // create the desired amount of dummy list elements & deliver resource of the item that should be used
        adapter.createDummyElements(NUMBER_OF_LIST_ITEMS_SHOWN,R.drawable.synchronize_generic_plain_center);
        // Set the adapter in our list
        list.setAdapter(adapter);

        // create callback in order to get informed when a list item was clicked.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // for now the content of the list element is very simple - just a string
                Object o = list.getItemAtPosition(position);
                ListSampleCustomHolderData element = (ListSampleCustomHolderData) o;
                // show selected item
                Toast.makeText(ListSampleCustomHolderActivity.this, "Selected : " + element.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}