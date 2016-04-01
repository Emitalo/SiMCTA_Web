## SiMCTA_Web

Sistema de Matrícula do Centro de Treinamento Automotivo (CTA)

##### Tecnologias utilizadas

* _Grails:_ 3.1.1

* _MySQL:_ 5.5

##### Execução da aplicação (local)

* Criar um banco de dados para a aplicação 

        nome do banco de dados: simctaWeb

* Clonar o repositório

        git clone https://github.com/SiMCTA/SiMCTA_Web.git

* Entrar na pasta da aplicação
		
		cd SiMCTA_Web/simcta/
 
* Editar o arquivo de conexão com o banco para colocar o seu usuário e senha configurados no MySQL
		
		caminho do arquivo: simcta/grails-app/conf/application.yml

* Entrar na pasta da aplicação
		
		cd SiMCTA_Web/simcta/

* Executar a aplicação
		
		grails run-app


##### Deploy da aplicação

* Execute os mesmos passos anteriores, mas ao invés de:
	
		grails run-app

* Execute:

		grails war
	
* Pegue o arquivo .war na pasta /build/libs e faça o deploy da aplicação no servidor desejado.

	
