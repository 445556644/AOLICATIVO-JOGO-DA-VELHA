    package br.senai.sp.cotia.appjogodavelha.fragment;

    import android.content.Context;
    import android.content.SharedPreferences;
    import android.os.Bundle;

    import androidx.annotation.NonNull;
    import androidx.appcompat.app.AppCompatActivity;
    import androidx.fragment.app.Fragment;
    import androidx.navigation.fragment.NavHostFragment;

    import android.preference.PreferenceManager;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;

    import br.senai.sp.cotia.appjogodavelha.R;
    import br.senai.sp.cotia.appjogodavelha.databinding.FragmentInicioBinding;
    import br.senai.sp.cotia.appjogodavelha.databinding.FragmentJogoBinding;

    public class fragmentInicio extends Fragment {




        private @NonNull FragmentInicioBinding biding;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            biding = FragmentInicioBinding.inflate(inflater, container, false);

            // ação do btn que leva ao fragment do jogo

            biding.btProximo.setOnClickListener(view -> {

                biding.nomeJog1.getText();
                biding.nomeJog2.getText();
                NavHostFragment.findNavController(fragmentInicio.this).navigate(R.id
                .action_fragmentInicio_to_fragmentJogo);
            });

            return biding.getRoot();
        }

        public static void salvarNomeJog1(String nomeJ1, Context context) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nome_jog_1", nomeJ1);
            editor.commit();
        }

        public static void salvarNomeJog2(String nomeJ2, Context context) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("nome_jog_2", nomeJ2);
            editor.commit();
        }



        @Override
        public void onStart() {

            super.onStart();

            // para remover a toolbar
            // pegar uma referencia do tipo AppCompactActivity
            AppCompatActivity minhaActivity = (AppCompatActivity) getActivity();
            // oculta a toolbar
            minhaActivity.getSupportActionBar().hide();


        }
    }