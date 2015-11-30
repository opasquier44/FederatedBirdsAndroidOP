package fr.sio.ecp.federatedbirds.app;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import fr.sio.ecp.federatedbirds.R;

/**
 * Created by Michaël on 26/11/2015.
 */
public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings);
    }

}
