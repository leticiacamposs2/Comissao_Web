<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="entidade.Vendedor, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
	<title>Sistema de Controle de Vendas Web</title>
	<meta charset="UTF-8">
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
	<script>
		function remover( matricula ) {
			if (confirm("Remove o vendedor com a matricula" + matricula)) {
				$('#formVendedor').empty();
				$('#formVendedor').append('<input type="hidden" name="txtMatricula" value="' + matricula + '"/>');
				$('#formVendedor').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formVendedor').submit();
			}
		}
		
		function editar( matricula ) {
			$('#formVendedor').empty();
			$('#formVendedor').append('<input type="hidden" name="txtMatricula" value="' + matricula + '"/>');
			$('#formVendedor').append('<input type="hidden" name="cmd" value="editar"/>');
			$('#formVendedor').submit();
		}		
	</script>
</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="./principal.jsp">Menu Principal > </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="./principal.jsp">Inicio <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="./vendedor.jsp">Vendedor <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="./vendas.jsp">Vendas <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="./comissoes.jsp">Comissões <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Relatórios <span class="sr-only">(current)</span></a>
      </li>    
      <li class="nav-item active">
        <a class="nav-link" href="./login.jsp">Sair <span class="sr-only">(current)</span></a>
      </li>        
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="TXTPESQUISA" placeholder=" " aria-label="Pesquisar">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Pesquisar</button>
    </form>
  </div>
</nav>

  
<div class="container">
  <br />
  <br /> 
</div>

	<h2><center>Cadastro de Vendedor</center></h2>
	
	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Vendedor> lista = (List<Vendedor>)session.getAttribute("LISTA");
	   if (lista == null) { 
	   	   lista = new ArrayList<Vendedor>();
	   } else { 
		   session.setAttribute("LISTA", null);
	   }
	   
	   Vendedor vendedorAtual = (Vendedor)session.getAttribute("VENDEDOR_ATUAL");
	   if (vendedorAtual == null) { 
		   vendedorAtual = new Vendedor();
	   } else { 
		   session.setAttribute("VENDEDOR_ATUAL", null);
	   }
	   
	   if (msg != null) {
		   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
	
	
	<form matricula="formVendedor" action="./VendedorController" method="post">
		<div class="container">
			<div class="form-group">
    			<label for="txtMatricula">Matricula</label>
    			<input type="text" class="form-control" id="txtMatricula" name="txtMatricula" value="<%=vendedorAtual.getMatricula()%>"/>
  			</div>
			<div class="form-group">
    			<label for="txtNome">Nome</label>
    			<input type="text" class="form-control" id="txtNome" name="txtNome" value="<%=vendedorAtual.getNome()%>"/>
  			</div>  	
			<div class="form-group">
    			<label for="txtSalario">Salário</label>
    			<input type="text" class="form-control" id="txtSalario" name="txtSalario" value="<%=vendedorAtual.getSalario()%>"/>
  			</div>   	
			<div class="form-group">
    			<label for="txtNivelExperiencia">Nível de Experiência</label>
    			<select class="form-control" id="txtNivelExperiencia" name="txtNivelExperiencia">
      				<option value="treinamento">Em treinamento</option>
      				<option value="junior">Júnior</option>
      				<option value="pleno">Pleno</option>
				    <option value="senior">Sênior</option>
				</select>
			</div>
			<div class="form-group">
				<%if (vendedorAtual.getMatricula() == 0) { %>
					<button type="submit" class="btn btn-primary" name="cmd" value="adicionar">Adicionar</button>
				<%} else { %>
					<button type="submit" class="btn btn-primary" name="cmd" value="salvar">Salvar</button>
				<%} %>
				<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
			</div>																		
		</div>
		<%if (lista.size() > 0) {%>
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Matricula</th>
						<th>Nome</th>
						<th>Salario</th>
						<th>NivelExperiencia</th>
					</tr>
				</thead>
				<tbody>
					<% for (Vendedor v : lista) { %>
					<tr>
						<td><%=v.getMatricula()%></td>
						<td><%=v.getNome()%></td>
						<td><%=v.getSalario()%></td>
						<td><%=v.getNivelExperiencia()%></td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-primary" onclick="remover(<%=v.getMatricula()%>);">Remover</button>
								<button type="button" class="btn btn-primary" onclick="editar(<%=v.getMatricula()%>);">Editar</button>
							</div>																		
						</td>
					</tr>
					<% } %>
				</tbody>
			</table>
		</div>
		<%} %>
	</form>

	<script>
		$('#txtNivelExperiencia').val("<%=vendedorAtual.getNivelExperiencia()%>");
	</script>			
</body>
</html>