package launcher

import tornadofx.Controller
import java.security.MessageDigest

class LauncherController: Controller() {

    fun login(login: String, password: String): Boolean {
        return true
    }

    fun registration(userName: String, email: String, password: String): Boolean {
        return true
    }

    fun hash(str1: String): String {
        val bytes = str1.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("", { str, it -> str + "%02x".format(it) })
    }

}