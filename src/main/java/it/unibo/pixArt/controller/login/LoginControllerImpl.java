package it.unibo.pixArt.controller.login;

import java.io.IOException;

import it.unibo.pixArt.controller.SimpleController;
import it.unibo.pixArt.model.user.manager.UserManagerImpl;
import it.unibo.pixArt.model.user.validator.ValidationResult;

public class LoginControllerImpl extends SimpleController implements LoginController{

    private static final int PASS_MIN_LENGTH = 8;
    private static final int PASS_MAX_LENGTH = 16;
    private static final int NAME_MIN_LENGTH = 6;
    private static final int NAME_MAX_LENGTH = 30;

    @Override
    public boolean login(String username, String password) throws IOException {
        if (!UserManagerImpl.getInstance().login(username, password).isEmpty()){
            final String path = UserManagerImpl.getInstance().login(username, password).get().getPathToFile();
            this.getModel().setUser(username, password, path);
            return true;
        }
        return false;
    }

    @Override
    public boolean register(String username, String password, String path) throws IOException {
        if (!UserManagerImpl.getInstance().register(username, password, path).isEmpty()){
            this.getModel().setUser(username, password, path);
            return true;
        } 
        return false;
    }

    @Override
    public ValidationResult usernameValidation(final String username) {
        if (username.length() < NAME_MIN_LENGTH) {
            return ValidationResult.TOO_SHORT;
        } else if (username.length() > NAME_MAX_LENGTH) {
            return ValidationResult.TOO_LONG;
        }
        return ValidationResult.CORRECT;
    }

    @Override
    public ValidationResult passwordValidation(final String password) {
        if (password.length() < PASS_MIN_LENGTH) {
            return ValidationResult.TOO_SHORT;
        } else if (password.length() > PASS_MAX_LENGTH) {
            return ValidationResult.TOO_LONG;
        }
        return ValidationResult.CORRECT;
    }

    
}
