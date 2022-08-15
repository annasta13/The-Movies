package com.habileducation.themovie.util

import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.stringWithContentsOfFile

/**
 * Created by Annas Surdyanto on 15/08/22.
 *
 */
actual class FileReader actual constructor(){
    actual fun readFile(fileName: String): String {
        val string = NSString.stringWithContentsOfFile(fileName, NSUTF8StringEncoding, null)
        return string!!
    }
}