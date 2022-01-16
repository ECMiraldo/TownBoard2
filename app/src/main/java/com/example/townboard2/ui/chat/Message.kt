package com.example.townboard2.ui.chat

import com.google.firebase.Timestamp
import java.util.*

class Message {
    var message    :  String? = null
    var senderUID  :  String? = null
    var senderName :  String? = null
    var date       :  String? = null
    var hour       :  String? = null
    constructor(){}

    constructor(
        message: String, senderUID: String,
        senderName: String, date: String, hour: String  ){
        this.message = message
        this.senderUID = senderUID
        this.senderName = senderName
        this.date = date
        this.hour = hour
    }


}