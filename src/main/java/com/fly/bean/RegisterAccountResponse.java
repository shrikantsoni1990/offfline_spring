package com.fly.bean;


/**
 * Created by adarsh on 05/07/17.
 */
public class RegisterAccountResponse {
    public AccountBean account;
    public Error error;

    public class Error {
        public String[] base;
    }
}
