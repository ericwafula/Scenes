package tech.ericwathome.scenes.presentation.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import tech.ericwathome.tours.R


class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        val nightModePref = findPreference<SwitchPreferenceCompat>(getString(R.string.key_night_mode))
        nightModePref?.setOnPreferenceChangeListener { preference, newValue ->
            switchToNightMode(newValue as Boolean)
            true
        }
    }

    private fun switchToNightMode(newValue: Boolean?) {
        when (newValue) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            else -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}