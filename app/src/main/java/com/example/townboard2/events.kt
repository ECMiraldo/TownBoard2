package com.example.townboard2

class events {

    var name : String? = null
    var local : String? = null
    var hora : String? = null
    var data : String? = null
    var description : String? = null



    constructor(name:String?,local:String?,hora:String?,data:String?,description:String?){
        this.name=name
        this.local=local
        this.hora=hora
        this.data=data
        this.description=description
    }
}