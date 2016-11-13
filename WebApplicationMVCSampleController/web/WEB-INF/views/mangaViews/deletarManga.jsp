<%-- 
    Document   : deletarGenero
    Created on : 30/10/2016, 16:50:44
    Author     : Cyber
--%>
<title>Deletar Manga</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Deletar Manga?</h1>
<hr />
<form action="mvc?cmd=MangaCmd&action=excluido" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
            <input class="form-control" type="text" name="id" value="<c:out value="${manga.id}" />" readonly="readonly" placeholder="Genero ID" />    
        </dd>
        <dt>
            <p><strong>Titulo: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${manga.titulo}" /></td>
        </dd>
        <dt>
            <p><strong>Descrição: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${manga.descricao}" /></td>
        </dd>
        
        <dt>
            <p><strong>Valor: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${manga.valor}" /></td>
        </dd>
        <dt>
            <p><strong>Genero: </strong></p>
        </dt>

        <dd>
        <td><c:out value="${manga.genero.nome}" /></td>
        </dd>


    </dl>
    <hr />
    <p>
        <button type="submit" class="btn btn-danger" >Excluir</button> | 
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>