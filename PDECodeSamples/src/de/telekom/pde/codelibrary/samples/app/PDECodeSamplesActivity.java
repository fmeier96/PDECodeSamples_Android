/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.app;


// imports
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import de.telekom.pde.codelibrary.samples.R;

import java.text.Collator;
import java.util.*;


/**
 * @brief ListActivity class for a list of all categories in the sample app.
 */
public class PDECodeSamplesActivity extends ListActivity {

    //some defines
    final static String SAMPLE_INTENT_EXTRA_PATH = "de.telekom.pde.codelibrary.samples.Path";
    final static String SAMPLE_CATEGORY_CODE = "de.telekom.pde.codelibrary.samples.category.code";

    // by setting neuland = true you may see parts of the libary which are not yet supported and may change any
    // time in the future
    final static boolean NEULAND = false;

    /**
     * @brief Create the ListActivity.
     *
     * Creates the  Activity with the list of all Categories and opens new list or subcategorie by click on element.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String path = intent.getStringExtra(SAMPLE_INTENT_EXTRA_PATH);

        setListAdapter(new SimpleAdapter(this,
                getActivitiesList(path),
                R.layout.samplelist_screen_listitem,
                new String[] {"activity_title"},
                new int[] {R.id.choose_simple_list_text} ));

        //getListView().setTextFilterEnabled(true);
    }

    /**
     * @brief returns a activity list.
     *
     * Returns a array with all activities listed by a prefix.
     *
     * @param prefix
     *
     * @return a new ArrayList
     */
    protected ArrayList<Map<String, Object>> getActivitiesList(String prefix) {
        ArrayList<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(SAMPLE_CATEGORY_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list)
            return myData;

        String[] prefixPath;

        if (TextUtils.isEmpty(prefix)) {
            prefixPath = null;
            prefix = "";
        } else {
            prefixPath = prefix.split("/");
        }

        int len = list.size();

        Map<String, Boolean> entries = new HashMap<String, Boolean>();

        for (int i = 0; i < len; i++) {
            ResolveInfo info = list.get(i);
            CharSequence labelSeq = info.loadLabel(pm);
            String label = labelSeq != null
                    ? labelSeq.toString()
                    : info.activityInfo.name;

            if (prefix.length() == 0 || label.startsWith(prefix)) {

                String[] labelPath = label.split("/");

                String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                if (!(nextLabel.compareTo("Developer") == 0 && !NEULAND)) {
                    if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                        addItem(myData,
                                nextLabel,
                                activityIntent(
                                    info.activityInfo.applicationInfo.packageName,
                                    info.activityInfo.name));
                    } else {
                        if (entries.get(nextLabel) == null) {
                            addItem(myData,
                                    nextLabel,
                                    browseIntent(prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
                            entries.put(nextLabel, true);
                        }
                    }
                }
            }
        }

        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }

    /**
     * @brief Comparator to compare to Map objects.
     *
     */
    private final static Comparator<Map> sDisplayNameComparator = new Comparator<Map>() {
        private final Collator collator = Collator.getInstance();

        public int compare(Map map1, Map map2) {
            return collator.compare(map1.get("activity_title"), map2.get("activity_title"));
        }
    };


    /**
     * @brief Return a Intent by a give package and component name.
     *
     * @param pkg
     * @param componentName
     *
     * @return a new Intent
     */
    protected Intent activityIntent(String pkg, String componentName) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        return result;
    }


    /**
     * @brief Returns Intent by a path.
     *
     * @param path
     *
     * @return a new Intent
     */
    protected Intent browseIntent(String path) {
        Intent result = new Intent();
        result.setClass(this, PDECodeSamplesActivity.class);
        result.putExtra(SAMPLE_INTENT_EXTRA_PATH, path);
        return result;
    }


    /**
     * @brief Adds a new Map Element to a given Map List.
     *
     * @param data
     * @param intent
     * @param name
     */
    protected void addItem(List<Map<String, Object>> data, String name, Intent intent) {
        Map<String, Object> temp = new HashMap<String, Object>();
        temp.put("activity_title", name);
        temp.put("intent", intent);
        data.add(temp);
    }

    /**
     * @brief React on list item click and start new activity.
     *
     * @param id
     * @param l
     * @param position
     * @param v
     */
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Map map = (Map) l.getItemAtPosition(position);

        Intent intent = (Intent) map.get("intent");
        startActivity(intent);
    }
}


