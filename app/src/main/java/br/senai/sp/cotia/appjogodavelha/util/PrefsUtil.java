package br.senai.sp.cotia.appjogodavelha.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefsUtil {

    // context é necessario para pegarmos um arquivo de referencia
    public static String getSimboloJog1(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // passando uma variavel como primeiro parametro e depois passsando umm valor default
        return sharedPreferences.getString("simb_jog_1", "X");
    }

    public static String getSimboloJog2(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // passando uma variavel como primeiro parametro e depois passsando umm valor default
        return sharedPreferences.getString("simb_jog_2", "O");
    }

    public static String getNomeJog1(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // passando uma variavel como primeiro parametro e depois passsando umm valor default
        return sharedPreferences.getString("nome_jog_1", "Jogador 1");
    }


    public static String getNomeJog2(Context context){

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        // passando uma variavel como primeiro parametro e depois passsando umm valor default
        return sharedPreferences.getString("nome_jog_2", "Jogador 2");
    }
}
