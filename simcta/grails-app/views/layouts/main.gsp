<!doctype html>
<html lang="pt-BR" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="SiMCTA"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>
<body>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/#">
                <i class="fa grails-icon">
                <asset:image src="logo.png"/>
                </i> SiMCTA
            </a>
        </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cursos <span class="caret"></span></a>
                
                    <ul class="dropdown-menu">
                        <li><g:link controller="course" action="create">Cadastrar Curso</g:link></li>
                        <li><g:link controller="course" action="index">Visualizar Cursos</g:link></li>
                    </ul>
                
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Pacotes <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><g:link controller="packge" action="create">Cadastrar Pacote</g:link></li>
                        <li><a href="#">Visualizar Pacotes</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Alunos <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Matricular Aluno</a></li>
                        <li><a href="#">Consultar Aluno</a></li>
                    </ul>
                </li>
                
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Turmas <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Abrir Turma</a></li>
                        <li><a href="#">Visualizar Turmas</a></li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Professores <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Cadastrar Professor</a></li>
                        <li><a href="#">Visualizar Professores</a></li>
                    </ul>
                </li>
            
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
            
    <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
        <ul class="nav navbar-nav navbar-right">
            <g:pageProperty name="page.nav" />
        </ul>
    </div>
</nav> 

    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>

</body>
</html>
