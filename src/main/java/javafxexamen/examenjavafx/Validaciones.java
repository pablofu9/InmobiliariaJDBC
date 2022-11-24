package javafxexamen.examenjavafx;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Validaciones {

    public static void crearAlertaInfo(String textoAlerta) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Información");
        alert.setContentText(textoAlerta);
        alert.showAndWait();
    }

    public static boolean crearAlertaConf(String confirmar) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(confirmar);
        Optional<ButtonType> action = alert.showAndWait();
        if (action.get() == ButtonType.OK) {
            return true;
        } else {
            return false;
        }
    }
    
    public static boolean validarTelefono(String num){
        boolean valido=true;
        for(int i = 0 ; i < num.length(); i++){
            if(!Character.isDigit(num.charAt(i))){
                valido=false;
            }else{
                if(!num.startsWith("6") || !num.startsWith("6")){
                    valido = false;
                }else{
                    if(num.length()!=9){
                        valido = false;
                    }else{
                        valido = true;
                    }
                    
                }
            }
        }
       return valido;
    }
}
