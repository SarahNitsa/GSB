package fr.be2.gsb_sn;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class fraisauforfais extends MainActivity {
    EditText textQuantite1;
    Spinner listeForfait1;
    String[] valeurs;
    TextView montanttotal;
    TextView maDate;
    SQLHelper database;
    DatePickerDialog picker;
    Calendar calendrier = Calendar.getInstance(); //on declare une classe d'un calendrier qui existe deja
    int jj=calendrier.get(Calendar.DAY_OF_MONTH);
    int mm=calendrier.get(Calendar.MONTH);
    int aaaa=calendrier.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fraisauforfais);
        database= new SQLHelper(this);

        textQuantite1=findViewById(R.id.qte);
        montanttotal=findViewById(R.id.somme);
        listeForfait1=findViewById(R.id.spinnefraisauforfait);
        //nsomme=findViewById(R.id.somme);
        maDate = findViewById(R.id.DateFrais);
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
    public void ShowCal(View vv)
    {
        picker = new DatePickerDialog(fraisauforfais.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        maDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        //date qu'on recupere une fois qu'on a selectionne
                    }
                },aaaa, mm, jj);//date qui s'affiche sur le calendrier
        picker.show();
    }
    public void MonClick(View v){
        Integer quantite =Integer.parseInt(String.valueOf(textQuantite1.getText()));
        String forfait = listeForfait1 .getSelectedItem().toString();
        Float montantCalcule = Float.parseFloat(montanttotal.getText().toString());
        String date = maDate.getText().toString();
        if (database.insertData(forfait, quantite, null, montantCalcule, forfait)){
            afficherMessage("Succès", "Valeur ajoutée. " + "Montant= " + montantCalcule);
            return;
        }
    }
}