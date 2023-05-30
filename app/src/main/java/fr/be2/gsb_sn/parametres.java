package fr.be2.gsb_sn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class parametres extends MainActivity{

    private static final String MONFICHIER = "GSB_PREF_USER";

    EditText Codev;
    EditText Nom;
    EditText Prenom;
    EditText Mail;
    EditText Urlserveur;
    Button Valider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parametres);
        Mail = findViewById(R.id.TV_mailparametres);
        Codev = findViewById(R.id.codevisiteur);
        Nom = findViewById(R.id.nomparametres);
        Urlserveur = findViewById(R.id.urlserveur);
        Valider = findViewById(R.id.main_button_confirmerparamètres);
        Prenom = findViewById(R.id.prenomparametres);
        recupererInfos();

    }

    private void recupererInfos() {
        Codev.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("CodeVisiteur",""));
        Mail.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("email",""));
        Nom.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("Nom",""));
        Prenom.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("Prenom",""));
        Urlserveur.setText(getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("UrlServeur",""));
    }

    public void Ajouter_info_v(View v){
        SharedPreferences preferences = getSharedPreferences(MONFICHIER, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        getSharedPreferences(MONFICHIER, MODE_PRIVATE)
                .edit()
                .putString("Nom", Nom.getText().toString())
                .putString("Prenom", Prenom.getText().toString())
                .putString("UrlServeur", Urlserveur.getText().toString())
                .putString("email", Mail.getText().toString())
                .putString("CodeVisiteur", Codev.getText().toString())

                .apply();

        afficherMessage("Succès!", "Informations ajoutées.");
        return;
    }
}