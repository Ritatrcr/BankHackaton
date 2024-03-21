import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Menu {

    public static void main(String[] args) 
    {
        int option;
        do 
        {
            option = Integer.parseInt(JOptionPane.showInputDialog(
                    "1. Crear cuenta\n" +
                    "2. Ingresar\n" +
                    "3. Salir"
            ));

            switch (option) 
            {
                case 1:
                    createAccount();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        } while (option != 3);
    }

    public static void createAccount() {
        String name = JOptionPane.showInputDialog("Ingrese nombre:");
        double balance = Double.parseDouble(JOptionPane.showInputDialog("Ingrese balance:"));
        String document = JOptionPane.showInputDialog("Ingrese documento:");
        String accountType = JOptionPane.showInputDialog("Ingrese tipo de cuenta:");
        String password = JOptionPane.showInputDialog("Ingrese contraseña:");
        password = encryptPassword(password);

        BankAccount account = new BankAccount(name, balance, document, accountType, password);
        saveAccountInfo(account);
    }

    public static void saveAccountInfo(BankAccount account) 
    {
        try 
        {
            BufferedWriter writer = new BufferedWriter(new FileWriter("cuentas.txt", true));
            writer.write(account.getName() + "," + account.getBalance() + "," + account.getDocument() + "," + account.getAccountType() + "," + account.getPassword() + "\n");
            writer.close();
            JOptionPane.showMessageDialog(null, "La cuenta se ha creado y guardado correctamente ");
        } catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al guardar la cuenta en el archivo.");
        }
    }

    public static void login() {
        String document = JOptionPane.showInputDialog("Ingrese documento:");
        String passwordD = JOptionPane.showInputDialog("Ingrese contraseña:");

        if (comparePassword(passwordD, decryptPassword(searchAccount(document)))) {
            JOptionPane.showMessageDialog(null, "Iniciando sesión...");
        } 
        else 
        {
            if (searchAccount(document) == null) 
            {
                JOptionPane.showMessageDialog(null, "El documento ingresado no existe.");
            } else 
            {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
            }
        }
    }

    public static String searchAccount(String document) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cuentas.txt"));
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",");
                String storedDocument = parts[2].trim();
                String storedPassword = parts[4].trim();
                if (storedDocument.equals(document))
                {
                    reader.close();
                    return storedPassword;
                }
            }
            reader.close();
        } 
        catch (IOException e) 
        {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
            e.printStackTrace();
        }
        return null;
    }

    public static boolean comparePassword(String password, String savedPassword) 
    {
        return password.equals(savedPassword);
    }

    public static String encryptPassword(String password) 
    {
        return password;
    }

    public static String decryptPassword(String password) 
    {
        return password;
    }
}


