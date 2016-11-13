<%-- 
    Document   : detalhesGenero
    Created on : 03/11/2016, 13:26:22
    Author     : Cyber
--%>
<title>Detalhes Generos</title>

<%@include file="/WEB-INF/jspf/head.jspf"%>

<h1>Detalhes Genero</h1>
<hr />
<form action="mvc?cmd=GeneroCmd&action=editado" method="post">
    <dl class="dl-horizontal">
        <dt>
            <p><strong>ID:</strong></p>
        </dt>

        <dd>
        <td name="id"><c:out value="${genero.id}" /></td>
        </dd>
        <dt>
            <p><strong>Nome:</strong></p>
        </dt>

        <dd>
        <td><c:out value="${genero.nome}" /></td>
        </dd>

    </dl>
    <div>
        <div>
            ${msgLivros}
            <table id="grid-basic" class="table table-condensed table-hover table-striped">         
                <tbody>
                    <c:forEach var="o" items="${livros}">
                        <tr>
                            <td><c:out value="${o.id}" /></td>
                            <td><c:out value="${o.titulo}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div>
            ${msgMangas}
            <table id="grid-basic" class="table table-condensed table-hover table-striped">      
                <tbody>
                    <c:forEach var="o" items="${mangas}">
                        <tr>
                            <td><c:out value="${o.id}" /></td>
                            <td><c:out value="${o.titulo}" /></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <hr />
    <p>
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </p>
</form>
<%@include file="/WEB-INF/jspf/footer.jspf"%>

