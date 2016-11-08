<%-- 
    Document   : detalhesGenero
    Created on : 03/11/2016, 13:26:22
    Author     : Cyber
--%>
         <title>Detalhes Livro</title>

        <%@include file="/WEB-INF/jspf/head.jspf"%>
  
        <h1>Detalhes Livro</h1>
        <hr />
        <form action="mvc?cmd=GeneroCmd&action=editado" method="post">
            <dl class="dl-horizontal">
                <dt>
                    <p><strong>ID:</strong></p>
                </dt>

                <dd>
                <td name="id"><c:out value="${livro.id}" /></td>
                </dd>
                <dt>
                    <p><strong>Titulo: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${livro.titulo}" /></td>
                </dd>
                <dt>
                    <p><strong>Autor: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${livro.autor}" /></td>
                </dd>
                <dt>
                    <p><strong>Ano Edição: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${livro.anoEdicao}" /></td>
                </dd>
                <dt>
                    <p><strong>Valor: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${livro.valor}" /></td>
                </dd>
                  <dt>
                    <p><strong>Genero: </strong></p>
                </dt>

                <dd>
                <td><c:out value="${livro.genero.nome}" /></td>
                </dd>
                

            </dl>
            <hr />
            <p>
                <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
            </p>
        </form>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

   