package com.example.demo.controller;

import com.example.demo.model.Categoria;
import com.example.demo.service.SCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categoria")
public class CCategoria {

    @Autowired
    private SCategoria sCategoria;

    @GetMapping("/listar")
    public String listarCategorias(Model model) {
        model.addAttribute("categorias", sCategoria.listarCategorias());
        return "categoria/listar";
    }

    @GetMapping("/crear")
    public String nuevaCategoria(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoria/crear";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria) {
        sCategoria.crearCategoria(categoria);
        return "redirect:/categoria/listar";
    }

    @GetMapping("/editar/{id}")
    public String editarCategoria(@PathVariable Integer id, Model model) {
        Categoria categoria = sCategoria.buscarCategoriaPorId(id).orElse(null);
        model.addAttribute("categoria", categoria);
        return "categoria/editar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Integer id) {
        sCategoria.eliminarCategoria(id);
        return "redirect:/categoria/listar";
    }
    
    @GetMapping("/buscar")
    public String buscarPorNombre(@RequestParam("nombre") String nombre, Model model) {
        model.addAttribute("categorias", sCategoria.buscarPorNombre(nombre));
        return "categoria/listar";
    }
}
