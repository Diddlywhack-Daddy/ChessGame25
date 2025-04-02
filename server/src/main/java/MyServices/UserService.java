package MyServices;


import RequestClasses.BadRequestException;
import RequestClasses.LoginRequest;
import RequestClasses.LogoutRequest;
import RequestClasses.RegisterRequest;
import ResponseClasses.LoginResponse;
import ResponseClasses.LogoutResponse;
import ResponseClasses.RegisterResponse;
import dataaccess.AuthDAO;
import dataaccess.DataAccessException;
import dataaccess.UserDAO;
import model.AuthData;
import model.UserData;

//service for Register, Login, and Logout
public class UserService {
    private UserDAO userDAO;
    private AuthDAO authDAO;
    public RegisterResponse register(RegisterRequest req) throws DataAccessException,AlreadyTakenException, BadRequestException {
        try {
            if(userDAO.getUser(req.username())!=null){
                throw new AlreadyTakenException("Username already taken");
                //TODO: throw the other exceptions
            }
                userDAO.createUser(new UserData(req.username(), req.password(), req.email()));
                AuthData authData = authDAO.createAuth(req.username());
                String authToken = authData.authToken();
                return new RegisterResponse(req.username(),authToken);

        } catch (DataAccessException e) {
            throw new DataAccessException(e.getMessage());
        }
    }

    public LoginResponse login(LoginRequest newRequest) {

        return null;
    }

    public LogoutResponse logout(LogoutRequest newRequest) {
        return null;
    }
}
