package javafxexamen.examenjavafx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class PrimaryController implements Initializable {

    @FXML
    private Button btnALtaJDBC, btnAlquiler, btnReservar, btnLimpiar, btnBorrar, btnBorrarId, btnEliminar, btnReset;

    @FXML
    private TextField txtId_zona, txtMetros, txtNombre, txtTelefono, txtPrecio, txtId_piso, txtPisoBorrar;

    @FXML
    private CheckBox reservado;

    @FXML
    private ComboBox combo_operacion;

    @FXML
    private TableView<Piso> tabla;

    @FXML
    private TableColumn<Piso, Integer> col_idPiso;
    @FXML
    private TableColumn<Piso, Integer> col_idZona;
    @FXML
    private TableColumn<Piso, String> col_tipo;
    @FXML
    private TableColumn<Piso, String> col_nombre;
    @FXML
    private TableColumn<Piso, String> col_telefono;
    @FXML
    private TableColumn<Piso, Integer> col_precio;
    @FXML
    private TableColumn<Piso, Integer> col_metros;
    @FXML
    private TableColumn<Piso, Boolean> col_reservado;

    @FXML
    private Pane panelOculto;

    @FXML
    private void btnAltaJDBCOnClick() throws IOException {
        if (Validaciones.validarTelefono(txtTelefono.getText())) {
            int id_zona = Integer.parseInt(txtId_zona.getText());
            int metros = Integer.parseInt(txtMetros.getText());
            String nombreProp = txtNombre.getText();
            String telefono = txtTelefono.getText();
            int precio = Integer.parseInt(txtPrecio.getText());

            boolean reserved;
            if (reservado.isSelected()) {
                reserved = true;
            } else {
                reserved = false;
            }
            String tipo = combo_operacion.getValue().toString();
            String operacion;

            Piso p1 = new Piso(id_zona, tipo, metros, nombreProp, telefono, precio, reserved);

            //VOLVEMOS A CARGAR LA TABLA
            CRUD_pisos.insertarPisos(p1);
            cargarTabla();
        }else{
            Validaciones.crearAlertaInfo("Telefono incorrecto");
        }
        //Este es el metodo para insertar, lee el formulario de arriba y coge los campos y crear un objeto piso. Despues lo añade a la BD

    }

    @FXML
    private void borrarPiso() {

        tabla.getItems().removeAll(tabla.getSelectionModel().getSelectedItem());
        CRUD_pisos.borrarPiso(Integer.parseInt(txtId_piso.getText()));
    }//Con este boton borramos directamente de la tabla

    @FXML
    private void borrarPisoId() {//Este boton nos hace visible el panel en el que seleccionamos el id del usuario para que borres el que tu quieras
        panelOculto.setVisible(true);
    }

    @FXML
    private void btnEliminarClick() { //Para eliminar piso escribiendole el id
        if (CRUD_pisos.buscarPiso(Integer.parseInt(txtPisoBorrar.getText()))) {
            if (Validaciones.crearAlertaConf("Seguro que quieres borrar el piso")) {
                CRUD_pisos.borrarPiso(Integer.parseInt(txtPisoBorrar.getText()));
                Validaciones.crearAlertaInfo("Piso eliminado");
                limpiar();
                cargarTabla();
                panelOculto.setVisible(false);
            } else {

            }

        } else {
            Validaciones.crearAlertaInfo("Piso no encontrado");

        }

    }

    //Por lo tanto, podremos desde la tabla directamente o introduciendo el ID
    @FXML
    public void filtrar() {//Metodo para filtrar
        col_idPiso.setCellValueFactory(new PropertyValueFactory<>("id_piso"));
        col_idZona.setCellValueFactory(new PropertyValueFactory<>("id_zona"));
        col_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo_operacion"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_prop"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono_prop"));
        col_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        col_metros.setCellValueFactory(new PropertyValueFactory<>("metros"));
        col_reservado.setCellValueFactory(new PropertyValueFactory<>("reservado"));
        //Nos va a filtrar los pisos no reservados de la zona que seleccionemos

        tabla.setItems(CRUD_pisos.getPisosFiltered(Integer.parseInt(txtId_zona.getText())));
        //El metodo reset del boton de abajo, lo usamos para cuando hemos filtrado, volver a mostrar toda la tabla
    }

    @FXML
    public void pisoSelected() {
        //Lo que hacemos aquí es, ver sobre que objeto hemos pinchado y volcar los campos de la tabla al formulario de arriba
        int index = tabla.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return;
        }
        txtId_piso.setText(col_idPiso.getCellData(index).toString());
        txtId_zona.setText(col_idZona.getCellData(index).toString());
        txtNombre.setText(col_nombre.getCellData(index));
        txtTelefono.setText(col_telefono.getCellData(index));
        txtMetros.setText(col_metros.getCellData(index).toString());
        txtPrecio.setText(col_precio.getCellData(index).toString());

        if (col_reservado.getCellData(index).toString().equals("true")) {
            reservado.setSelected(true);
        } else {
            reservado.setSelected(false);
        }

        if (col_tipo.getCellData(index).equals("Alquiler")) {
            combo_operacion.setValue("Alquiler");
        } else {
            combo_operacion.setValue("Compra");
        }
    }//ESTE METODO SE LO PONEMOS A LA TABLA EN ONMOUSECLICKED
    //Lo que hace es rellenar el formulario de arriba con la información que corresponde

    @FXML
    public void reset() {//Metodo del boton de resetear, por si hemos filtrado y queremos volver a ver toda la tabla
        cargarTabla();
    }

    //Resetar formulario
    @FXML
    public void limpiar() {
        txtId_zona.setText(" ");
        txtMetros.setText(" ");
        txtNombre.setText(" ");
        txtId_piso.setText(" ");
        txtTelefono.setText(" ");
        txtPrecio.setText(" ");
    }

    @FXML
    public void reservar() { //Con este metodo, usamos el metodo de reservar de crud y lo que hacemos es, cuando seleccionamos un item de la tabla
        //se vuelca arriba y si le damos a reservar, coge el id del campo id_piso y reserva ese piso, recarga la tabla y pone el checkox reservado a true
        pisoSelected();
        CRUD_pisos.reservarPiso(Integer.parseInt(txtId_piso.getText()));
        reservado.setSelected(true);
        cargarTabla();
    }

    public void cargarTabla() {
        col_idPiso.setCellValueFactory(new PropertyValueFactory<>("id_piso"));
        col_idZona.setCellValueFactory(new PropertyValueFactory<>("id_zona"));
        col_tipo.setCellValueFactory(new PropertyValueFactory<>("tipo_operacion"));
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre_prop"));
        col_telefono.setCellValueFactory(new PropertyValueFactory<>("telefono_prop"));
        col_precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        col_metros.setCellValueFactory(new PropertyValueFactory<>("metros"));
        col_reservado.setCellValueFactory(new PropertyValueFactory<>("reservado"));

        tabla.setItems(CRUD_pisos.getPisos());
    } //METODO PARA CARGAR LA TABLA

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTabla();
        CRUD_pisos.llenarCombo(combo_operacion);
    }
}
