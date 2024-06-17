const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/gs-guide-websocket' //서버 IP:port
});

stompClient.onConnect = (frame) => {
    setConnected(true);
    console.log('Connected: ' + frame);
    stompClient.subscribe('/topic/messages/'+$("#chatRoomId").val(), (message) => {
        showMessage(JSON.parse(message.body));
    });
};

stompClient.onWebSocketError = (error) => {
    console.error('Error with websocket', error);
};

stompClient.onStompError = (frame) => {
    console.error('Broker reported error: ' + frame.headers['message']);
    console.error('Additional details: ' + frame.body);
};

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        getPrevMessages(); //이전 대화내용 조회
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

function connect() {``
    stompClient.activate();
}

function disconnect() {
    stompClient.deactivate();
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    console.log("Sending message");
    stompClient.publish({
        destination: "/app/message",
        body: JSON.stringify({
            'content': $("#content").val(),
            'chatRoomId': $("#chatRoomId").val(),
            'memberId': $("#memberId").val(),
            'memberName': $("#memberName").val()
        })
    });
    $("#content").val('')
}

function showMessage(message) {
    $("#messages").append(
        "<tr>" +
            "<td>" + message.memberName + " \>\>\> " + "</td>" +
            "<td>" + message.content + "</td>" +
        "</tr>");

    //가장 아래로 스크롤 내리기
    window.scrollTo(0, document.body.scrollHeight);
}

$(function () {
    $("form").on('submit', (e) => e.preventDefault());
    $( "#connect" ).click(() => connect());
    $( "#disconnect" ).click(() => disconnect());
    $( "#send" ).click(() => sendMessage());
});

const getPrevMessages = function () {
    fetch('/chat-rooms/' + $("#chatRoomId").val() + '/messages')
        .then(response => response.json()) // JSON 응답을 파싱
        .then(data => {
            const tableBody = document.getElementById('messages');
            // 테이블 내용 비우기
            tableBody.innerHTML = '';

            // JSON 응답에서 각 메시지를 가져와 테이블에 추가
            data.forEach(function (message) {
                const row = document.createElement('tr');
                const nameCell = document.createElement('td');
                const contentCell = document.createElement('td');
                nameCell.textContent = message.memberName + " \>\>\> ";
                contentCell.textContent = message.content;
                row.appendChild(nameCell);
                row.appendChild(contentCell);
                tableBody.appendChild(row);
            });

            //가장 아래로 스크롤 내리기
            window.scrollTo(0, document.body.scrollHeight);
            
        })
        .catch(error => console.error('There was an error!', error));
};