package com.example.realmtest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realmtest.model.User
import io.realm.Realm
import org.bson.types.ObjectId


class MainActivity : AppCompatActivity() {

    private var userName = "venya"
    private var realm: Realm? = null
    private var key :String = "012345678901234567890123"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm = Realm.getDefaultInstance()

//        val config = SyncConfiguration.Builder(taskApp.currentUser(), "PARTITION")
//            .allowQueriesOnUiThread(true)
//            .allowWritesOnUiThread(true)
//            .build()


//        Realm.getInstanceAsync(config, object : Realm.Callback() {
//            override fun onSuccess(r: Realm) {
//                Log.v("EXAMPLE", "Successfully opened a realm with reads and writes allowed on the UI thread.")
//                realm = r
//            }
//        })

        val buttonSet = findViewById<Button>(R.id.button)
        val buttonGet = findViewById<Button>(R.id.buttonGet)
        val editTextString = findViewById<EditText>(R.id.editTextString)
        val editTextInt = findViewById<EditText>(R.id.editTextInt)
        var login : String = ""
        var id : Int = 0

        buttonSet.setOnClickListener {
            login = editTextString.text.toString()
            id = editTextInt.inputType.toInt()
            Toast.makeText(this,login,Toast.LENGTH_LONG).show()
            setInfo(login, id)
        }

        buttonGet.setOnClickListener {
            getInfo()
        }
    }

    fun setInfo(login:String, id:Int){
        Thread{
            realm?.executeTransaction { r: Realm ->
                // Instantiate the class using the factory function.
                val user = r.createObject(User::class.java, key)
                // Configure the instance.
                user.strVal = login
                user.intVal = id
            }
        }

    }

    fun getInfo(){
        var kObjectId = ObjectId(key)
//        val projectsQuery = realm!!.where(User::class.java)
        val task = realm!!.where(User::class.java)
            .equalTo("_id", kObjectId).findFirst()
        Log.v("EXAMPLE", "Fetched object by primary key: $task")

        //val results = projectsQuery.sort("name", Sort.DESCENDING).findAll()

//        val tasks: RealmResults<User> = realm!!.where(User::class.java).findAll()
//        Log.v("EXAMPLE", "Fetched object by primary key: $tasks")
    }
}