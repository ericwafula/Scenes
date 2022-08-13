package tech.ericwathome.tours.ui

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import tech.ericwathome.tours.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
    }
}