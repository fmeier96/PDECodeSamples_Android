/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import de.telekom.pde.codelibrary.ui.events.PDEEvent;
import de.telekom.pde.codelibrary.ui.events.PDEEventSource;
import de.telekom.pde.codelibrary.ui.events.PDEIEventSource;


public class EventSourceSample implements PDEIEventSource {

    private PDEEventSource mEventSource;

    public EventSourceSample() {
        mEventSource = new PDEEventSource();
    }

    public PDEEventSource getEventSource() {
        return mEventSource;
    }

    @Override
    public Object addListener(Object target, String methodName) {
        return mEventSource.addListener(target, methodName);
    }

    @Override
    public Object addListener(Object target, String methodName, String eventMask) {
        return mEventSource.addListener(target, methodName, eventMask);
    }

    public void triggerEvent() {

        final PDEEvent myEvent = new PDEEvent();
        myEvent.setSender(this);
        myEvent.setType("EventSourceSample.TestEvent");

        mEventSource.sendEvent(myEvent);

    }

}
