<%-- 
    Document   : editarGenero
    Created on : 30/10/2016, 16:20:24
    Author     : Cyber
--%>


<title>Adicionar/Editar Generos</title>
<!-- Bootstraps -->
<%@include file="/WEB-INF/jspf/head.jspf"%>
<!--
<h1> livro</h1>
<hr />
<dl class="dl-horizontal">
    <form action="mvc?cmd=GeneroCmd&action=editado" method="post">
        <fieldset>
            <div>
                <label for="id"> ID</label> 
                <input class="form-control" type="text" name="id" value="" readonly="readonly" placeholder="Genero ID" />
            </div>
            <div>
                <label for="nome">Nome</label> 
                <input class="form-control" type="text" required="required" name="nome" value="" placeholder="nome" />
            </div>   

            <hr />

            <div>    
                <button type="submit" class="btn btn-success" >Salvar</button> | 
                <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
            </div>
        </fieldset>
    </form>
</dl>-->
<div class="form-group"><h2><span class="glyphicon glyphicon-plus"></span> ${tAdEd} livro</h2></div>

<hr />
<form action="mvc?cmd=LivroCmd&action=editado" method="post">
    <div class="row">


        <div class="form-group col-xs-3">
            <label for="id"> ID</label> 
            <input class="form-control" type="text" name="id" value="<c:out value="${livro.id}" />" readonly="readonly" placeholder="Livro ID" />
        </div>

        <div class="form-group col-xs-8">
            <label for="titulo">Titulo</label> 
            <input class="form-control" type="text"  required="required" name="titulo" value="<c:out value="${livro.titulo}" />" placeholder="Titulo" />
        </div>

    </div>

    <div class="row">
        <div class="form-group col-xs-3">
            <label for="autor">Autor</label> 
            <input class="form-control" type="text"  name="autor" value="<c:out value="${livro.autor}" />" placeholder="Autor" />
        </div>

        <div class="form-group col-xs-3">
            <label for="ano">Ano Edição</label> 
            <input class="form-control" type="number"  id = "ano" name="ano" value="<c:out value="${livro.anoEdicao}" />" placeholder="Ano edição" />

        </div>

        <div class="form-group col-xs-3">
            <label for="valor">Valor</label> 
            <input class="form-control" type="number"  name="valor" value="<c:out value="${livro.valor}" />" placeholder="Valor" />
        </div>

        <div class="form-group col-xs-6">
            <label for="comboRule">Genero:</label>
            <!-- COMBOBOX RULE -->
            <SELECT id="comboGeneros" name="comboGeneros" size="1" class="form-control">
                <c:forEach var="o" items="${comboGeneros}">
                    <option value="${o.id}" ${o.id == idSelecionado ? 'selected="selected"' : ''}>${o.nome}</option>
                </c:forEach>                                    
            </SELECT>
        </div>  
      



    </div>

    <div class="form-group">
        <button type="submit" class="btn btn-success" >Salvar</button> | 
        <button type="button" class="btn btn-default" data-dismiss="modal">Voltar</button>
    </div>

</form>



<%@include file="/WEB-INF/jspf/footer.jspf"%>

