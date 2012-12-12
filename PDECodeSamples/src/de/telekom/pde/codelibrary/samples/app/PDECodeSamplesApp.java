/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.app;


// imports
import android.app.Application;
import de.telekom.pde.codelibrary.ui.PDECodeLibrary;


/**
 * @brief Application class of PDECodeSamples test.
 */
public class PDECodeSamplesApp extends Application {

    /**
     * @brief Creates singleton instance of library and initializes it.
     */
    @Override
    public void onCreate() {
        //init the library with the current context
        PDECodeLibrary.getInstance().libraryInit(this);
        super.onCreate();
    }


    /**
     * @brief Finalize sample application.
     *
     * Deinits library when sample application is finalized.
     *
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        //deinit the library
        PDECodeLibrary.getInstance().libraryDeinit();
        super.finalize();
    }
}
