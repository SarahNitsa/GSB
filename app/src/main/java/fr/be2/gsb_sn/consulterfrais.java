package fr.be2.gsb_sn;

        import static java.lang.Integer.parseInt;

        import androidx.appcompat.app.AppCompatActivity;

        import android.database.Cursor;
        import android.os.Bundle;
        import android.text.Editable;
        import android.text.TextWatcher;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.EditText;
        import android.widget.FilterQueryProvider;
        import android.widget.ListView;
        import android.widget.SimpleCursorAdapter;
        import android.widget.Toast;

public class consulterfrais extends MainActivity {
    private SQLHelper bd;
    private SimpleCursorAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulterfrais);

        bd = new SQLHelper(this);
        bd.open();
        displayListView();


    }

    private void displayListView() {


        Cursor cursor = bd.fetchAllFrais();

        // Les colonnes que l’on veut lier
        String[] columns = new String[] {
                SQLHelper.ID_FRAIS,
                SQLHelper.TYPEFRAIS,
                SQLHelper.QUANTITE,
                SQLHelper.MONTANT,
                SQLHelper.DATEFRAIS,
                SQLHelper.LIBELLE

        };

        // Les éléments defnis dans le XML auxquels les données sont liées
        int[] to = new int[] {
                R.id.code,
                R.id.typefrais,
                R.id.quantite,
                R.id. montant ,
                R.id.date,
                R.id.libelle
        };


//On crée l'adaptateur à l'aide du curseur pointant sur les données souhaitées  ainsi que les informations de mise en page
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.detailfrais,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Attribuer l’adapter au ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // On obtient le curseur, positionne sur la ligne correspondante dans le jeu de résultats
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                //On obtient l'id du frais en cliquant sur le frais
                String myId = cursor.getString(cursor.getColumnIndexOrThrow("ID"));
                Toast.makeText(getApplicationContext(), myId, Toast.LENGTH_SHORT).show();
               // bd.deleteData (parseInt (myId));

            }
        });
        EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                return bd.fetchFrais(constraint.toString());
            }
        });
    }

}