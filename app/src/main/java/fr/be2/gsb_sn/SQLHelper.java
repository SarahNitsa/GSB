package fr.be2.gsb_sn;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {
    //declaration des variables
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";
    public static final String ID_FRAIS = "ID_FRAIS"; //ce sera les colonnes de la table frais
    public static final String TYPEFRAIS = "TYPEFRAIS";
    public static final String QUANTITE = "QUANTITE";
    public static final String DATEFRAIS = "DATEFRAIS";
    public static final String MONTANT = "MONTANT";
    public static final String DATESAISIE = "DATESAISIE";
    public static final String LIBELLE = "LIBELLE";

    /**
     * Crée une table par une requete SQL
     */
    private static final String CREATE_TABLE = "CREATE TABLE " + DB_TABLE + " (" + ID_FRAIS +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + TYPEFRAIS + " TEXT," + QUANTITE + " INTEGER," + DATEFRAIS
            + " TEXT," + MONTANT + " REAL," + LIBELLE + " TEXT," + DATESAISIE + " DATETIME DEFAULT CURRENT_TIMESTAMP)";

    private static final String CREATE_PARAMETRES="CREATE TABLE PARAMETRES(id int primary key,codev text ,nom text ," +
            "prenom text, email text , urlserveur text ,password text)";

    private static final String INIT_PARAMETRES="INSERT INTO PARAMETRES( ID, CODEV,NOM, PRENOM,EMAIL, URLSERVEUR) Values(1,0,'','','@','https://')";
    private static final String TAG = "SQLHelper";

    /**
     *
     * @param context
     */

    public SQLHelper(Context context) {

        super(context, DB_NAME, null, 1);//permet d'acceder aux membres de la classe mère

    }

    /**
     * constructeur de la classe
     * methode venant de SQLLite et permettant d'executer une requete SQL
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }



    /**
     * destructeur de la classe
     * @param sqLiteDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insère dans le BDD les données type, quantité, date, montant et libellé renseignées par le visiteur
     * @param typeForfait
     * @param quantite
     * @param dateForfait
     * @param montant
     * @param libelle
     * @return booleen
     */
    public boolean insertData(String typeForfait, Integer quantite, String dateForfait, double montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPEFRAIS, typeForfait);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATEFRAIS, dateForfait);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;

    }

    private SQLiteDatabase Sql;

       /* public void close() {
            if (SQLHelper != null) {
                SQLHelper.close();
            }
        }*/

    public long createFrais(String code, String Type_Forfait,
                            String Quantite, String date) {

        ContentValues initialValues = new ContentValues();
        initialValues.put(ID_FRAIS, code);
        initialValues.put(TYPEFRAIS, Type_Forfait);
        initialValues.put(QUANTITE, Quantite);
        initialValues.put(DATEFRAIS, date);

        return Sql.insert(DB_TABLE, null, initialValues);
    }
    //connexion a la BD
    public SQLHelper open() throws SQLException {

        SQLiteDatabase db =this.getWritableDatabase();
        return this;

    }

    public boolean deleteAllFrais() {

        int doneDelete = 0;
        doneDelete = Sql.delete(DB_TABLE, null , null);
        Log.w(TAG, Integer.toString(doneDelete));
        return doneDelete > 0;

    }




    public Cursor fetchAllFrais() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor mCursor = db.query(DB_TABLE, new String[] {"rowid _id",ID_FRAIS,
                        TYPEFRAIS, QUANTITE, DATEFRAIS,MONTANT,LIBELLE},
                null, null, null, null, null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }


    public Cursor fetchFrais(String filtre) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor Cursor = db.query(DB_TABLE, new String[]{"rowid _id",LIBELLE,
                        ID_FRAIS, DATEFRAIS, DATESAISIE, MONTANT, QUANTITE},
                filtre, null, null, null, null);

        if (Cursor != null) {
            Cursor.moveToFirst();
        }
        return Cursor;
    }
    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from " + DB_TABLE;
        //cursor: type, pointeur: pr parcourir les lignes ds les resultats de la requete. Null car pas de where
        Cursor pointeur = db.rawQuery(query, null);
        return pointeur;}



    }
