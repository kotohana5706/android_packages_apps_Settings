/*
 * Copyright (C) 2014 Team Triangles ROM Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings.triangle;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.os.SystemClock;
import android.os.SystemProperties;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.preference.PreferenceFragment;
import android.util.Log;
import com.android.settings.R;

public class TriangleCenter extends PreferenceFragment {

    private static final String KEY_TRIANGLE_LOGO = "triangle_logo";
    private static final String KEY_MOD_VERSION = "mod_version";
    private static final String LOG_TAG = "DeviceInfoSettings";
    long[] mHits = new long[1];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.triangle_center);

        setValueSummary(KEY_MOD_VERSION, "ro.triangle.display.version");
        findPreference(KEY_MOD_VERSION).setEnabled(true);
    }

    private void setValueSummary(String preference, String property) {
        try {
            findPreference(preference).setSummary(
                    SystemProperties.get(property,
                            getResources().getString(R.string.device_info_default)));
        } catch (RuntimeException e) {
            // No recovery
        }
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
		if (preference.getKey().equals(KEY_TRIANGLE_LOGO)) {
            System.arraycopy(mHits, 1, mHits, 0, mHits.length-1);
            mHits[mHits.length-1] = SystemClock.uptimeMillis();
            if (mHits[0] >= (SystemClock.uptimeMillis()-500)) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.putExtra("is_triangle", true);
                intent.setClassName("android",
                        com.android.internal.app.triangle.TriangleEasterEggActivity.class.getName());
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Unable to start EasterEggActivity " + intent.toString());
                }
            }
        }
	return super.onPreferenceTreeClick(preferenceScreen, preference);
	}
}
