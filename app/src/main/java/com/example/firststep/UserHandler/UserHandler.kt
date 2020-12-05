package com.example.firststep.UserHandler

import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserHandler {
var database: FirebaseDatabase
var userlistRef : DatabaseReference


init{
    database = FirebaseDatabase.getInstance()
    userlistRef = database.getReference("Users")
}

fun create(userdb: userDB): Boolean {
    val id = userlistRef.push().key
    userdb.id = id

    userlistRef.child(id!!).setValue(userdb)
    return true
}
fun update(userdb: userDB):Boolean{
    userlistRef.child(userdb.id!!).setValue(userdb)
    return true
}
fun delete(userdb: userDB): Boolean{
    userlistRef.child(userdb.id!!).removeValue()
    return true
}
}