package tech.ericwathome.tours.ui.fragments

import android.os.Bundle
import android.widget.Toast
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import tech.ericwathome.tours.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val nightModePref = findPreference<SwitchPreference>(getString(R.string.key_night_mode))
        nightModePref?.setOnPreferenceChangeListener { preference, newValue ->
            switchToNightMode(newValue as Boolean)
            true
        }
    }

    private fun switchToNightMode(newValue: Boolean?) {
        Toast.makeText(requireContext(), "$newValue", Toast.LENGTH_LONG).show()
    }
}