# Trabalho Chat Multicast
O programa apresentado consciste em uma aplicação java que utiliza da biblioteca java.net para fazer conexoes UDP entre uma classe Cliente e uma Classe Servidor. É possível também fazer uma conexão multicast peer to peer entre clientes para fazer um chat de texto entre os mesmos.

É apresentado no programa uma Classe Cliente para fazer que permite ao usuário criar uma sala de chat multicast, ver as salas existetentes com seus participantes e entrar em uma sala de chat multicast. Fazendo assim uma conexão com o servidor para buscar os dados.

# Autor
Wilcson Araújo

# Protocolo de comunicação

CRIAR SALA: Desenvolvido a entrada automática no bate papo.

LISTAR SALA: Ao informar **::salas** no bate papo, o servidor exibe as salas disponíveis para acesso.

LISTAR MEMBROS DA SALA: Para saber as pessoas que estão disponiveis na sala, basta digitar **::listarclientes** no bate papo para o servidor enviar a lista.

SAIR DA SALA: Para sair da sala de bate papo, basta apenas digitar no bate papo o comando **::sair**

ENVIAR MENSAGEM: O programa permite o envio de mensagens para todos da sala e para o privado de alguma pessoa presente na sala. Só digitar o comando **::msg** + **nome da pessoa** que a mensagem será enviado apenas para a pessoa selecionada.
