package restaurante;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;
import java.util.Comparator;

public class Restaurante {
    private List<Producto> productos;
    private List<Pedido> pedidos;
    private List<Usuario> usuarios;

    public Restaurante(List<Producto> productos, List<Pedido> pedidos, List<Usuario> usuarios) {
        this.productos = productos;
        this.pedidos = pedidos;
        this.usuarios = usuarios;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void generarReporte() {
        int total = calcularTotal();
        System.out.println("------------------------");
        System.out.println("El total de ventas para el restaurante es: " + total);
        List<Pedido> pedidosOrdenados = this.pedidosPorPrecio();
        for (Pedido pedido : pedidosOrdenados) {
            pedido.generarReporte();
        }
    }

    private int calcularTotal() {
        int total = 0;
        for (Pedido pedido : pedidos) {
            for (Producto producto : pedido.getProductos()) {
                total += producto.getPrecio();
            }
        }
        return total;
    }

    public List<Pedido> pedidosPorPrecio() {
        List<Pedido> pedidosCopia = new ArrayList<>(pedidos);
        List<Pedido> pedidosOrdenados = ordenarPedidosPorPrecioHelper(pedidosCopia);
        Collections.reverse(pedidosOrdenados);
        return pedidosOrdenados;
    }

    private List<Pedido> ordenarPedidosPorPrecioHelper(List<Pedido> pedidos) {
        if (pedidos.size() <= 1) {
            return pedidos;
        }
        List<Pedido> pedidosOrdenados = new ArrayList<>(pedidos);
        pedidosOrdenados.sort(Comparator.comparingInt(Pedido::calcularTotal));
        Collections.reverse(pedidosOrdenados);
        return pedidosOrdenados;
    }

}
