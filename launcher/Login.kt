package launcher

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import tornadofx.*
import java.util.regex.Pattern

class Login: View() {

    override val root: GridPane by fxml("/launcher/Login.fxml")

    @FXML
    lateinit var emailField: TextField

    @FXML
    lateinit var passwordField: PasswordField

    @FXML
    lateinit var enterButton: Button

    @FXML
    lateinit var registrationButton: Hyperlink

    @FXML
    lateinit var incorrectEmail: Label

    @FXML
    lateinit var incorrectPassword: Label

    val controller: LauncherController by inject()

    fun initialize() {
        emailField.textProperty().addListener { _, _, new -> incorrectEmail.isVisible = !Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(new).matches() }
        passwordField.textProperty().addListener {_, _, new -> incorrectPassword.isVisible = !new.all { it.isLetterOrDigit() || it == '_' }}
    }

    fun login() {
        if (!incorrectEmail.isVisible && !incorrectPassword.isVisible) {
            if (controller.login(emailField.text, passwordField.text)) {
                val alert = Alert(Alert.AlertType.INFORMATION)
                alert.title = "Вход"
                alert.headerText = null
                alert.contentText = "Вы успешно вошли"
                alert.showAndWait()
            } else {
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Вход"
                alert.headerText = null
                alert.contentText = "Неверная почта или пароль"
                alert.showAndWait()
            }
        }
    }

    fun switchScene() {
        replaceWith(Registration::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.LEFT))
    }

}