package com.example.tourisme.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.tourisme.AcceuilActivity;
import com.example.tourisme.PreferenceActivity;
import com.example.tourisme.ProfileActivity;
import com.example.tourisme.R;

public class PreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {


    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey) {
        setPreferencesFromResource(R.xml.preferences,rootKey);

        PreferenceManager.getDefaultSharedPreferences(requireContext())
                .registerOnSharedPreferenceChangeListener(this);

        /*Preference myref = (Preference) findPreference("contact_preference");
        myref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue) {
                startActivity(new Intent(getContext(), AcceuilActivity.class));
                return true;
            }
        }); */
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("theme_preference")) {
            boolean isDarkThemeEnabled = sharedPreferences.getBoolean("theme_preference", false);
            if (isDarkThemeEnabled) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            getActivity().recreate();
        }
    }


    @Override
    public boolean onPreferenceTreeClick(Preference preference) {
        String key = preference.getKey();
        if ("profile_preference".equals(key)) {
            Intent intent = new Intent(getActivity(), ProfileActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onPreferenceTreeClick(preference);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(requireContext())
                .unregisterOnSharedPreferenceChangeListener(this);
    }

}