//package org.dacss.projectinitai.views.loggin;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.checkbox.Checkbox;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.notification.Notification;
//import com.vaadin.flow.component.textfield.PasswordField;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.server.VaadinSession;
//import org.dacss.projectinitai.services.CredentialService;
//import org.dacss.projectinitai.frontend.localllms.views.LocalLlmsView;
//
//public class HuggingFaceLoginView extends Div {
//
//    private TextField usernameField;
//    private PasswordField passwordField;
//    private Checkbox saveCredentialsCheckbox;
//
//    public HuggingFaceLoginView() {
//        if (CredentialService.areCredentialsSaved()) {
//            String username = CredentialService.getUsername();
//            String password = CredentialService.getPassword();
//            VaadinSession.getCurrent().setAttribute("username", username);
//            VaadinSession.getCurrent().setAttribute("password", password);
//            getUI().ifPresent(ui -> ui.navigate(LocalLlmsView.class));
//        } else {
//            usernameField = new TextField("Username");
//            passwordField = new PasswordField("Password");
//            saveCredentialsCheckbox = new Checkbox("Save credentials");
//
//            Button loginButton = new Button("Login", event -> login());
//
//            add(usernameField, passwordField, saveCredentialsCheckbox, loginButton);
//        }
//    }
//
//    private void login() {
//        String username = usernameField.getValue();
//        String password = passwordField.getValue();
//        boolean saveCredentials = saveCredentialsCheckbox.getValue();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            Notification.show("Please enter both username and password", 3000, Notification.Position.MIDDLE);
//            return;
//        }
//
//        VaadinSession.getCurrent().setAttribute("username", username);
//        VaadinSession.getCurrent().setAttribute("password", password);
//
//        if (saveCredentials) {
//            CredentialService.saveCredentials(username, password);
//        }
//
//        getUI().ifPresent(ui -> ui.navigate(LocalLlmsView.class));
//    }
//}
