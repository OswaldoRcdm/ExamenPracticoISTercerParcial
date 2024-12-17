/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author OswaldoRcdm
 */
public class logica {
   // Configuración de conexión a base de datos
    private static final String URL = "jdbc:mysql://localhost/electronica_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    // Método para establecer conexión
    private Connection getConnection() throws SQLException {
    try {
        Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e) {
        System.out.println("Error: Driver JDBC no encontrado");
        e.printStackTrace();
        throw new SQLException("Driver JDBC no encontrado");
    }

    try {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Conexión establecida exitosamente");
        return conn;
    } catch (SQLException e) {
        System.out.println("Error al establecer conexión:");
        System.out.println("URL: " + URL);
        System.out.println("Usuario: " + USER);
        System.out.println("Mensaje de error: " + e.getMessage());
        e.printStackTrace();
        throw e;
    }
}

    // Clase interna para representar un equipo electrónico
    public class Equipo {
        private int id;
        private String nombre;
        private String marca;
        private double precio;

        public Equipo(int id, String nombre, String marca, double precio) {
            this.id = id;
            this.nombre = nombre;
            this.marca = marca;
            this.precio = precio;
        }

        // Getters
        public int getId() { return id; }
        public String getNombre() { return nombre; }
        public String getMarca() { return marca; }
        public double getPrecio() { return precio; }
    }

    // Método para agregar un equipo
    public boolean agregarEquipo(String nombre, String marca, double precio) {
        String query = "INSERT INTO equipos (nombre, marca, precio) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, marca);
            pstmt.setDouble(3, precio);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para obtener todos los equipos
    public List<Equipo> obtenerEquipos() {
        List<Equipo> equipos = new ArrayList<>();
        String query = "SELECT id, nombre, marca, precio FROM equipos";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                equipos.add(new Equipo(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("marca"),
                    rs.getDouble("precio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return equipos;
    }

    // Método para eliminar un equipo
    public boolean eliminarEquipo(int id) {
        String query = "DELETE FROM equipos WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Método para actualizar un equipo
    public boolean actualizarEquipo(int id, String nombre, String marca, double precio) {
        String query = "UPDATE equipos SET nombre = ?, marca = ?, precio = ? WHERE id = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, nombre);
            pstmt.setString(2, marca);
            pstmt.setDouble(3, precio);
            pstmt.setInt(4, id);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
