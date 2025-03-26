package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProcessdorFuncionarios {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = Arrays.asList(
            new Funcionario("Letícia", 24, 1500),
            new Funcionario("Mônica", 35, 1900),
            new Funcionario("Vinicius", 23, 2500),
            new Funcionario("Fernada", 44, 5500),
            new Funcionario("Luana", 22, 1000)
        );
        List<Funcionario> funcionariosComMaisDe30 = funcionarios.stream()
                .filter(f -> f.getIdade() >= 30) //utilizado para filtrar pessoas com >= 30 anos
                .collect(Collectors.toList());
        funcionariosComMaisDe30.forEach(System.out::println); //Exibe as informações dos funcionarios com >= de 30 anos

        List<String>nomesDosFuncionarios = funcionarios.stream()
                .map(Funcionario::getNome)//mostra todos os nomes dos usuarios
                .collect(Collectors.toList());
        nomesDosFuncionarios.forEach(System.out::println);

        double somaSalarios = funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .sum(); //utilizado para somar os salarios
        System.out.println(somaSalarios);
    }
}
