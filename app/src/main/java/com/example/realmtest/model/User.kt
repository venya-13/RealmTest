package com.example.realmtest.model

import io.realm.*
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import org.bson.types.ObjectId


//@RealmClass
//open class User : RealmModel {
//    @PrimaryKey
//    var _id : ObjectId = ObjectId()
//    var strVal : String = ""
//    var intVal : Int = 0
//
//    constructor( s: String, n: Int){
//        this.strVal = s
//        this.intVal = n
//    }
//
//    constructor(){}
//}


open class User : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()
    @Required
    var strVal : String = ""
    var intVal : Int = 0

    constructor( s: String, n: Int){
        this.strVal = s
        this.intVal = n
    }

    constructor(){}
}
