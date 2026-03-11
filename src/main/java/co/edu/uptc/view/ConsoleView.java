package co.edu.uptc.view;

import java.util.Scanner;

import co.edu.uptc.interfaces.PresenterInterface;
import co.edu.uptc.interfaces.ViewInterface;

public class ConsoleView implements ViewInterface {
    private PresenterInterface presenter;
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void setPresenter(PresenterInterface presenter) {
        this.presenter = presenter;
    }

    @Override
    public void start() {
        System.out.println("Aplicación iniciada");
    }

    public void showMessage(String mensaje) {
        System.out.println(mensaje);
    }

    public String readLine(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    public int readInt(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(readLine(mensaje));
            } catch (NumberFormatException e) {
                showMessage("Entrada inválida. Por favor ingrese un número entero.");
            }
        }
    }

    public double readDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(readLine(mensaje));
            } catch (NumberFormatException e) {
                showMessage("Entrada inválida. Por favor ingrese un número válido.");
            }
        }
    }
}
