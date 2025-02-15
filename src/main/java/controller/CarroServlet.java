package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Carro;
import persistence.CarroDao;
import persistence.GenericDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/carro")
public class CarroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public CarroServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String placa = request.getParameter("id");
		
		Carro c = new Carro();
		List<Carro> carros = new ArrayList<>();
		String erro = "";
		try {
			if (!placa.isBlank() || placa == null) {
				GenericDao gDao = new GenericDao();
				CarroDao cDao = new CarroDao(gDao);
				
				c.setPlaca(placa);
				
				if (acao.equals("excluir")) {
					cDao.excluir(c);
					carros = cDao.listar();
					c = null;
				} else if (acao.equals("editar")) {
					c = cDao.buscar(c);
					carros = null;
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.getMessage();
		} finally {
			request.setAttribute("erro", erro);
			request.setAttribute("carro", c);
			request.setAttribute("carros", carros);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placa = request.getParameter("placa");
		String marca = request.getParameter("marca");
		String modelo = request.getParameter("modelo");
		String ano = request.getParameter("ano");
		String cor = request.getParameter("cor");
		String cmd = request.getParameter("botao");
		
		Carro c = new Carro();
		if (!cmd.equalsIgnoreCase("Listar")) {
			c.setPlaca(placa);
		}
		if (cmd.contentEquals("Inserir") || cmd.equalsIgnoreCase("Atualizar")) {
			c.setMarca(marca);
			c.setModelo(modelo);
			c.setAno(Integer.parseInt(ano));
			c.setCor(cor);
		}
		
		GenericDao gDao = new GenericDao();
		CarroDao cDao = new CarroDao(gDao);
		
		String saida = "";
		String erro = "";
		List<Carro> carros = new ArrayList<Carro>();
		
		try {
			if (cmd.equalsIgnoreCase("Inserir")) {
				cDao.inserir(c);
				saida = "Carro inserido com sucesso";
			}
			if (cmd.equalsIgnoreCase("Atualizar")) {
				cDao.atualizar(c);
				saida = "Carro atualizado com sucesso";
			}
			if (cmd.equalsIgnoreCase("Excluir")) {
				cDao.excluir(c);
				saida = "Carro excluido com sucesso";
			}
			if (cmd.equalsIgnoreCase("Buscar")) {
				c = cDao.buscar(c);
			}
			if (cmd.equalsIgnoreCase("Listar")) {
				carros = cDao.listar();
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			if (!cmd.equalsIgnoreCase("Buscar")) {
				c = null;
			}
			if (!cmd.equalsIgnoreCase("Listar")) {
				carros = null;
			}
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("carro", c);
			request.setAttribute("carros", carros);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
