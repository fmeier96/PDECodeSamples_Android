/* Deutsche Telekom AG owns the right of use concerning the following code taken from the Deutsche Telekom
 * Experience Toolbox. You can obtain a copy of the terms and conditions of the Experience Toolbox at
 * https://www.design.telekom.com/myaccount/terms-of-use/
 *
 * Copyright (c) 2012. Neuland Multimedia GmbH.
 */

package de.telekom.pde.codelibrary.samples.developerscreens;


// imports
import android.app.Activity;
import android.os.Bundle;


/**
 * Created with IntelliJ IDEA.
 * User: kdanner
 * Date: 22.10.12
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
public class FontTestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FontTestView ftv = new FontTestView(this);

        setContentView(ftv);



    }




}
