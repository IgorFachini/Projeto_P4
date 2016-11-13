<%-- 
    Document   : deletarGenero
    Created on : 30/10/2016, 16:50:44
    Author     : Cyber
--%>
<title>Deletar Generos</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Deletar Genero?</h1>
<hr />
<form action="mvc?cmd=GeneroCmd&action=excluido" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
            <input class="form-control" type="text" name="id" value="<c:out value="${genero.id}" />" readonly="readonly" placeholder="Genero ID" />    
        </dd>
        <dt>
            <p><strong>Nome:</strong></p>
        </dt>

        <dd>
        <td><c:out value="${genero.nome}" /></td>
        </dd>
    </dl>
    <hr />
    <div class="form-group col-md-offset-1">
        ${msgLivros}${e}
        ${msgMangas}
        ${esseGenero}
    </div>
    <p>
        <button type="submit" class="btn btn-danger" ${botaoDesabilitado} >Excluir</button> | 
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>