package com.android.settings.triangle;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import com.android.settings.R;

public class Triangle_Info extends PreferenceFragment {


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		addPreferencesFromResource(R.xml.triangle_info);
		
	}
}




