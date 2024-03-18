![Icone](https://github.com/ECMiraldo/TownBoard2/blob/main/icon.png?raw=true)




## Estrutura 
![Estrutura](https://github.com/ECMiraldo/TownBoard2/blob/main/estrutura.png?raw=true)




## Modelos de Dados
**User**
```
class User {
   var name          : String? = null
   var email         : String? = null
   var uID           : String? = null
```

**City**
```
class City {
   var name          : String? = null
```


**Message**
```
class Message {
   var message       :  String? = null
   var senderUID     :  String? = null
   var senderName    :  String? = null
   var date          :  String? = null
   var hour          :  String? = null
```

**Event**
```
class Event {

   var name          : String? = null
   var local         : String? = null
   var hora          : String? = null
   var data          : String? = null
   var description   : String? = null
   var photoName     : String? = null
```

**Add**
```
class Add {
   var photoName    : String? = null
   var name         : String? = null
   var local        : String? = null
   var description  : String? = null
```


























## Funcionalidades e implementação

**Registo/Login**


A primeira interação de um utilizador com a aplicação é a inserção de dados válidos nos campos de email/login e password para poder aceder à aplicação. Caso não possua um registo válido, pode clicar no botão para se registar e assim abrir a janela de registo para inserir o seu nome, email e password pretendidos. Esta informação será guardada na base de dados do Firebase. 
O Firebase possui um registo de autenticação pré definido, limitado ao número de campos. Assim sendo, criamos uma classe User que guarda todos os campos da autenticação que serão necessários para, por exemplo, identificar o utilizador que enviou uma mensagem no chat. No futuro, poderiam também ser utilizados para guardar os criadores de eventos ou publicidades.


**City**

![CityView](https://github.com/ECMiraldo/TownBoard2/blob/main/CityView.png?raw=true)
		
Após ter feito o login com sucesso, o utilizador é direcionado para uma activity onde lhe são apresentadas todas as cidades que fazem parte da aplicação, através de uma RecyclerView, podendo escolher com qual deseja interagir. Após escolher essa cidade, o utilizador é endereçado ao núcleo da aplicação, onde pode ter total acesso às funcionalidades de chat, publicidades e eventos.


**Chat** 

![ChatView](https://github.com/ECMiraldo/TownBoard2/blob/main/Chat.png?raw=true)

O chat da cidade contém a informação de quem enviou a mensagem, a data/hora e correspondente ordenação para uma fácil visualização dos eventos a ocorrer. Outra funcionalidade é que, as mensagens enviadas pelo utilizador aparecem do lado direito do ecrã com o fundo roxo, enquanto que as mensagens recebidas dos restantes utilizadores aparecem do lado esquerdo, tendo assim uma certa semelhança com outras aplicações de chat mais conhecidas(whatsapp, facebook messenger, …).

Para a realização desta funcionalidade, foram criados três layouts. O message send(mensagens de quem envia), message_receive (mensagens dos outros utilizadores) e um fragment_chat (todas as mensagens). Para que haja uma organização das mensagens e como devem aparecer perante o utilizador, foi necessário criar um adapter que faz essa gestão das views. Este adapter estende para um RecyclerView Adapter, cujo ViewHolder encarrega-se em atribuir as mensagens aos lados correspondentes. 

Todas as mensagens e informações relevantes são guardadas na nossa base de dados sob a coleção da cidade correspondente. 
Sempre que a base de dados muda, ou seja, que uma nova mensagem foi enviada, o nosso ChatFragment busca os dados atualizados e notifica o nosso ChatAdapter desta mudança que retorna ao utilizador a vista atualizada do estado do chat.


**Events**

![Events](https://github.com/ECMiraldo/TownBoard2/blob/main/events.png?raw=true) ![newEvent](https://github.com/ECMiraldo/TownBoard2/blob/main/eventAdd.png?raw=true)

 
Os eventos contém informação simples e rápida sobre eventos que aconteceram ou que acontecerão. O desenvolvimento desta funcionalidade foi divido em 3 partes, a primeira é seu row que é feito com LinearLayout que está ligado ao fragments onde vai buscar as informações que são inseridas como nome,data,hora,local, photo e uma pequena descrição, todas as informações são colocadas em uma listView. 

Por fim temos a parte de adicionar um novo evento que está num fragment a parte. 

**Adds**

![Adds](https://github.com/ECMiraldo/TownBoard2/blob/main/adds.png?raw=true)

Os adds são a parte da aplicação destinada a divulgação de promoções, produtos, lançamentos e outras informações que, diferentemente de eventos, não possuem um data e hora em concreto.

Para criar este fragmento utilizamos uma RecyclerView. Mas quisemos ir mais longe e ao invés de utilizarmos uma row comum como, por exemplo, nas funcionalidades do chat e dos events criamos um CardLayout. O que não apenas torna a aplicação mais dinâmica, mas também ajuda a diferenciar esta parte em relação à sua “irmã”, os events


**Notificações**

![Notifications](https://github.com/ECMiraldo/TownBoard2/blob/main/notifications.png?raw=true)

	O firebase dispõe de um sistema de cloud Messaging para o envio de notificações para os utilizadores. Para que haja ligação entre a aplicação e esse sistema, é obrigatório adicionar os comandos do serviço no AndroidManisfest (MyFirebaseMessagingService). É possível enviar uma notificação manualmente pela própria firebase, preenchendo o assunto, texto e uma data específica, portanto o que fizemos foi apenas criar uma função que preenchesse esses campos com a informação sobre de um evento acabado de criar. 






