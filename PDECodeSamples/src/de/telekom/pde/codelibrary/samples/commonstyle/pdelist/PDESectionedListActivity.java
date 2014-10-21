/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;
import de.telekom.pde.codelibrary.ui.components.lists.PDEEventListItem;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListItem;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.components.lists.PDESectionedListAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDEArrayAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDESimpleAdapter;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

//----------------------------------------------------------------------------------------------------------------------
// PDESectionedListActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief Example of a PDE Sectioned List.
 *
 * This sample features the a simple text list item of the type pde_list_plain_text_single_line_medium_row.
 * For each section a PDEArrayAdapter is used.
 * Please note that you need to use a PDESimpleAdapter or PDEArrayAdapter with the PDE-Layout list items, since the
 * native adapters only fill TextViews (but not PDETextViews).
 * With the menu the user can add and remove sections as well as add and remove items in sections.
 *
 */
public class PDESectionedListActivity extends PDEActionBarActivity {

    // internal class of the PDEArrayAdapter with String, in order to avoid warning about type checks.
    class PDEStringArrayAdapter extends PDEArrayAdapter<String> {
        PDEStringArrayAdapter(Context context, int resource, int textViewResourceId, List<String> objects) {
            super(context, resource, textViewResourceId, objects);
        }
    }

    // sectioned list adapter
    private PDESectionedListAdapter mAdapter;


