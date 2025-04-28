package com.example.demo.controller;

import com.example.demo.model.Proveedor;
import com.example.demo.service.SProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/proveedor")
public class CProveedor {

    @Autowired
    private SProveedor sProveedor;

    @GetMapping("/listar")
    public String listarProveedores(@RequestParam(value = "nombre", required = false) String nombre, Model model) {
        if (nombre != null && !nombre.isEmpty()) {
            model.addAttribute("proveedores", sProveedor.buscarProveedoresPorNombre(nombre));
        } else {
            model.addAttribute("proveedores", sProveedor.listarProveedores());
        }
        return "proveedor/ListarBuscar";
    }
    @GetMapping("/crear")
    public String nuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedor/crear";
    }

    @PostMapping("/guardar")
    public String guardarProveedor(@ModelAttribute Proveedor proveedor) {
        sProveedor.crearProveedor(proveedor);
        return "redirect:/proveedor/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarProveedor(@PathVariable Integer id, Model model) {
        Proveedor proveedor = sProveedor.buscarProveedorPorId(id).orElse(null);
        model.addAttribute("proveedor", proveedor);
        return "proveedor/editar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable Integer id) {
        sProveedor.eliminarProveedor(id);
        return "redirect:/proveedor/listar";
    }
}
