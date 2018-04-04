package com.sjh.thinkinginjava.io.serialize;

import java.io.*;
import java.util.Date;

public class Login implements Serializable{
    private Date date = new Date();
    private String username;
    private transient String password;//禁止序列化
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }
    @Override
    public String toString() {
        return "Login{" +
                "date=" + date +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public static void main(String[] args) throws Exception {
        Login login = new Login("root", "123");
        System.out.println("序列化前Login : " + login);
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("login.out"));
        out.writeObject(login);
        out.close();
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("login.out"));
        login = (Login) in.readObject();
        System.out.println("序列化后Login : " + login);
    }
}
