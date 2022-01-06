package com.example.townboard2

import android.widget.ImageView
import java.io.FileDescriptor

class Add {
    var image       :ImageView?=null
    var name        :String?=null
    var local       :String?=null
    var minidescription :String?=null
    var description : String?=null

    constructor() {}

    constructor(image :ImageView?, name: String?, local: String?, minidescription: String?,description: String?) {
        this.image=image
        this.name = name
        this.local = local
        this.minidescription = minidescription
        this.description = description
    }
}