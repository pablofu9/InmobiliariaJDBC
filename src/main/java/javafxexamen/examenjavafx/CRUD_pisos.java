package javafxexamen.examenjavafx;

import Conexion.Conexion;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class CRUD_pisos {

    //METODO PARA INSERTAR PISOS
    public static void insertarPisos(Piso piso) {
        PreparedStatement ps;
        String sqlPiso;
        Connection con = Conexion.getConexion();
        
        try {
            sqlPiso = "INSERT into pisos(Id_Zona,Tipo_Operacion,Metros,Nombre_propietario,Telefono_contacto,Precio,Reservado) values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sqlPiso);

            ps.setInt(1, piso.getId_zona());
            ps.setString(2, piso.getTipo_operacion());
            ps.setInt(3, piso.getMetros());
            ps.setString(4, piso.getNombre_prop());
            ps.setString(5, piso.getTelefono_prop());
            ps.setInt(6, piso.getPrecio());
            ps.setBoolean(7, piso.isReservado());
            ps.executeUpdate();
            Validaciones.crearAlertaInfo("Piso insertado");
        } catch (SQLException e) {
            Validaciones.crearAlertaInfo("No se ha podido insertar el piso");

        }

    }

    //METODO PARA LLENAR LA TABLA
    public static ObservableList<Piso> getPisos() {
        Connection con = Conexion.getConexion();
        ObservableList<Piso> listaPisos;
        listaPisos = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM pisos");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaPisos.add(new Piso(rs.getInt("Id_piso"), rs.getInt("Id_Zona"), rs.getString("Tipo_Operacion"), rs.getInt("Metros"),
                        rs.getString("Nombre_propietario"), rs.getString("Telefono_contacto"), rs.getInt("Precio"), rs.getBoolean("Reservado")));
            }
        } catch (SQLException ex) {

        }
        return listaPisos;
    }

    public static ObservableList<Piso> getPisosFiltered(int id_zona) {
        Connection con = Conexion.getConexion();
        ObservableList<Piso> listaPisos;
        listaPisos = FXCollections.observableArrayList();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("SELECT * FROM pisos WHERE Id_Zona =? AND Reservado=?");
            ps.setInt(1, id_zona);
            ps.setBoolean(2, false);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                listaPisos.add(new Piso(rs.getInt("Id_piso"), rs.getInt("Id_Zona"), rs.getString("Tipo_Operacion"), rs.getInt("Metros"),
                        rs.getString("Nombre_propietario"), rs.getString("Telefono_contacto"), rs.getInt("Precio"), rs.getBoolean("Reservado")));
            }
        } catch (SQLException ex) {

        }
        return listaPisos;
    }

    //METODO PARA LLENAR EL COMBO
    public static void llenarCombo(ComboBox c) {
        Connection con = Conexion.getConexion();
        List<String> tipo = new ArrayList<String>();
        tipo.add("Alquiler");
        tipo.add("Compra");
        ObservableList<String> listaCombo = FXCollections.observableArrayList(tipo);
        c.setItems(listaCombo);
    }

    public static void borrarPiso(int id_piso) {
        //metodo que borra un piso según el id del piso que este seleccionado
        Connection con = Conexion.getConexion();
        String sentenciaSql = "DELETE  from  pisos WHERE Id_piso = ?";
        PreparedStatement sentencia = null;

        try {
            sentencia = con.prepareStatement(sentenciaSql);
            sentencia.setInt(1, id_piso);
            sentencia.execute();
        } catch (SQLException e) {

        }
    }

    public static Boolean buscarPiso(int id_piso) {
        Connection con = Conexion.getConexion();
        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado = false;

        try {
            String SQL = "SELECT * FROM pisos WHERE Id_piso = ? ;";
            ps = (PreparedStatement) con.prepareStatement(SQL, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setInt(1, id_piso);
            rs = ps.executeQuery();

            if (rs.next()) {
                encontrado = true;

            }
        } catch (SQLException ex) {

        }
        return encontrado;

    }

    //Metodo para reservar piso
    public static void reservarPiso(int id_piso) {
        Connection con = Conexion.getConexion();

        try {
            String sentenciaSql = "UPDATE pisos SET Reservado = ? "
                    + "WHERE Id_piso=?";
            PreparedStatement sentencia = null;
            sentencia = con.prepareStatement(sentenciaSql);
            sentencia.setBoolean(1, true);
            sentencia.setInt(2, id_piso);

            sentencia.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
