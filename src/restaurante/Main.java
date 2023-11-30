package restaurante;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Main {
    private static final String[] NOMBRES_PRODUCTOS = { "Hamburguesa", "Papas", "Refresco", "Helado" };
    private static final String[] NOMBRES_USUARIOS = { "Juan", "Pedro", "Maria", "Ana" };
    private static final String[] DIRECCIONES = { "Calle 1", "Calle 2", "Avenida 3", "Plaza 4" };
    private static final int MAX_PRECIO = 150;
    private static final int MIN_PRECIO = 20;
    private static final int NUMERO_DE_PEDIDOS = 3;
    private static SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases

    private static ArrayList<Producto> crearProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        for (String nombre : NOMBRES_PRODUCTOS) {
            int precio = random.nextInt(MAX_PRECIO - MIN_PRECIO + 1) + MIN_PRECIO;
            productos.add(new Producto(nombre, precio));
        }
        return productos;
    }

    private static ArrayList<Usuario> crearUsuarios() {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for (String nombre : NOMBRES_USUARIOS) {
            String direccion = DIRECCIONES[random.nextInt(DIRECCIONES.length)];
            usuarios.add(new Usuario(nombre, direccion, new ArrayList<>()));
        }
        return usuarios;
    }

    private static ArrayList<Pedido> crearPedidos(ArrayList<Usuario> usuarios, ArrayList<Producto> productos) {
        ArrayList<Pedido> pedidos = new ArrayList<>();
        for (int i = 0; i < NUMERO_DE_PEDIDOS; i++) {
            Usuario usuario = usuarios.get(random.nextInt(usuarios.size()));
            ArrayList<Producto> productosPedido = new ArrayList<>();
            int numProductos = random.nextInt(productos.size()) + 1; // al menos 1 producto en el pedido
            for (int j = 0; j < numProductos; j++) {
                Producto producto = productos.get(random.nextInt(productos.size()));
                productosPedido.add(producto);
            }
            pedidos.add(new Pedido(usuario, productosPedido));
        }
        return pedidos;
    }

    private static void asignarPedidosAUsuarios(ArrayList<Usuario> usuarios, ArrayList<Pedido> pedidos) {
        int numUsuarios = usuarios.size();
        int numPedidos = pedidos.size();
        for (int i = 0; i < numUsuarios; i++) {
            ArrayList<Pedido> pedidosUsuario = new ArrayList<>();
            for (int j = i; j < numPedidos; j += numUsuarios) {
                pedidosUsuario.add(pedidos.get(j));
            }
            usuarios.get(i).setPedidos(pedidosUsuario);
        }
    }

    public static void main(String[] args) {
        ArrayList<Producto> productos = crearProductos();
        ArrayList<Usuario> usuarios = crearUsuarios();
        ArrayList<Pedido> pedidos = crearPedidos(usuarios, productos);
        asignarPedidosAUsuarios(usuarios, pedidos);
        Restaurante restaurante = new Restaurante(productos, pedidos, usuarios);
        restaurante.generarReporte();
        usuarios.get(0).generarReporte();
        usuarios.get(1).generarReporte();
    }
}
