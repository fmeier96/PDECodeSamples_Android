/*
 * Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2014. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.commonstyle.pdelist;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Arrays;

import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDEConstants;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarActivity;
import de.telekom.pde.codelibrary.ui.buildingunits.PDEBuildingUnits;
import de.telekom.pde.codelibrary.ui.components.elementwrappers.PDETextView;
import de.telekom.pde.codelibrary.ui.components.lists.PDEListView;
import de.telekom.pde.codelibrary.ui.components.lists.PDESectionedListAdapter;
import de.telekom.pde.codelibrary.ui.components.lists.adapters.PDESimpleCursorAdapter;


//----------------------------------------------------------------------------------------------------------------------
// ContactListActivity
//----------------------------------------------------------------------------------------------------------------------


/**
 * @brief Example which shows the use of a PDE sectioned list with the help of the internal phone book.
 *
 * The sample reads the entries of the phone book data base of the android phone and displays the results within a PDE
 * sectioned list similar to the native phone book application.
 */
public class ContactListActivity extends PDEActionBarActivity {

    @Override
    @SuppressWarnings("unchecked")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadData();
    }


    private void loadData() {
        // pde list view
        final PDEListView list = new PDEListView(this);

        ContentResolver cr = getContentResolver();
        // cursor adapter which can handle PDE views
        PDESimpleCursorAdapter dataAdapter;
        // PDE sectioned list adapter
        PDESectionedListAdapter sectionAdapter;
        // create adapter
        sectionAdapter = new PDESectionedListAdapter(this, R.layout.pde_list_header_clustered);


        // create array for alphabetic sections
        ArrayList<String> strListAlphabet = new ArrayList<String>(
                Arrays.asList("#",
                              "A",
                              "B",
                              "C",
                              "D",
                              "E",
                              "F",
                              "G",
                              "H",
                              "I",
                              "J",
                              "K",
                              "L",
                              "M",
                              "N",
                              "O",
                              "P",
                              "Q",
                              "R",
                              "S",
                              "T",
                              "U",
                              "V",
                              "W",
                              "X",
                              "Y",
                              "Z")
        );


        String[] projection = new String[]{ContactsContract.Contacts._ID,
                                           ContactsContract.Contacts.DISPLAY_NAME};
        String selection;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " ASC";
        Cursor cur;


        // create arrays to map values to text fields
        String[] columns = new String[]{
                ContactsContract.Contacts.DISPLAY_NAME
        };

        int[] to = new int[]{
                R.id.PDEList_ItemText
        };

        for (String s : strListAlphabet) {
            // create query
            selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = '1' AND "
                        + ContactsContract.Contacts.DISPLAY_NAME + " LIKE '" + s + "%'";

            if (s.equals("#")) {
                selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = '1' AND NOT (("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'A%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'B%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'C%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'D%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'E%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'F%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'G%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'H%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'I%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'J%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'K%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'L%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'M%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'N%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'O%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'P%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'Q%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'R%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'S%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'T%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'U%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'V%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'W%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'X%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'Y%') OR ("
                            + ContactsContract.Contacts.DISPLAY_NAME + " LIKE 'Z%'))";
            }


            // query
            cur = cr.query(ContactsContract.Contacts.CONTENT_URI,
                           projection, selection, null, sortOrder);


            // set section adapter if there are more than 0 items
            if (cur != null && cur.getCount() > 0) {
                dataAdapter = new PDESimpleCursorAdapter(this, R.layout.pde_list_plain_text_single_line_large_row, cur,
                                                         columns, to, 0);
                sectionAdapter.addSection(s, dataAdapter, true);
            }
        }

        // set list
        list.setAdapter(sectionAdapter);
        this.setContentView(list);
    }


    private void noDataLoaded() {
        PDETextView text = new PDETextView(this);
        text.setText("Datenzugriff nicht erlaubt");
        text.setHorizontalAlignment(PDEConstants.PDEAlignment.PDEAlignmentCenter);
        text.setPaddingAll(PDEBuildingUnits.BU());
        text.setTextSize(PDEBuildingUnits.BU() * 2.0f);

        this.setContentView(text);
    }

}
