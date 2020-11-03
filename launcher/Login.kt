package launcher

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import tornadofx.*

class Login: View() {

    override val root: GridPane by fxml("/launcher/Login.fxml")

    @FXML
    lateinit var loginField: TextField

    @FXML
    lateinit var passwordField: PasswordField

    @FXML
    lateinit var enterButton: Button

    @FXML
    lateinit var registrationButton: Hyperlink

    @FXML
    lateinit var incorrectLogin: Label

    @FXML
    lateinit var incorrectPassword: Label

    val controller: LauncherController by inject()

    fun initialize() {
        loginField.textProperty().addListener { _, _, new -> incorrectLogin.isVisible = !new.all { it.isLetterOrDigit() || it == '_' }}
        passwordField.textProperty().addListener {_, _, new -> incorrectPassword.isVisible = !new.all { it.isLetterOrDigit() || it == '_' }}
    }

    fun login() {
        if (!incorrectLogin.isVisible && !incorrectPassword.isVisible) {
            if (controller.login(loginField.text, passwordField.text)) {
                val alert = Alert(Alert.AlertType.INFORMATION)
                alert.title = "Вход"
                alert.headerText = null
                alert.contentText = "Вы успешно вошли"
                alert.showAndWait()
            } else {
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Вход"
                alert.headerText = null
                alert.contentText = "Неверный логин или пароль"
                alert.showAndWait()
            }
        }
    }

    fun switchScene() {
        replaceWith(Registration::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
    }

}