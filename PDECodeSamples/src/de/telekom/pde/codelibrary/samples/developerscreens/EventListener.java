/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.util.Log;
import de.telekom.pde.codelibrary.ui.events.PDEEvent;

public class EventListener {

    /**
     * @brief Global tag for log outputs.
     */
    private final static String LOG_TAG = EventListener.class.getName();
    private final static boolean DEBUGPARAMS = false;

    public EventListener(EventSourceSample sample) {

        sample.getEventSource().addListener(this,"onPDEEvent");

    }

    public void onPDEEvent(PDEEvent event) {
        doSomeThing(event);
    }


    public void doSomeThing(PDEEvent event) {
        if(DEBUGPARAMS){
            Log.d(LOG_TAG, "doSomething "+event.getType());
        }
    }
}
