<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Carros</title>
    <link rel="icon" type="image/png" href="favicon.png">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="style.css" type="text/css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
</head>
<body class="bg-dark">
    <div class="container text-center mt-4">
        <h1 class="text-white">Cadastro de Carros</h1>
        <form action="carro" method="post" class="mt-4 bg-dark">
            <table class="table tabela-personalizada">
                <tr>
                    <td colspan="3">
                        <input type="text" id="placa" name="placa" class="form-control" placeholder="Placa" value="<c:out value='${carro.placa}' />">
                    </td>
                    <td>
                        <input type="submit" name="botao" value="Buscar" class="btn btn-secondary w-100">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="text" name="marca" class="form-control" placeholder="Marca" value="<c:out value='${carro.marca}' />">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="text" name="modelo" class="form-control" placeholder="Modelo" value="<c:out value='${carro.modelo}' />">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="number" name="ano" class="form-control" placeholder="Ano" value="<c:out value='${carro.ano}' />">
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <input type="text" name="cor" class="form-control" placeholder="Cor" value="<c:out value='${carro.cor}' />">
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" name="botao" value="Inserir" class="btn btn-success w-100"></td>
                    <td><input type="submit" name="botao" value="Atualizar" class="btn btn-primary w-100"></td>
                    <td><input type="submit" name="botao" value="Excluir" class="btn btn-danger w-100"></td>
                    <td><input type="submit" name="botao" value="Listar" class="btn btn-warning w-100"></td>
                </tr>
            </table>
        </form>

        <c:if test="${not empty saida}">
            <h2 class="text-primary"> <c:out value="${saida}" /> </h2>
        </c:if>

        <c:if test="${not empty erro}">
            <h2 class="text-danger"> <c:out value="${erro}" /> </h2>
        </c:if>

        <c:if test="${not empty carros}">
            <table class="table table-dark table-striped mt-4">
                <thead>
                    <tr>
                        <th>Placa</th>
                        <th>Marca</th>
                        <th>Modelo</th>
                        <th>Ano</th>
                        <th>Cor</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="c" items="${carros}">
                        <tr>
                            <td>${c.placa}</td>
                            <td>${c.marca}</td>
                            <td>${c.modelo}</td>
                            <td>${c.ano}</td>
                            <td>${c.cor}</td>
                            <td><a href="${pageContext.request.contextPath}/carro?acao=editar&id=${c.placa}" class="btn btn-warning btn-sm">Editar</a></td>
                            <td><a href="${pageContext.request.contextPath}/carro?acao=excluir&id=${c.placa}" class="btn btn-danger btn-sm">Excluir</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</body>
</html>
