package com.inquirer.exeptions;

/**
 * Throw it when your user have no mark
 */
public class UserWithoutMarkExeption extends Exception {
    public UserWithoutMarkExeption(String message){
        super(message);
    }
}
