package DistribuidoraDeGas.negocio.gerenciadores;

import DistribuidoraDeGas.dados.RepositorioProdutosVendidos;
import DistribuidoraDeGas.negocio.entidades.Venda;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class NegocioVenda {
/*
    public static void registarVenda(Venda venda){
        repositorioProdutosVendidos2.adicionarVenda(venda);
    }

    public void marcarVendaConcluida(Venda v){
        v.concluirStatus();
        this.repositorioProdutosVendidos.atualizarVenda(v);
    }

    public ArrayList<Venda> consultarVendaPorData(String data){
        return this.repositorioProdutosVendidos.consultarVendaPorData(data);
    }

    public ArrayList<Venda> consultarVendaProdutos(){
        return this.repositorioProdutosVendidos.consultarVendas();
    }

    public void alterarVenda(Venda venda, String data, String hora){
        venda.setData(data);
        venda.setHora(hora);
        this.repositorioProdutosVendidos.atualizarVenda(venda);
    }

    public ArrayList<Venda> consultarVendaCliente(String cpf){
        return this.repositorioProdutosVendidos.consultarVendaPeloCliente(cpf);
    }

   public ArrayList<Venda> consultarVendasCLienteNaoConcluida(String cpf){
        ArrayList<Venda> vendaCliente = this.repositorioProdutosVendidos.consultarVendaPeloCliente(cpf);
        ArrayList<Venda> vendas = new ArrayList<>();

        for(Venda v: vendaCliente){
            if(v.getStatus().equals("Não concluída")){
                vendas.add(v);
            }
        }
        return vendas;
    }

    public ArrayList<String> consultarHorariosDisponiveisPorData(String data){
        SimpleDateFormat sdfData = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfDataComp = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        GregorianCalendar gc = new GregorianCalendar();

        gc.setTime(new Date());
        gc.add(Calendar.HOUR,-1);
        String dataAtual = sdfData.format(gc.getTime());

        ArrayList<String> listaHoras = new ArrayList<String>(Arrays.asList("08:00", "09:00", "10:00", "11:00", "12:00"));

        GregorianCalendar horaTemp = new GregorianCalendar();
        ArrayList<String> horasRemovidasTemp = new ArrayList<>();

        if(data.equals(dataAtual)){
            for(String hora: listaHoras){
                try{
                    horaTemp.setTime(sdfDataComp.parse(dataAtual + " " + hora));
                    if(horaTemp.getTime().before(gc.getTime())){
                        horasRemovidasTemp.add(hora);
                    }
                }catch (ParseException e){
                    e.printStackTrace();
                }
            }for(String hora: horasRemovidasTemp){
                listaHoras.remove(hora);
            }
        }
        return listaHoras;
    }

   public ArrayList<Venda> consultarVendaClientePorData(String cpf, String data){
        ArrayList<Venda> vendaPorData = this.repositorioProdutosVendidos.consultarVendaPorData(data);
        ArrayList<Venda> vendaClientePorData = new ArrayList<>();

        for(Venda v: vendaPorData){
            if(v.getCliente().getCpf().equals(cpf)){
                vendaClientePorData.add(v);
            }
        }
        return vendaClientePorData;
    }*/
}
