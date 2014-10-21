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
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.components.lists.PDESectionedListAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDEArrayAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDESimpleAdapter;

//----------------------------------------------------------------------------------------------------------------------
// PDESectionedListImageActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief Example of a PDE Sectioned List.
 *
 * This example shows the usage of PDESimpleAdapters for the sections. The pde_list_plain_graphic_multi_line_row item
 * layout is used.
 */
public class PDESectionedListImageActivity extends PDEActionBarActivity {


    // hash map key constants
    public final static String ITEM_TITLE = "title";
    public final static String ITEM_CONTENT = "content";
    public final static String ITEM_PHOTO = "photo";

    // sectioned list adapter
    private PDESectionedListAdapter mAdapter;


    /**
     * @brief Overwritten onCreate method
     */
    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // pde list view
        PDEListView mList;
        // create pde sectioned list adapter
        mAdapter = new PDESectionedListAdapter(this);

        // ------------------- Array Adapter ---------------------------------------------------------------------------


        List<Map<String, ?>> photosList = new LinkedList<Map<String, ?>>();
        photosList.add(createPhotoDoubleStringItem("Foto 1", "Lorem ipsum dolor", R.drawable.kids));
        photosList.add(createPhotoDoubleStringItem("Foto 2", "Conseteur sadipscing", R.drawable.couple));
        photosList.add(createPhotoDoubleStringItem("Foto 3", "Tempor invidunt ut labore et dolore magna aliquyam " +
                                                         "erat, sed diam voluptua. At vero eos et accusam et justo " +
                                                         "duo dolores et ea rebum.",
                                               R.drawable.kids));


        for (int i = 0; i < 3; i++) {

            List<Map<String, ?>> newList = new LinkedList<Map<String, ?>>();

            for (int j = 0; j < i+1; j++) {
                newList.add(photosList.get(j));
            }

            mAdapter.addSection("Photo Frame Header " + i,
                                new PDESimpleAdapter(this,
                                                     newList,
                                                     R.layout.pde_list_plain_graphic_multi_line_row,
                                                     new String[]{ITEM_TITLE, ITEM_CONTENT, ITEM_PHOTO},
                                                     new int[]{R.id.PDEList_ItemText,
                                                               R.id.PDEList_ItemSubText,
                                                               R.id.PDEList_ItemImage}),
                                true);


        }

        // create the pde list view
        mList = new PDEListView(this);
        // add our PDESectionListAdapter
        mList.setAdapter(mAdapter);
        // set list as content view
        this.setContentView(mList);
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
            // disabled since simple adapter cannot be changed
            item.setVisible(false);
        }

        item = menu.findItem(R.id.menu_sectioned_list_remove_item_top_section);
        if (item != null) {
            // disabled since simple adapter cannot be changed
            item.setVisible(false);
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

        // dummy list that can be added.
        List<Map<String, ?>> photoList = new LinkedList<Map<String, ?>>();
        photoList.add(createPhotoDoubleStringItem("Foto 1", "Lorem ipsum dolor", R.drawable.kids));
        photoList.add(createPhotoDoubleStringItem("Foto 2", "Conseteur sadipscing", R.drawable.couple));
        photoList.add(createPhotoDoubleStringItem("Foto 3", "Tempor invidunt ut labore et dolore magna aliquyam " +
                                                             "erat, sed diam voluptua. At vero eos et accusam et justo " +
                                                             "duo dolores et ea rebum.",
                                                   R.drawable.kids));        // Let's start simple with a common Android ArrayAdapter
        PDESimpleAdapter newList = new PDESimpleAdapter(this,
                             photoList,
                             R.layout.pde_list_plain_graphic_multi_line_row,
                             new String[]{ITEM_TITLE, ITEM_CONTENT, ITEM_PHOTO},
                             new int[]{R.id.PDEList_ItemText,
                                       R.id.PDEList_ItemSubText,
                                       R.id.PDEList_ItemImage});

        switch (item.getItemId()) {
            // insert new section at the top of the list
            case R.id.menu_sectioned_list_add_section_top:
                mAdapter.insertSection("New Section On Top", newList, true, 0);
                Toast.makeText(PDESectionedListImageActivity.this, "new section added on top",
                               Toast.LENGTH_SHORT).show();
                // notify section adapter of the change
                mAdapter.notifyDataSetChanged();
                return true;

            // insert new section at the bottom of the list
            case R.id.menu_sectioned_list_add_section_bottom:
                mAdapter.addSection("New Section At Bottom", newList, true);
                Toast.makeText(PDESectionedListImageActivity.this, "new section added at bottom",
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
                    Toast.makeText(PDESectionedListImageActivity.this, "top section removed",
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
                    Toast.makeText(PDESectionedListImageActivity.this, "bottom section removed",
                                   Toast.LENGTH_SHORT).show();
                }
                return true;
        }

        return super.onOptionsItemSelected(item);

    }

    //R.id.menu_sectioned_list_add_item_top_section:
    //R.id.menu_sectioned_list_remove_item_top_section

//---------------------------------------------------------------------------------------------------------------------
// ----- Creation of data sets ----------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------


    /**
     * @brief Helper for creation of a hash map which serves as data source.
     *
     * @param title list item title
     * @param content list item text content
     */
    public Map<String, ?> createDoubleStringItem(String title, String content) {
        Map<String, String> item = new HashMap<String, String>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CONTENT, content);
        return item;
    }


    /**
     * @brief Helper for creation of a hash map which serves as data source.
     *
     * @param title list item title
     * @param content list item text content
     * @param photoID id of list item photo
     */
    public Map<String, ?> createPhotoDoubleStringItem(String title, String content, int photoID) {
        Map<String, Object> item = new HashMap<String, Object>();
        item.put(ITEM_TITLE, title);
        item.put(ITEM_CONTENT, content);
        item.put(ITEM_PHOTO, photoID);
        return item;
    }
}
