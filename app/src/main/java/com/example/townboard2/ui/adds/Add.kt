package com.example.townboard2.ui.adds

import android.widget.ImageView
import java.io.FileDescriptor

class Add {
    var photoName   : String?=null
    var name        : String?=null
    var local       : String?=null
    var description : String?=null

    constructor() {}

    constructor(photoName :String?, name: String?, local: String?,description: String?) {
        this.photoName=photoName
        this.name = name
        this.local = local
        this.description = description
    }
}