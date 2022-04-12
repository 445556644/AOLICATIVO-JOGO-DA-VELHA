package br.senai.sp.cotia.appjogodavelha.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.senai.sp.cotia.appjogodavelha.R;


public class FragmentPref extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // linkando o fragmento de preferencias ao xml
        addPreferencesFromResource(R.xml.preferences);
    }
}