package org.wit.example.beautycentral

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import org.wit.example.beautycentral.models.ClientModel

class DatabaseHandler(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){

companion object{
    private const val DATABASE_VERSION=1
    private const val DATABASE_NAME="ClientDB"
    private const val TABLE_CLIENTS="clientTable"
    private const val KEY_ID="_id"
    private const val KEY_DATE="date"
    private const val KEY_NAME="name"
    private const val KEY_PHONE="phoneNo"
    private const val KEY_SERVICE="service"
    private const val KEY_NOTES="notes"
}

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CLIENTS_TABLE=("CREATE TABLE " + TABLE_CLIENTS+ "("
                + KEY_ID+" INTEGER PRIMARY KEY,"+ KEY_DATE+"INTEGER,"+ KEY_NAME+ "TEXT,"
                + KEY_PHONE+"TEXT,"+ KEY_SERVICE+"TEXT,"+ KEY_NOTES+"TEXT"+")")
        db?.execSQL(CREATE_CLIENTS_TABLE)
        db?.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS"+ TABLE_CLIENTS)
        onCreate(db)
        db.close()
    }
    fun addClientDB(client:ClientModel):Long{
        val db=this.writableDatabase

        //puts data into contentValues which will be inserted into database
        val contentValues=ContentValues()
        contentValues.put(KEY_DATE,client.date)
        contentValues.put(KEY_NAME,client.name)
        contentValues.put(KEY_PHONE,client.phoneNo)
        contentValues.put(KEY_SERVICE,client.service)
        contentValues.put(KEY_NOTES,client.notes)
        //inserts row
        val success=db.insert(TABLE_CLIENTS,null,contentValues)
        db.close() //closes database connection
        return success
    }
    fun viewClients():ArrayList<ClientModel>{
        val clientList: ArrayList<ClientModel> = ArrayList<ClientModel>()
        val selectQuery = "SELECT * FROM $TABLE_CLIENTS"

        val db=this.readableDatabase
        var cursor: Cursor? = null

        try{
            cursor=db.rawQuery(selectQuery,null)
        }catch(e:SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var phone:String
        var service:String
        var notes:String

        if(cursor.moveToFirst()){
            do{
                id=cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name=cursor.getString(cursor.getColumnIndex(KEY_NAME))
                phone=cursor.getString(cursor.getColumnIndex(KEY_PHONE))
                service=cursor.getString(cursor.getColumnIndex(KEY_SERVICE))
                notes=cursor.getString(cursor.getColumnIndex(KEY_NOTES))

                val client= ClientModel(
                    id =id,
                    name =name,
                    phoneNo =phone,
                    service = service,
                    notes = notes)
                clientList.add(client)
            }while(cursor.moveToNext())
        }
        db.close()
        return clientList
    }
    fun updateClients(client: ClientModel):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(KEY_NAME,client.name)
        contentValues.put(KEY_PHONE,client.phoneNo)
        contentValues.put(KEY_SERVICE,client.service)
        contentValues.put(KEY_NOTES,client.notes)

        val success=db.update(TABLE_CLIENTS,contentValues, KEY_ID+"="+client.id,null)
        db.close()
        return success
    }
    fun deleteClient(client: ClientModel):Int{
        val db=this.writableDatabase
        val contentValues=ContentValues()
        contentValues.put(KEY_ID,client.id) //client model id
        //deleting row/client
        val success=db.delete(TABLE_CLIENTS, KEY_ID+"="+client.id,null)

        db.close()
        return success
    }
}