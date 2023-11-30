package restaurante;

import java.util.List;

public class Pedido {
    private Usuario cliente;
    private List<Producto> productos;

    public Pedido(Usuario cliente, List<Producto> productos) {
        this.cliente = cliente;
        this.productos = productos;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int calcularTotal() {
        int total = 0;
        for (Producto producto : productos) {
            total += producto.getPrecio();
        }
        return total;
    }

    public void generarReporte() {
        StringBuilder reporte = new StringBuilder("------------------------\n");
        reporte.append("Pedido de ").append(cliente.getNombre()).append("\n");
        reporte.append("Productos:\n");
        for (Producto producto : productos) {
            reporte.append(producto.getNombre()).append(" - ").append(producto.getPrecio()).append("\n");
        }
        reporte.append("Total: ").append(calcularTotal()).append("\n");
        System.out.println(reporte.toString());
    }
}
