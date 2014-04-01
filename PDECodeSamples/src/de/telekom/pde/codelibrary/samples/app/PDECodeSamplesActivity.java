/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.app;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import de.telekom.pde.codelibrary.samples.R;
import de.telekom.pde.codelibrary.ui.PDECodeLibrary;
import de.telekom.pde.codelibrary.ui.activity.PDEActionBarListActivity;
import de.telekom.pde.codelibrary.ui.color.PDEColor;
import de.telekom.pde.codelibrary.ui.elements.common.PDEDrawableDelimiter;

import java.text.Collator;
import java.util.*;


// imports



/**
 * @brief ListActivity class for a list of all categories in the sample app.
 */
public class PDECodeSamplesActivity extends PDEActionBarListActivity {

    //some defines
    final static String SAMPLE_INTENT_EXTRA_PATH = "de.telekom.pde.codelibrary.samples.Path";
    final static String SAMPLE_CATEGORY_CODE = "de.telekom.pde.codelibrary.samples.category.code";
    public final static String PDE_CODELIB_SAMPLE_EXTRA_PREFIX = "de.telekom.pde.codelibrary.samples.extra.prefix";
    @SuppressWarnings("unused")
    final static String LOG_TAG = PDECodeSamplesActivity.class.getName();

    static ArrayList<String> sortOrderArray;


    // by setting neuland = true you may see parts of the library which are not yet supported and may change any
    // time in the future
    final static boolean NEULAND = false;


