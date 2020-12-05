package com.example.firststep.UserHandler

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class userDB (var id: String? = "", var fname: String? = "", var lname: String? = "", var email: String? = "", var password: String? = "",var age: String = ""){
    override fun toString(): String {
        return "$fname $lname with email address of $email"
    }


}
