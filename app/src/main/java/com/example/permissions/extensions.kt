package com.example.permissions

import android.content.Context
import android.widget.Toast

/****
 * Project: Permissions
 * From: com.example.permissions
 * Created by José Zambrano Moya on 18/10/22 at 18:04
 * More info: zambranomoya74@gmail.com
 ****/

fun Context.toast(msj: String) {
    Toast.makeText(this, msj, Toast.LENGTH_SHORT).show()
}