package Principal;
import javax.swing.JOptionPane;

public class Principal {

    public static void main(String[] args) {

        int ano = 1;
        double valorFinal = 0;
        double investimento = Double.parseDouble(JOptionPane.showInputDialog("Quanto você irá investir por ano?"));;
        
        for (int idade = 21; idade <= 65; idade++) {
            if (ano <= 8) {
                valorFinal += investimento;
                valorFinal += valorFinal*0.10;
                ano++;
            } else {
                valorFinal += valorFinal*0.10;
            }
        }
        JOptionPane.showMessageDialog(null, valorFinal);
    }

}
