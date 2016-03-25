<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bem-vindo ao SiMCTA</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Cursos <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Cadastrar Curso</a></li>
                <li><a href="#">Visualizar Cursos</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Pacotes <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="#">Cadastrar Pacote</a></li>
                <li><a href="#">Visualizar Pacotes</a></li>
            </ul>
        </li>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Alunos <span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><a href="/student/create">Matricular Aluno</a></li>
                <li><a href="/student">Consultar Aluno</a></li>
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
                <li><a href="/teacher/create">Cadastrar Professor</a></li>
                <li><a href="/teacher">Visualizar Professores</a></li>
            </ul>
        </li>
    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="logo.png" class="grails-logo"/>
        </div>
    </div>

    <div id="content" role="main">
        
    </div>

</body>
</html>
