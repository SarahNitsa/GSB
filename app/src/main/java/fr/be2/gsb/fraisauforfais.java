package fr.be2.gsb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class fraisauforfais extends MainActivity {
    EditText textQuantite1;
    Spinner listeForfait1;
    String[] valeurs;
    TextView montanttotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraisauforfais);
        textQuantite1=findViewById(R.id.qte);
        montanttotal=findViewById(R.id.somme);
        listeForfait1=findViewById(R.id.spinnefraisauforfait);
        //nsomme=findViewById(R.id.somme);
        valeurs = getResources().getStringArray(R.array.MesValeur);
        textQuantite1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
            @Override
            public void afterTextChanged(Editable s) {
                // This is where we'll check the user input
                Integer q1 = Integer.parseInt(String.valueOf("0"+textQuantite1.getText()));
                //String f1 = listeForfait1.getSelectedItem().toString();
                int posF1 = listeForfait1.getSelectedItemPosition();
                Float s1 = q1 * Float.parseFloat(valeurs[posF1]);

                montanttotal.setText(s1.toString());
            }
        }
        );
    }
}