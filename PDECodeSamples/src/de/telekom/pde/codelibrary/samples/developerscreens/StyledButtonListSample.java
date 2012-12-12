/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;

import android.app.Activity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.ListView;
import de.telekom.pde.codelibrary.samples.R;


public class StyledButtonListSample extends Activity {
    ListView mListView;
    BaseAdapter mAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set xml layout
        setContentView(R.layout.developer_styledbuttonlist_sample_screen);
        // get gfx references
        mListView = (ListView)findViewById(R.id.styledbuttonlist_sample_list);
        // create adapter
        mAdapter = new StyledButtonListAdapter(this);
        mListView.setAdapter(mAdapter);

    }
}