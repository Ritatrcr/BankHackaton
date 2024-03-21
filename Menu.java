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

    public static void login() {
        String document = JOptionPane.showInputDialog("Ingrese documento:");
        String passwordD = JOptionPane.showInputDialog("Ingrese contraseña:");
        BankAccount account = searchAccount(document);
    
        if (account != null && comparePassword(passwordD, decryptPassword(account.getPassword()))) {
            JOptionPane.showMessageDialog(null, "Iniciando sesión...");
            JOptionPane.showMessageDialog(null, "Bienvenido " + account.getName()+"\n"+
                    "Saldo: " + account.getBalance()+"\n"+
                    "Documento: " + account.getDocument()+"\n"+
                    "Tipo de cuenta: " + account.getAccountType());
        } else {
            if (account == null) {
                JOptionPane.showMessageDialog(null, "El documento ingresado no existe.");
            } else {
                JOptionPane.showMessageDialog(null, "Contraseña incorrecta.");
            }
        }
    }

    public static String encryptPassword(String passwordDesencriptada) 
    {
        String passwordEncriptada = passwordDesencriptada;
        return passwordEncriptada;
    }

    public static String decryptPassword(String passwordEncriptada) 
    {
        String passwordDesencriptada = passwordEncriptada;
        return passwordDesencriptada;
    }
//===============================================================================
//AUXILIARES
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

   

    public static BankAccount searchAccount(String document) 
    {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("cuentas.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String storedDocument = parts[2].trim();
                if (storedDocument.equals(document)) {
                    reader.close();
                    // Construir un objeto BankAccount con los datos y devolverlo
                    return new BankAccount(
                            parts[0].trim(), // nombre
                            Double.parseDouble(parts[1].trim()), // saldo
                            storedDocument, // documento
                            parts[3].trim(), // tipo de cuenta
                            parts[4].trim() // contraseña
                    );
                }
            }
            reader.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo.");
            e.printStackTrace();
        }
        return null;
    }
    

    public static boolean comparePassword(String password, String savedPassword) 
    {
        return password.equals(savedPassword);
    }

  
}


