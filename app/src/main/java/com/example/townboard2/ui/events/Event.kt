package com.example.townboard2.ui.events

class Event {

    var name        : String? = null
    var local       : String? = null
    var hora        : String? = null
    var data        : String? = null
    var description : String? = null
    var photoName   : String? = null

    constructor(){}

    constructor(name:String?,local:String?,hora:String?,data:String?,description:String?,photoName:String?){
        this.name       =name
        this.local      =local
        this.hora       =hora
        this.data       =data
        this.description=description
        this.photoName  =photoName
    }
}