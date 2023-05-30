package fr.be2.gsb_sn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Intent intent;
    Context context;
    EditText codevisiteur;
    String nomUtilisateur;
    TextView nomComplet;
    private static final String MONFICHIER = "GSB_PREF_USER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        secure();
        setContentView(R.layout.activity_main);

    }

    public void click_Menu(View vue) {
        //String MSG = "";
        switch (vue.getId()) {
            case R.id.main_button_FraisAuForfait:
                intent = new Intent(MainActivity.this, fraisauforfais.class);

                break;
            case R.id.main_button_FraisHorsForfait:
                intent = new Intent(MainActivity.this, fraishorsforfaits.class);

                break;
            case R.id.main_button_SynthèseduMois:
                intent = new Intent(MainActivity.this, consulterfrais.class);
                break;
            case R.id.main_button_Paramètres:
                intent = new Intent(MainActivity.this, parametres.class);
                break;


            case R.id.main_button_deconnexion:
                getSharedPreferences(MONFICHIER, MODE_PRIVATE)
                        .edit()
                        .clear()
                        .commit();
                intent = new Intent(MainActivity.this, seconnecter.class);

        }
        startActivity(intent);


    }

    //Toast.makeText(this, MSG, Toast.LENGTH_SHORT).show();
    public void closeActivity(View v) {

        this.finish();
    }


    public void secure(){
        String cvisiteur =getSharedPreferences("GSB_PREF_USER", MODE_PRIVATE).getString("CodeVisiteur","pas authentifie");
        if (cvisiteur.equals("pas authentifie")) {
            Intent intent = new Intent(MainActivity.this,seconnecter.class);
            startActivity(intent);
            //this.finish();
        }

    }
    public void afficherMessage(String titre, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this); //classe qui constuit une boite de dialogue
        builder.setCancelable(true); //pr que la boite de dialogue soit refermable
        builder.setTitle(titre);
        builder.setMessage(message);
        builder.show();

    }



}




