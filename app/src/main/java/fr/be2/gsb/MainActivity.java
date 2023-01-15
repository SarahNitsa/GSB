package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click_Menu(View vue) {
        String MSG = "";
        switch (vue.getId()) {
            case R.id.main_button_FraisAuForfait:
                intent = new Intent(MainActivity.this, fraisauforfais.class);

                break;
            case R.id.main_button_FraisHorsForfait:
                intent = new Intent(MainActivity.this, fraishorsforfaits.class);

                break;
            case R.id.main_button_SynthèseduMois:
                intent = new Intent(MainActivity.this, SyntheseDuMois.class);
                break;
            case R.id.main_button_Paramètres:
                intent = new Intent(MainActivity.this, parametres.class);
                break;

        }
        startActivity(intent);
    }

    //Toast.makeText(this, MSG, Toast.LENGTH_SHORT).show();
    public void closeActivity(View v) {

        this.finish();
    }
}




