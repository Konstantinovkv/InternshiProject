package internshipProject.exceptions;

public class UnauthorizedException extends Exception{

    @Override
    public String getMessage() {
        return "Wrong username or password.";
    }
}
