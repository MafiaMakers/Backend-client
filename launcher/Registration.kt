package launcher

import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.GridPane
import tornadofx.*
import java.util.regex.Pattern

class Registration: View() {

    override val root: GridPane by fxml("/launcher/Registration.fxml")

    @FXML
    lateinit var nameField: TextField

    @FXML
    lateinit var emailField: TextField

    @FXML
    lateinit var passwordField: PasswordField

    @FXML
    lateinit var confirmPasswordField: PasswordField

    @FXML
    lateinit var registrationButton: Button

    @FXML
    lateinit var enterButton: Hyperlink

    @FXML
    lateinit var incorrectName: Label

    @FXML
    lateinit var incorrectEmail: Label

    @FXML
    lateinit var incorrectPassword: Label

    @FXML
    lateinit var incorrectConfirmPassword: Label

    fun initialize() {
        nameField.textProperty()
            .addListener { _, _, new -> incorrectName.isVisible = !new.all { it.isLetterOrDigit() || it == '_' } }
        emailField.textProperty().addListener { _, _, new ->
            incorrectEmail.isVisible =
                !Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}").matcher(new).matches()
        }
        passwordField.textProperty()
            .addListener { _, _, new -> incorrectPassword.isVisible = !new.all { it.isLetterOrDigit() || it == '_' } }
        confirmPasswordField.textProperty()
            .addListener { _, _, new -> incorrectConfirmPassword.isVisible = new != passwordField.text }
    }

    fun registration() {
        if (!incorrectName.isVisible && !incorrectEmail.isVisible && !incorrectPassword.isVisible && !incorrectConfirmPassword.isVisible) {
            if (LauncherApp().registration(nameField.text, emailField.text, passwordField.text)) {
                val alert = Alert(Alert.AlertType.INFORMATION)
                alert.title = "Регистрация"
                alert.headerText = null
                alert.contentText = "Вы успешно зарегистрировались"
                alert.showAndWait()
            } else {
                val alert = Alert(Alert.AlertType.ERROR)
                alert.title = "Регистрация"
                alert.headerText = null
                alert.contentText = "Что-то пошло не так..."
                alert.showAndWait()
            }
        }
    }

    fun switchScene() {
        replaceWith(Login::class, ViewTransition.Slide(0.3.seconds, ViewTransition.Direction.RIGHT))
    }

}