    /**
     * @brief Overwritten onCreate method
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // create pde sectioned list adapter
        mAdapter = new PDESectionedListAdapter(this);

        // the default array list, which will be (partially) cloned to populate all the sections.
        ArrayList<String> loremList = new ArrayList<String>(
                Arrays.asList("Lorem ipsum 1",
                              "Lorem ipsum 2",
                              "Lorem ipsum 3",
                              "Lorem ipsum 4",
                              "Lorem ipsum 5",
                              "Lorem ipsum 6")
        );

        // create six section with 1 to 6 items in it/
        for (int i = 0; i < 6; i++) {

            ArrayList<String> newList = new ArrayList<String>(i+1);

            // fill the array list with an increasing amount of items.
            for (int j = 0; j < i+1; j++) {
                newList.add(loremList.get(j));
            }

            // add the section, by defining the header text, creating the adapter for the items within the section,
            // and there should be a count shown in each header
            mAdapter.addSection("Header "+i,
                                new PDEStringArrayAdapter(this,
                                                            R.layout.pde_list_plain_text_single_line_medium_row,
                                                            R.id.PDEList_ItemText,
                                                            newList),
                                true);
        }

        // create the pde list view
        PDEListView pdeList = new PDEListView(this);

        // add our PDESectionListAdapter
        pdeList.setAdapter(mAdapter);

        // add listener
        pdeList.addListener(this, "onPDEListItemClicked");

        // set list as content view
        this.setContentView(pdeList);
    }


//----------------------------------------------------------------------------------------------------------------------
// ----- Options Menu ----------------------------------------------------------------------------
//----------------------------------------------------------------------------------------------------------------------


    /**
     * @brief Create options menu that allows to inserting / removing of sections and items.
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sectioned_list, menu);
        return true;
    }


    /**
     * @brief Shows option menu entries.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // get all menu entries and make them visible
        MenuItem item = menu.findItem(R.id.menu_sectioned_list_add_section_top);
        if (item != null) {
            item.setVisible(true);
        }

        item = menu.findItem(R.id.menu_sectioned_list_add_section_bottom);
        if (item != null) {
            item.setVisible(true);
        }

        item = menu.findItem(R.id.menu_sectioned_list_remove_section_top);
        if (item != null) {
            item.setVisible(true);
        }

        item = menu.findItem(R.id.menu_sectioned_list_remove_section_bottom);
        if (item != null) {
            item.setVisible(true);
        }

        item = menu.findItem(R.id.menu_sectioned_list_add_item_top_section);
        if (item != null) {
            item.setVisible(true);
        }

        item = menu.findItem(R.id.menu_sectioned_list_remove_item_top_section);
        if (item != null) {
            item.setVisible(true);
        }

        return super.onPrepareOptionsMenu(menu);
    }


    /**
     * @brief Listener for clicked option menu item.
     *
     * @param item The item of the option menu which was selected.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mAdapter == null) return false;

        Adapter a;

        // dummy list that can be added.
        ArrayList<String> strList = new ArrayList<String>(Arrays.asList("Lorem ipsum novus 1",
                                                                        "Lorem ipsum novus 2",
                                                                        "Lorem ipsum novus 3"));
        PDEStringArrayAdapter newList = new PDEStringArrayAdapter(this,
                R.layout.pde_list_plain_text_single_line_medium_row,
                R.id.PDEList_ItemText,
                strList);

        switch (item.getItemId()) {
            // insert new section at the top of the list
            case R.id.menu_sectioned_list_add_section_top:
                mAdapter.insertSection("New Section On Top", newList, true, 0);
                Toast.makeText(PDESectionedListActivity.this, "new section added on top",
                               Toast.LENGTH_SHORT).show();
                // notify section adapter of the change
                mAdapter.notifyDataSetChanged();
                return true;

            // insert new section at the bottom of the list
            case R.id.menu_sectioned_list_add_section_bottom:
                mAdapter.addSection("New Section At Bottom", newList, true);
                Toast.makeText(PDESectionedListActivity.this, "new section added at bottom",
                               Toast.LENGTH_SHORT).show();
                // notify section adapter of the change
                mAdapter.notifyDataSetChanged();
                return true;

            // remove section at the top of the list
            case R.id.menu_sectioned_list_remove_section_top:
                // security
                if (mAdapter.getSectionCount() > 0) {
                    mAdapter.removeSection(0);
                    // notify section adapter of the change
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(PDESectionedListActivity.this, "top section removed",
                                   Toast.LENGTH_SHORT).show();
                }
                return true;

            // remove section at the bottom of the list
            case R.id.menu_sectioned_list_remove_section_bottom:
                // security
                if (mAdapter.getSectionCount() > 0) {
                    mAdapter.removeSection(mAdapter.getSectionCount() - 1);
                    // notify section adapter of the change
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(PDESectionedListActivity.this, "bottom section removed",
                                   Toast.LENGTH_SHORT).show();
                }
                return true;

            // add a list item to the top section
            case R.id.menu_sectioned_list_add_item_top_section:
                // security
                if (mAdapter.getSectionCount() > 0) {
                    a = mAdapter.getSectionAdapter(0);
                    // check if the top section is an ArrayAdapter, since SimpleAdapters handle only STATIC data.
                    if (a instanceof PDEStringArrayAdapter) {
                        PDEStringArrayAdapter paa = (PDEStringArrayAdapter) a;
                        paa.add("Lorem ipsum novus");
                        Toast.makeText(PDESectionedListActivity.this, "new item added to top section",
                                       Toast.LENGTH_SHORT).show();
                    } else if (a instanceof SimpleAdapter) {
                        Toast.makeText(PDESectionedListActivity.this,
                                       "The top section is based on a SimpleAdapter." +
                                       " SimpleAdapters can only handle STATIC data sets. Can't add an item!",
                                       Toast.LENGTH_LONG).show();
                    }

                }
                return true;

            // remove a list item from the top section
            case R.id.menu_sectioned_list_remove_item_top_section:
                // security
                if (mAdapter.getSectionCount() > 0) {
                    a = mAdapter.getSectionAdapter(0);
                    // check if the top section is an ArrayAdapter, since SimpleAdapters handle only STATIC data.
                    if (a instanceof PDEStringArrayAdapter) {
                        PDEStringArrayAdapter paa = (PDEStringArrayAdapter) a;
                        if (paa.getCount() > 0) {
                            // here we know that we handle strings
                            String o = paa.getItem(paa.getCount() - 1);
                            paa.remove(o);
                            Toast.makeText(PDESectionedListActivity.this, "item removed from top section",
                                           Toast.LENGTH_SHORT).show();
                        }
                    } else if (a instanceof SimpleAdapter) {
                        Toast.makeText(PDESectionedListActivity.this,
                                       "The top section is based on a SimpleAdapter. " +
                                       "SimpleAdapters can only handle STATIC data sets. Can't remove an item!",
                                       Toast.LENGTH_LONG).show();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * @brief Listener for PDE List events.
     */
    @SuppressWarnings("unused")
    public void onPDEListItemClicked(PDEEvent event) {
        PDEEventListItem listEvent = (PDEEventListItem) event;

        if (TextUtils.equals(listEvent.getType(), PDEListItem.PDE_LIST_ITEM_EVENT_ACTION_SELECTED)) {
            // this action is fired after all list item animations are finished
            Log.d("PDEListPlainTextSingleLineActivity", "List Item " + listEvent.getListPosition()
                                                        + " which is in section "
                                                        + mAdapter.getSectionForItem(listEvent.getListPosition())
                                                        + " -> SELECTED");

            // show the string (and section id) in a toast
            Toast.makeText(this,
                           mAdapter.getItem(listEvent.getListPosition())
                           + " (" + mAdapter.getSectionForItem(listEvent.getListPosition()) + ")"
                           + " -> SELECTED",
                           Toast.LENGTH_SHORT)
                 .show();


        } else if (TextUtils.equals(listEvent.getType(), PDEListItem.PDE_LIST_ITEM_EVENT_ACTION_WILL_BE_SELECTED)) {
            // this action is fired before the list item animations will be started
            Log.d("PDEListPlainTextSingleLineActivity",
                  "List Item " + listEvent.getListPosition() + " -> WILL BE SELECTED");
        }
    }
}
