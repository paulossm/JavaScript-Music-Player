/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 * Used to manipulate
 * exceptions while
 * managing user Login
 * 
 * @author Paulo
 */
public class LoginException extends IllegalArgumentException {

    /**
     * Creates a new instance of <code>LoginException</code> without detail
     * message.
     */
    public LoginException() {
    }

    /**
     * Constructs an instance of <code>LoginException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoginException(String msg) {
        super(msg);
    }
}
