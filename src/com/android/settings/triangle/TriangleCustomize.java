package com.android.settings.triangle;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.util.Log;
import com.android.settings.R;

public class TriangleCustomize extends PreferenceFragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.triangle_customize);
		
	}
}
