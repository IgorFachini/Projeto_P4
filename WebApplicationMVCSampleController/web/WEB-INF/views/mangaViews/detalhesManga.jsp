<%-- 
    Document   : detalhesGenero
    Created on : 03/11/2016, 13:26:22
    Author     : Cyber
--%>
         <title>Detalhes Manga</title>

        <%@include file="/WEB-INF/jspf/head.jspf"%>
  
        <h1>Detalhes Manga</h1>
        <hr />
        <form action="mvc?cmd=MangaCmd&action=editado" method="post">
            <dl class="dl-horizontal">
                <dt>
                    <p><strong>ID:</strong></p>
                </dt>

                <dd>
                <td name="id"><c:out value="${manga.id}" /></td>
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
                <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
            </p>
        </form>
        <%@include file="/WEB-INF/jspf/footer.jspf"%>

   