package personal.views;

import personal.controllers.UserController;
import personal.model.User;

import java.util.List;
import java.util.Scanner;

public class ViewUser {

    private UserController userController;

    public ViewUser(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com = Commands.NONE;
        while (true) {
            String command = prompt("Введите команду: ");
            try {
            com = Commands.valueOf(command.toUpperCase());
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    create();
                    break;
                case READ:
                    read();
                    break;
                case LIST:
                    list();
                    break;
                case UPDATE:
                    updateUser();
                    break;
                case DELETE:
                    deleteUser();
                    break;
                }
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void deleteUser() {
        String readId = prompt("Введите ID контакта который надо удалить: ");
        userController.deleteUser(readId);
    }

    private void updateUser() throws Exception {
        String readId = prompt("Введите новый ID контакта: ");
        userController.updateUser(readId, inputUser());
    }

    private void list() {
        List<User> listUsers = userController.readAllUsers();
        for (User user : listUsers) {
            System.out.println(user + "\n");
        }
    }

    private void read() throws Exception {
        String id = prompt("Идентификатор пользователя: ");
            User user = userController.readUser(id);
            System.out.println(user);
    }

    private User inputUser() {
        String firstName = prompt("Имя: ");
        String lastName = prompt("Фамилия: ");
        String phone = prompt("Номер телефона: ");
        return new User(firstName, lastName, phone);
    }

    private void create() throws Exception {
        userController.saveUser(inputUser());
    }
    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }
}
