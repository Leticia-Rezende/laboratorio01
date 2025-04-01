package model.laboratorio02;

import model.laboratorio01.Funcionario;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Nodes.collect;
import static sun.awt.image.MultiResolutionToolkitImage.map;

public class Pedido {
    private String cliente;
    private List<Item> itens;
    private boolean pago;

    public Pedido(String cliente, List<Item> itens, boolean pago) {
        this.cliente = cliente;
        this.itens = itens;
        this.pago = pago;
    }

    public static void main(String[] args) {
        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Sandra", Arrays.asList(new Item("Secador", 1500), new Item("Chapinha", 150)), true),
                new Pedido("Márcio", Arrays.asList(new Item("Barbeador", 100), new Item("Creme de Barbear", 50)), false),
                new Pedido("Luiza", Arrays.asList(new Item("Perfume", 109), new Item("Creme Corporal", 39.99)), true),
                new Pedido("Amanda", Arrays.asList(new Item("Creme para mãos", 103), new Item("Oléo Corporal", 99)), true)
        );
        List<Pedido> pedidosPagos = pedidos.stream()
                .filter(pedido -> pedido.pago) //Filtra os pedidos pagos
                .map(pedido -> {
                    double valorTotalPedido = pedido.itens.stream()
                            .mapToDouble(item -> item.valor)
                            .sum(); //calcula o valor total de cada pedido
                    if (valorTotalPedido > 1000) {
                        valorTotalPedido *= 0.9; //aplica o desconto de 10% para pedidos acima de 1000
                    }
                    pedido.itens.add(new Item("Valor total", valorTotalPedido));
                    return pedido;
                })
                .filter(pedido -> pedido.itens.stream()
                        .filter(item -> item.nomeProduto.equals("Valor total do pedido"))
                        .findFirst()
                        .get().valor > 1500)
                .map(pedido -> pedido.cliente)
                .collect(Collectors.toList());
        pedidosPagos.forEach(System.out::println);

        List<String> clientesComPedidosAcimaDe1500 = pedidos.stream();
                .map(Pedido::getCliente)//mostra todos os clientes com compras acima de 1500 reais
                .collect(Collectors.toList());
        System.out.println("Clientes com pedidos acima de R$ 1500,00: " + clientesComPedidosAcimaDe1500);
    }
}
