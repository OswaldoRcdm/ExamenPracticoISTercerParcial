<%-- 
    Document   : index
    Created on : 16/12/2024, 09:04:47 PM
    Author     : OswaldoRcdm
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="crud.logica, java.util.List" %>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Registro de Equipos Electrónicos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }
        .content-wrapper {
            flex: 1;
        }
        .footer {
            background-color: #f8f9fa;
            padding: 20px 0;
            margin-top: 30px;
        }
    </style>
</head>
<body>
    <!-- Header -->
    <header class="bg-primary text-white py-4">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-8">
                    <h1 class="m-0">
                        <i class="fas fa-laptop me-2"></i>Gestión de Equipos Electrónicos OSWALDO BENITEZ
                    </h1>
                </div>
            </div>
        </div>
    </header>

    <!-- Contenido Principal -->
    <div class="container mt-4 content-wrapper">
        <%
        logica Logic = new logica();
        
        // Procesar formulario si hay datos
        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String accion = request.getParameter("accion");
            
            if ("agregar".equals(accion)) {
                String nombre = request.getParameter("nombre");
                String marca = request.getParameter("marca");
                double precio = Double.parseDouble(request.getParameter("precio"));
                
                boolean resultado = Logic.agregarEquipo(nombre, marca, precio);
                
                if (resultado) {
                    out.println("<div class='alert alert-success'><i class='fas fa-check-circle me-2'></i>Producto agregado exitosamente</div>");
                } else {
                    out.println("<div class='alert alert-danger'><i class='fas fa-exclamation-triangle me-2'></i>Error al agregar el producto</div>");
                }
            } else if ("eliminar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Logic.eliminarEquipo(id);
            } else if ("editar".equals(accion)) {
                int id = Integer.parseInt(request.getParameter("id"));
                String nombre = request.getParameter("nombre");
                String marca = request.getParameter("marca");
                double precio = Double.parseDouble(request.getParameter("precio"));
                
                boolean resultado = Logic.actualizarEquipo(id, nombre, marca, precio);
                
                if (resultado) {
                    out.println("<div class='alert alert-success'><i class='fas fa-check-circle me-2'></i>Producto actualizado exitosamente</div>");
                } else {
                    out.println("<div class='alert alert-danger'><i class='fas fa-exclamation-triangle me-2'></i>Error al actualizar el producto</div>");
                }
            }
        }
        
        // Obtener lista de equipos
        List<logica.Equipo> equipos = Logic.obtenerEquipos();
        %>
        
        <div class="row">
            <div class="col-md-4">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <i class="fas fa-plus-circle me-2"></i>Agregar Equipo
                    </div>
                    <div class="card-body">
                        <form method="post">
                            <input type="hidden" name="accion" value="agregar">
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-tag me-2"></i>Nombre</label>
                                <input type="text" class="form-control" name="nombre" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-copyright me-2"></i>Marca</label>
                                <input type="text" class="form-control" name="marca" required>
                            </div>
                            <div class="mb-3">
                                <label class="form-label"><i class="fas fa-dollar-sign me-2"></i>Precio</label>
                                <input type="number" class="form-control" name="precio" step="0.01" required>
                            </div>
                            <button type="submit" class="btn btn-primary">
                                <i class="fas fa-save me-2"></i>Agregar
                            </button>
                        </form>
                    </div>
                </div>
            </div>
            
            <div class="col-md-8">
                <div class="card shadow">
                    <div class="card-header bg-primary text-white">
                        <i class="fas fa-list me-2"></i>Lista de Equipos
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre</th>
                                        <th>Marca</th>
                                        <th>Precio</th>
                                        <th>Acciones</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <% for (logica.Equipo equipo : equipos) { %>
                                    <tr>
                                        <td><%= equipo.getId() %></td>
                                        <td><%= equipo.getNombre() %></td>
                                        <td><%= equipo.getMarca() %></td>
                                        <td>$<%= String.format("%.2f", equipo.getPrecio()) %></td>
                                        <td>
                                            <button type="button" class="btn btn-warning btn-sm" data-bs-toggle="modal" 
                                                data-bs-target="#editModal" 
                                                data-id="<%= equipo.getId() %>"
                                                data-nombre="<%= equipo.getNombre() %>"
                                                data-marca="<%= equipo.getMarca() %>"
                                                data-precio="<%= equipo.getPrecio() %>">
                                                <i class="fas fa-edit me-1"></i>Editar
                                            </button>
                                            <form method="post" class="d-inline">
                                                <input type="hidden" name="accion" value="eliminar">
                                                <input type="hidden" name="id" value="<%= equipo.getId() %>">
                                                <button type="submit" class="btn btn-danger btn-sm">
                                                    <i class="fas fa-trash me-1"></i>Eliminar
                                                </button>
                                            </form>
                                        </td>
                                    </tr>
                                    <% } %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal de Edición (Esto es una ventana emergente)-->
    <div class="modal fade" id="editModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title"><i class="fas fa-edit me-2"></i>Editar Equipo</h5>
                    <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                </div>
                <form method="post">
                    <div class="modal-body">
                        <input type="hidden" name="accion" value="editar">
                        <input type="hidden" name="id" id="editId">
                        <div class="mb-3">
                            <label class="form-label"><i class="fas fa-tag me-2"></i>Nombre</label>
                            <input type="text" class="form-control" name="nombre" id="editNombre" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><i class="fas fa-copyright me-2"></i>Marca</label>
                            <input type="text" class="form-control" name="marca" id="editMarca" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label"><i class="fas fa-dollar-sign me-2"></i>Precio</label>
                            <input type="number" class="form-control" name="precio" id="editPrecio" step="0.01" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                            <i class="fas fa-times me-2"></i>Cancelar
                        </button>
                        <button type="submit" class="btn btn-primary">
                            <i class="fas fa-save me-2"></i>Guardar Cambios
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <p class="mb-0">
                        <i class="fas fa-laptop-code me-2"></i>Sistema de Gestión de Equipos Electrónicos EXAMEN IS
                    </p>
                    <small class="text-muted">© 2024 Todos los derechos reservados Oswaldo Benitez Camacho</small>
                </div>
            </div>
        </div>
    </footer>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        const editModal = document.getElementById('editModal')
        editModal.addEventListener('show.bs.modal', event => {
            const button = event.relatedTarget
            const id = button.getAttribute('data-id')
            const nombre = button.getAttribute('data-nombre')
            const marca = button.getAttribute('data-marca')
            const precio = button.getAttribute('data-precio')

            const modalId = editModal.querySelector('#editId')
            const modalNombre = editModal.querySelector('#editNombre')
            const modalMarca = editModal.querySelector('#editMarca')
            const modalPrecio = editModal.querySelector('#editPrecio')

            modalId.value = id
            modalNombre.value = nombre
            modalMarca.value = marca
            modalPrecio.value = precio
        })
    </script>
</body>
</html>