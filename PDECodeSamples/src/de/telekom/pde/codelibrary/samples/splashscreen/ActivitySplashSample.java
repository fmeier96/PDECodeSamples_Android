package de.telekom.pde.codelibrary.samples.splashscreen;


import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import de.telekom.pde.codelibrary.samples.app.PDECodeSamplesActivity;
import de.telekom.pde.codelibrary.ui.activity.PDEActivitySplash;


public class ActivitySplashSample extends PDEActivitySplash
{

	private class NextActivityLoader implements Runnable
	{

		@Override
		public void run()
		{
			final Intent intent = new Intent(ActivitySplashSample.this, PDECodeSamplesActivity.class);
			startActivity(intent);
			finish();
		}

	}

	private static final int	DELAY_IN_MS	= 2000;


	@Override
	protected void onResume()
	{
		super.onResume();
		startNextActivity();
	}


	private void startNextActivity()
	{
		final NextActivityLoader nextActivityLoader = new NextActivityLoader();
		final Handler handler = new Handler(Looper.getMainLooper());
		handler.postDelayed(nextActivityLoader, DELAY_IN_MS);
	}

}
