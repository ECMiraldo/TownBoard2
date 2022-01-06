package com.example.townboard2.ui.chat

import java.time.Instant
import java.util.*

class Message {
    var message : String? = null
    var senderUID : String? = null

    constructor(){}

    constructor(message : String, senderUID : String){
        this.message = message
        this.senderUID = senderUID
    }
}