    /**
     * @brief Create the ListActivity.
     *
     * Creates the  Activity with the list of all Categories and opens new list or subcategory by click on element.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sortOrderArray = new ArrayList<String>();

        // order for the main section
        sortOrderArray.add("Common style");
        sortOrderArray.add("Flat style");
        sortOrderArray.add("Haptic style");
        sortOrderArray.add("Graphical elements");
        sortOrderArray.add("Playground");
        sortOrderArray.add("Developer screens");


        // order for the ActionBar Samples section
        sortOrderArray.add("Standard actionbar");
        sortOrderArray.add("Split actionbar");
        sortOrderArray.add("Spinner actionbar");
        sortOrderArray.add("Overflow actionbar");
        sortOrderArray.add("Multi select listview");
        sortOrderArray.add("Sliding drawer (Google)");

        // order for the Styles section
        sortOrderArray.add("Buttons");
        sortOrderArray.add("Inputfields");
        sortOrderArray.add("Sliders and progressbars");
        sortOrderArray.add("Stage");
        sortOrderArray.add("Stage and cutout");
        sortOrderArray.add("Metaphors");
        sortOrderArray.add("Login screens");

        // order Common Style section
        sortOrderArray.add("Actionbars");
        sortOrderArray.add("Headers and headlines");
        sortOrderArray.add("Lists");
        sortOrderArray.add("Scrollbars");

        // order List section
        sortOrderArray.add("List graphic single line");
        sortOrderArray.add("List graphic multi line");
        sortOrderArray.add("List icon single line");
        sortOrderArray.add("List icon multi line");
        sortOrderArray.add("List text single line");
        sortOrderArray.add("List text multi line");

        // order ScrollBar section
        sortOrderArray.add("Scrollbar overview");
        sortOrderArray.add("Scrollbar events");
        sortOrderArray.add("Scrollbar resizing");

        // order Buttons section
        sortOrderArray.add("Buttons overview");
        sortOrderArray.add("Button disabled");
        sortOrderArray.add("Button programming sample");
        sortOrderArray.add("Button events");
        sortOrderArray.add("Button resizing");

        // order InputField section
        sortOrderArray.add("Inputfields overview");
        sortOrderArray.add("Inputfield programming sample");
        sortOrderArray.add("Inputfield events");
        sortOrderArray.add("Inputfield resizing");

        // order Slider section
        sortOrderArray.add("Sliders and progressbars overview");
        sortOrderArray.add("Slider programming sample");
        sortOrderArray.add("Progressbar programming sample");
        sortOrderArray.add("Slider events");
        sortOrderArray.add("Sliders resizing");

        // order Developer Screens section
        sortOrderArray.add("Settings");


        Intent intent = getIntent();
        String path = intent.getStringExtra(SAMPLE_INTENT_EXTRA_PATH);

        if (path != null) {
            // enable action bar icon as "back home" icon, when not at top most path
            getSupportActionBar().setHomeButtonEnabled(true);
            if (PDECodeLibrary.getInstance().isAssignmentOfDefaultFontToTextViewsEnabled()) {
                //getSupportActionBar().setTitle(PDEFontHelpers.createSpannableDefaultFontString(path));
                getSupportActionBar().setTitle(path);
            } else {
                getSupportActionBar().setTitle(path);
            }
        }

        this.getListView().setBackgroundColor(PDEColor.DTUIBackgroundColor().getIntegerColor());
        this.getListView().setCacheColorHint(PDEColor.DTUIBackgroundColor().getIntegerColor());
        this.getListView().setDivider(new PDEDrawableDelimiter());

        //this.getListView().setSelector(new ColorDrawable(0));

        setListAdapter(new CodeSampleSimpleAdapter(this,
                getActivitiesList(path),
                R.layout.samplelist_screen_listitem,
                new String[]{"activity_title"},
                new int[]{R.id.choose_simple_list_text}));

        //getListView().setTextFilterEnabled(true);
	}


    /**
     * @brief returns a activity list.
     *
     * Returns a array with all activities listed by a prefix.
     *
     * @return a new ArrayList
     */
    protected ArrayList<Map<String, Object>> getActivitiesList(String prefix) {
        //Log.d(LOG_TAG, "getActivitiesList: " + prefix);

        ArrayList<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(SAMPLE_CATEGORY_CODE);

        PackageManager pm = getPackageManager();
        List<ResolveInfo> list = pm.queryIntentActivities(mainIntent, 0);

        if (null == list) {
            return myData;
        }

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

            if (label != null) {
                String[] labelSegments = label.split("\\|");

                for (int j = 0; j < labelSegments.length; j++) {

                    String labelPart = labelSegments[j];

                    if ( (prefix.length() == 0 || labelPart.startsWith(prefix))) {

                        String[] labelPath = labelPart.split("/");
                        String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

                        if (!(nextLabel.compareToIgnoreCase("Developer Screens") == 0 && !NEULAND)) {
                            if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
                                addItem(myData,
                                        nextLabel,
                                        activityIntent(
                                                info.activityInfo.applicationInfo.packageName,
                                                info.activityInfo.name,
                                                prefix));
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
            }
        }

        Collections.sort(myData, sDisplayNameComparator);

        return myData;
    }


    /**
     * @brief Comparator to compare to Map objects.
     *
     */
    private final static Comparator<Map<String, Object>> sDisplayNameComparator = new Comparator<Map<String, Object>>() {
        private final Collator collator = Collator.getInstance();

        // sort by order defined in sortOrderArray. Afterwards sort alphabetically
        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
            int index1 = sortOrderArray.indexOf(map1.get("activity_title"));
            int index2 = sortOrderArray.indexOf(map2.get("activity_title"));

            if (index1 > -1 && index2 > -1) {
                if (index1 == index2) {
                    return 0;
                } else {
                    return index1 - index2;
                }
            } else if (index1 > -1) {
               return -1;
            } else if (index2 > -1) {
                return 1;
            }

            return collator.compare(map1.get("activity_title"), map2.get("activity_title"));
        }
    };


    /**
     * @brief Return a Intent by a give package and component name.
     *
     * @return a new Intent
     */
    protected Intent activityIntent(String pkg, String componentName, String prefix) {
        Intent result = new Intent();
        result.setClassName(pkg, componentName);
        result.putExtra(PDE_CODELIB_SAMPLE_EXTRA_PREFIX, prefix);
        return result;
    }


    /**
     * @brief Returns Intent by a path.
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
     */
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Object item =  l.getItemAtPosition(position);
        if(item instanceof Map) {
            @SuppressWarnings("unchecked")
            Map<String, Object> map = Map.class.cast(item);
            Intent intent = (Intent) map.get("intent");
            startActivity(intent);
        } else {
            throw new RuntimeException("Item is not of type Map");
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // app icon in action bar clicked; go home
                Intent intent = new Intent(this, PDECodeSamplesActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


}


