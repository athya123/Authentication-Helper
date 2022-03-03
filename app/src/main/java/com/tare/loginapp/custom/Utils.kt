package com.tare.loginapp.custom

import android.os.Build
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.TextView

fun TextView.makeHyperLink(url:String){
    text = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
        Html.fromHtml("<a href='$url'>$text</a>",Html.FROM_HTML_MODE_COMPACT)
    }else {
        Html.fromHtml("<a href='$url'>$text</a>")
    }
    movementMethod = LinkMovementMethod.getInstance()
    isClickable = true
}
class Utils {

}