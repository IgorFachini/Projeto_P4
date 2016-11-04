<%-- 
    Document   : editarGenero
    Created on : 30/10/2016, 16:20:24
    Author     : Cyber
--%>


        <title>Adicionar/Editar Generos</title>
        <!-- Bootstraps -->
        <%@include file="/WEB-INF/jspf/head.jspf"%>
   
        <h1>${tAdEd} genero</h1>
        <hr />
        <dl class="dl-horizontal">
            <form action="mvc?cmd=GeneroCmd&action=editado" method="post">
                <fieldset>
                    <div>
                        <label for="id"> ID</label> 
                        <input class="form-control" type="text" name="id" value="<c:out value="${genero.id}" />" readonly="readonly" placeholder="Genero ID" />
                    </div>
                    <div>
                        <label for="nome">Nome</label> 
                        <input class="form-control" type="text" required="required" name="nome" value="<c:out value="${genero.nome}" />" placeholder="nome" />
                    </div>   

                    <hr />

                    <div>    
                        <button type="submit" class="btn btn-success" >Salvar</button> | 
                        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
                    </div>
                </fieldset>
            </form>
        </dl>

        <%@include file="/WEB-INF/jspf/footer.jspf"%>

        