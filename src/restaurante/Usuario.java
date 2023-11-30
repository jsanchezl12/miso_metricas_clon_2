package restaurante;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Usuario {
    private String nombre;
    private String direccion;
    private List<Pedido> pedidos;
    public Usuario(String nombre, String direccion, List<Pedido> pedidos) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.pedidos = pedidos;
    }
    public String getNombre() {
        return nombre;
    }
    public String getDireccion() {
        return direccion;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void generarReporte() {
        int total = calcularTotal();
        StringBuilder reporte = new StringBuilder("------------------------\n");
        reporte.append("El total de compras para ").append(nombre).append(" es: ").append(total).append("\n");
        System.out.println(reporte.toString());
        List<Pedido> pedidosOrdenados = ordenarPedidosPorPrecio();
        for(Pedido pedido : pedidosOrdenados) {
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

    public List<Pedido> ordenarPedidosPorPrecio() {
        List<Pedido> pedidosOrdenados = new ArrayList<>();
        for (Pedido pedido : pedidos) {
            pedidosOrdenados.add(pedido);
        }
        for (int i = 0; i < pedidosOrdenados.size(); i++) {
            for (int j = 0; j < pedidosOrdenados.size() - 1; j++) {
                if (pedidosOrdenados.get(j).calcularTotal() > pedidosOrdenados.get(j + 1).calcularTotal()) {
                    Pedido aux = pedidosOrdenados.get(j);
                    pedidosOrdenados.set(j, pedidosOrdenados.get(j + 1));
                    pedidosOrdenados.set(j + 1, aux);
                }
            }
        }
        Collections.reverse(pedidosOrdenados);
        return pedidosOrdenados;
    }
}
